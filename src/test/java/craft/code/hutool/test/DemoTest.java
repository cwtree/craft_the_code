package craft.code.hutool.test;

import java.lang.management.ManagementFactory;
import java.net.NetworkInterface;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.google.common.collect.Maps;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.ChineseDate;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.text.StrSpliter;
import cn.hutool.core.thread.ConcurrencyTester;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ClassLoaderUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.dfa.WordTree;
import cn.hutool.setting.SettingUtil;

/**
 * 
 * 
 * @author phoenix
 * @date 2021-2-12
 */
public class DemoTest {
	
	/**
	 *     1111
	 * 12345
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param a
	 * @param b
	 * @return
	 */
	 public String fun(String a,String b) {
		 int lenA = a.length();
		 int lenB = b.length();
		 int len = Math.max(lenA, lenB);
		 if(lenA<len) {
			 StringUtils.leftPad(a, len,'0');
		 }
		 if(lenB<len) {
			 StringUtils.leftPad(b, len,'0');
		 }
		 StringBuffer sb = new StringBuffer();
		 int t = 0; 
		 for(int i=0;i<len;i++) {
			 int temp = Integer.parseInt(a.charAt(i)+"")+Integer.parseInt(b.charAt(i)+"")+t;
			 if(temp>=10) {
				 sb.append(0);
				 t = 1;
			 }else {
				 sb.append(temp);
			 }
		 }
		 return sb.reverse().toString();
	 }

	@Test
	public void test() {
		System.out.println(fun("1111", "12345"));

	}

}
