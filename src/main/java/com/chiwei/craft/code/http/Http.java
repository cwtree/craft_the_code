package com.chiwei.craft.code.http;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author chiwei
 * @Type Http.java
 * @Desc
 * @date 2016年5月25日 下午5:24:58
 */
public class Http {

    private static Logger logger = LoggerFactory.getLogger(Http.class);

    private static CloseableHttpClient httpclient = null;

    private static IdleConMonitorThread scanThread = null;

    private static PoolingHttpClientConnectionManager cm = null;
    private static SSLConnectionSocketFactory sslsf = null;
    private static SSLContextBuilder builder = null;

    static {
        try {
            builder = new SSLContextBuilder();
            // 全部信任 不做身份鉴定
            builder.loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s)
                        throws CertificateException {
                    return true;
                }
            });
            sslsf = new SSLConnectionSocketFactory(builder.build(),
                    new String[]{"SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}, null, (hostname, session) -> true);
            Registry<ConnectionSocketFactory> registry = RegistryBuilder
                    .<ConnectionSocketFactory>create()
                    .register("http", new PlainConnectionSocketFactory()).register("https", sslsf)
                    .build();
            // 连接池设置
            cm = new PoolingHttpClientConnectionManager(registry);
            cm.setMaxTotal(200); // 最多512个连接
            cm.setDefaultMaxPerRoute(64); // 每个路由64个连接
            // 创建client对象
            httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).setConnectionManager(cm)
                    .setConnectionManagerShared(true).disableAutomaticRetries()
                    .disableRedirectHandling().disableCookieManagement().disableConnectionState()
                    .build();
            // 扫描无效连接的线程
            scanThread = new IdleConMonitorThread(cm);
            scanThread.start();
        } catch (Exception e) {
            logger.error("HTTP 异常", e);
        }

    }

    /**
     * postJson:(); Description:TODO post方式发送请，报文为一串json串
     *
     * @param url
     * @param content
     * @return
     * @throws Exception
     * @author chiwei
     * @since JDK 1.6
     */
    public static String postJson(String url, String content) throws Exception {
        return postToUrl(url, content);
    }

    /**
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static String postForm(String url, Map<String, Object> params) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        // 设置内容
        // ContentType alertType = ContentType.create("application/x-www-form-urlencoded",
        // Consts.UTF_8);
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        if (params != null) {
            for (Entry<String, Object> e : params.entrySet()) {
                NameValuePair nv = new BasicNameValuePair(e.getKey(), (String) e.getValue());
                pairs.add(nv);
            }
        }
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, "UTF-8"));
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000)
                .setConnectTimeout(10000).setRedirectsEnabled(false).build();
        httpPost.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        return executeHttpInvoke(httpPost, response);
    }

    /**
     * postToUrl:(); Description:TODO post方式发送请，报文为一串json串
     *
     * @param url
     * @param content
     * @return
     * @throws Exception
     * @author chiwei
     * @since JDK 1.6
     */
    private static String postToUrl(String url, String content) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        // 设置内容
        ContentType type = ContentType.create("application/json", Consts.UTF_8);
        StringEntity reqEntity = new StringEntity(content, type);
        httpPost.setEntity(reqEntity);
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000)
                .setConnectTimeout(10000).setRedirectsEnabled(false).build();
        httpPost.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        return executeHttpInvoke(httpPost, response);
    }



    /**
     *
     * @param url
     * @param content
     * @return
     * @throws Exception
     * @author chiwei
     * @since JDK 1.6
     */
    public static String postWithHeader(String url, String content,Map<String, String> headers) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        // 设置内容
        ContentType type = ContentType.create("application/json", Consts.UTF_8);
        StringEntity reqEntity = new StringEntity(content, type);
        httpPost.setEntity(reqEntity);
        if (headers != null) {
            Set<String> keys = headers.keySet();
            for (String key : keys) {
                httpPost.addHeader(key, headers.get(key));
            }
        }
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000)
                .setConnectTimeout(10000).setRedirectsEnabled(false).build();
        httpPost.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        return executeHttpInvoke(httpPost, response);
    }

    /**
     * 执行调用
     *
     * @param httpPost
     * @param response
     * @return
     * @throws IOException
     */
    private static String executeHttpInvoke(HttpPost httpPost, CloseableHttpResponse response)
            throws IOException {
        try {
            // 执行请求
            response = httpclient.execute(httpPost, HttpClientContext.create());
            HttpEntity he = response.getEntity();
            String res = EntityUtils.toString(he, Consts.UTF_8);
            EntityUtils.consume(he);
            return res;
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    /**
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static InputStream fileStream(String str) throws Exception {
        URL url = new URL(str);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        boolean useHttps = str.startsWith("https");
        if (useHttps) {
            HttpsURLConnection https = (HttpsURLConnection) connection;
            trustAllHosts(https);
            https.setHostnameVerifier((hostname, session) -> true);
            return https.getInputStream();
        }else {
            return connection.getInputStream();
        }
    }

    /**
         * 覆盖java默认的证书验证
         */
    
    private static final TrustManager[] TRUST_MANAGERS = new TrustManager[]{new X509TrustManager() {
        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[]{};
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }
    }};

    /**
      * 信任所有
      * @param connection connection
     */
    private static void trustAllHosts(HttpsURLConnection connection) {
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, TRUST_MANAGERS, new java.security.SecureRandom());
            SSLSocketFactory newFactory = sc.getSocketFactory();
            connection.setSSLSocketFactory(newFactory);
        } catch (Exception e) {
            logger.error("trustAllHosts Exception",e);
        }
    }
    /**
     * get方式发送请，url里自行拼上请求参数
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static String getJsonRes(String url) {

        HttpGet httpGet = new HttpGet(url);
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000)
                .setConnectTimeout(10000).setRedirectsEnabled(false).build();
        httpGet.setConfig(requestConfig);
        // 请求结果
        CloseableHttpResponse response = null;
        String res = "";
        try {
            // 执行get方法
            response = httpclient.execute(httpGet);
            HttpEntity he = response.getEntity();
            res = EntityUtils.toString(he, Consts.UTF_8);
            EntityUtils.consume(he);
            return res;
        } catch (Exception e) {
            logger.error("Url:" + url + " getJsonRes failed!", e);
            return "";
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (Exception e) {
                logger.error("getJsonRes 网络关闭异常", e);
            }
        }
    }

    /**
     * @param url                url
     * @param millisecondTimeout millisecondTimeout
     * @return Boolean
     */
    public static Boolean urlConnectivity(String url, int millisecondTimeout) {

        HttpGet httpGet = new HttpGet(url);
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(millisecondTimeout)
                .setConnectTimeout(millisecondTimeout).setRedirectsEnabled(false).build();
        httpGet.setConfig(requestConfig);
        // 请求结果
        CloseableHttpResponse response = null;
        try {
            // 执行get方法
            response = httpclient.execute(httpGet);
            int status = response.getStatusLine().getStatusCode();
            return status == HttpStatus.OK.value();
        } catch (Exception e) {
            logger.error("Url connect failed:{}", url);
            return false;
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (Exception e) {
                logger.error("Url connect exception:" + url + " ", e);
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        String res = getJsonRes(
                "http://172.21.40.137:3166");
        System.out.println(res);
    }

}

class IdleConMonitorThread extends Thread {

    private static Logger logger = LoggerFactory.getLogger(IdleConMonitorThread.class);

    private final HttpClientConnectionManager connMgr;

    public IdleConMonitorThread(HttpClientConnectionManager connMgr) {
        super();
        this.connMgr = connMgr;
    }

    @Override
    public void run() {
        try {
            this.setName("HttpIdleConnMonitorThread");
            while (true) {
                synchronized (this) {
                    wait(30000L);
                    // 关闭无效连接
                    connMgr.closeExpiredConnections();
                    // 可选, 关闭空闲超过30秒的
                    connMgr.closeIdleConnections(30, TimeUnit.SECONDS);
                }
            }
        } catch (Exception e) {
            logger.error("空闲HTTP连接监控线程异常", e);
        }
    }

}

/**
 * Revision history
 * -------------------------------------------------------------------------
 * <p>
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2016年5月25日 chiwei create
 */
