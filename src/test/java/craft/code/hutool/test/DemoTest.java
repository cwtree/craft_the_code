package craft.code.hutool.test;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.GraphLayout;
import org.rocksdb.StatsCollectorInput;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chiwei.craft.code.serial_deserial.ProtostuffUtil;
import com.chiwei.craft.code.serial_deserial.SerializeDeserializeWrapper;
import com.chiwei.craft.code.util.Http;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * 
 * @author phoenix
 * @date 2021-2-12
 */
public class DemoTest {

	/**
	 * 1111 12345
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param a
	 * @param b
	 * @return
	 */
	public String fun(String a, String b) {
		int lenA = a.length();
		int lenB = b.length();
		int len = Math.max(lenA, lenB);
		if (lenA < len) {
			StringUtils.leftPad(a, len, '0');
		}
		if (lenB < len) {
			StringUtils.leftPad(b, len, '0');
		}
		StringBuffer sb = new StringBuffer();
		int t = 0;
		for (int i = 0; i < len; i++) {
			int temp = Integer.parseInt(a.charAt(i) + "") + Integer.parseInt(b.charAt(i) + "") + t;
			if (temp >= 10) {
				sb.append(0);
				t = 1;
			} else {
				sb.append(temp);
			}
		}
		return sb.reverse().toString();
	}

	@Test
	public void test() {
		String str1 = "0.80";
		String str2 = "0.8";
		BigDecimal b1 = new BigDecimal(str1);
		BigDecimal b2 = new BigDecimal(str2);
		System.out.println(b1.equals(b2));
		System.out.println(b1 == b2);
		System.out.println(b1.compareTo(b2));
		int a = 800;

		System.out.println(NumberUtil.div(89, 1000, 2));
		System.out.println(new BigDecimal(89).divide(new BigDecimal(1000)).doubleValue());
	}

	@Test
	public void testSdkToken() throws Exception {
		String str = SecureUtil.md5("cmcchy2021");
		byte[] bb = HexUtil.decodeHex(str);
		String base64 = Base64.encode(bb);
		String appSecret = "gPnAaCdbHBTMVKbLHhw/2A==";
		HMac hmac = new HMac(HmacAlgorithm.HmacSHA256, Base64.decode(appSecret));
		String appType = "X-SOHO-AppType";
		String clientVersion = "X-SOHO-ClientVersion";
		String deviceId = "X-SOHO-DeviceId";
		String romVersion = "X-SOHO-RomVersion";
		String sohoToken = "X-SOHO-SohoToken";
		String timestamp = "X-SOHO-Timestamp";
		String userId = "X-SOHO-UserId";
		String uuid = "X-SOHO-Uuid";
		String versionNum = "X-SOHO-VersionNum";

		String sign = "X-SOHO-Signature";

		/**
		 * String toBeSign = "POST&/token/sdkToken&" + appType + "=1.0.0&" +
		 * clientVersion + "=1&" + deviceId + "=132&" + romVersion + "=123&" + timestamp
		 * + "=" + DateUtil.current() + "&" + uuid + "=" + UUID.randomUUID().toString()
		 * + "&" + versionNum + "=1";
		 **/
		Map<String, String> header = Maps.newTreeMap();
		header.put(appType, "1");
		header.put(clientVersion, "1.0.0");
		header.put(deviceId, "device222");
		header.put(romVersion, "romVersion111");
		header.put(timestamp, DateUtil.current() + "");
		header.put(uuid, UUID.randomUUID().toString());
		header.put(versionNum, "1");
		header.put(appType, "1.0.0");
		header.put(appType, "1.0.0");
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, String> entry : header.entrySet()) {
			sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
			// System.out.println("Key = " + entry.getKey() + ", Value = " +
			// entry.getValue());

		}
		String toBeSign = sb.substring(0, sb.length() - 1);
		toBeSign = "POST&/token/sdkToken&" + toBeSign;
		System.out.println("待签名字符串 " + toBeSign);
		String signRes = hmac.digestHex(toBeSign);
		header.put(sign, signRes);
		String res = Http.postJson("http://172.21.44.193:9094/terminal/token/sdkToken", "", header);
		System.out.println("请求结果 " + res);
	}

	@Test
	public void testSmscode() throws Exception {
		String str = SecureUtil.md5("cmcchy2021");
		byte[] bb = HexUtil.decodeHex(str);
		String base64 = Base64.encode(bb);
		String appSecret = "gPnAaCdbHBTMVKbLHhw/2A==";
		HMac hmac = new HMac(HmacAlgorithm.HmacSHA256, Base64.decode(appSecret));
		String appType = "X-SOHO-AppType";
		String clientVersion = "X-SOHO-ClientVersion";
		String deviceId = "X-SOHO-DeviceId";
		String romVersion = "X-SOHO-RomVersion";
		String sohoToken = "X-SOHO-SohoToken";
		String timestamp = "X-SOHO-Timestamp";
		String userId = "X-SOHO-UserId";
		String uuid = "X-SOHO-Uuid";
		String versionNum = "X-SOHO-VersionNum";

		String sign = "X-SOHO-Signature";

		/**
		 * String toBeSign = "POST&/token/sdkToken&" + appType + "=1.0.0&" +
		 * clientVersion + "=1&" + deviceId + "=132&" + romVersion + "=123&" + timestamp
		 * + "=" + DateUtil.current() + "&" + uuid + "=" + UUID.randomUUID().toString()
		 * + "&" + versionNum + "=1";
		 **/
		Map<String, String> header = Maps.newTreeMap();
		header.put(appType, "1");
		header.put(clientVersion, "1.0.0");
		header.put(deviceId, "device222");
		header.put(romVersion, "romVersion111");
		header.put(timestamp, DateUtil.current() + "");
		header.put(uuid, UUID.randomUUID().toString());
		header.put(versionNum, "1");
		header.put(appType, "1.0.0");
		header.put(appType, "1.0.0");
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, String> entry : header.entrySet()) {
			sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
			// System.out.println("Key = " + entry.getKey() + ", Value = " +
			// entry.getValue());

		}
		String toBeSign = sb.substring(0, sb.length() - 1);
		toBeSign = "POST&/token/sdkToken&" + toBeSign;
		System.out.println("待签名字符串 " + toBeSign);
		String signRes = hmac.digestHex(toBeSign);
		header.put(sign, signRes);
		String res = Http.postJson("http://172.21.44.193:9094/terminal/token/sdkToken", "", header);
		System.out.println("请求结果 " + res);
	}

	@Test
	public void testLogin() throws Exception {
		String str = SecureUtil.md5("cmcchy2021");
		byte[] bb = HexUtil.decodeHex(str);
		String base64 = Base64.encode(bb);
		String appSecret = "gPnAaCdbHBTMVKbLHhw/2A==";
		HMac hmac = new HMac(HmacAlgorithm.HmacSHA256, Base64.decode(appSecret));
		String appType = "X-SOHO-AppType";
		String clientVersion = "X-SOHO-ClientVersion";
		String deviceId = "X-SOHO-DeviceId";
		String romVersion = "X-SOHO-RomVersion";
		String sohoToken = "X-SOHO-SohoToken";
		String timestamp = "X-SOHO-Timestamp";
		String userId = "X-SOHO-UserId";
		String uuid = "X-SOHO-Uuid";
		String versionNum = "X-SOHO-VersionNum";

		String sign = "X-SOHO-Signature";

		/**
		 * String toBeSign = "POST&/token/sdkToken&" + appType + "=1.0.0&" +
		 * clientVersion + "=1&" + deviceId + "=132&" + romVersion + "=123&" + timestamp
		 * + "=" + DateUtil.current() + "&" + uuid + "=" + UUID.randomUUID().toString()
		 * + "&" + versionNum + "=1";
		 **/
		Map<String, String> header = Maps.newTreeMap();
		header.put(appType, "1");
		header.put(clientVersion, "1.0.0");
		header.put(deviceId, "device222");
		header.put(romVersion, "romVersion111");
		header.put(timestamp, DateUtil.current() + "");
		header.put(uuid, UUID.randomUUID().toString());
		header.put(versionNum, "1");
		header.put(appType, "1.0.0");
		header.put(appType, "1.0.0");
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, String> entry : header.entrySet()) {
			sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
			// System.out.println("Key = " + entry.getKey() + ", Value = " +
			// entry.getValue());

		}
		String toBeSign = sb.substring(0, sb.length() - 1);
		toBeSign = "POST&/token/sdkToken&" + toBeSign;
		System.out.println("待签名字符串 " + toBeSign);
		String signRes = hmac.digestHex(toBeSign);
		header.put(sign, signRes);
		String res = Http.postJson("http://172.21.44.193:9094/terminal/token/sdkToken", "", header);
		System.out.println("请求结果 " + res);
	}

	@Test
	public void testIdtoken() throws Exception {
		String str = SecureUtil.md5("cmcchy2021");
		byte[] bb = HexUtil.decodeHex(str);
		String base64 = Base64.encode(bb);
		String appSecret = "gPnAaCdbHBTMVKbLHhw/2A==";
		HMac hmac = new HMac(HmacAlgorithm.HmacSHA256, Base64.decode(appSecret));
		String appType = "X-SOHO-AppType";
		String clientVersion = "X-SOHO-ClientVersion";
		String deviceId = "X-SOHO-DeviceId";
		String romVersion = "X-SOHO-RomVersion";
		String sohoToken = "X-SOHO-SohoToken";
		String timestamp = "X-SOHO-Timestamp";
		String userId = "X-SOHO-UserId";
		String uuid = "X-SOHO-Uuid";
		String versionNum = "X-SOHO-VersionNum";

		String sign = "X-SOHO-Signature";

		/**
		 * String toBeSign = "POST&/token/sdkToken&" + appType + "=1.0.0&" +
		 * clientVersion + "=1&" + deviceId + "=132&" + romVersion + "=123&" + timestamp
		 * + "=" + DateUtil.current() + "&" + uuid + "=" + UUID.randomUUID().toString()
		 * + "&" + versionNum + "=1";
		 **/
		Map<String, String> header = Maps.newTreeMap();
		header.put(appType, "1");
		header.put(clientVersion, "1.0.0");
		header.put(deviceId, "device222");
		header.put(romVersion, "romVersion111");
		header.put(timestamp, DateUtil.current() + "");
		header.put(uuid, UUID.randomUUID().toString());
		header.put(versionNum, "1");
		header.put(appType, "1.0.0");
		header.put(appType, "1.0.0");
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, String> entry : header.entrySet()) {
			sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
			// System.out.println("Key = " + entry.getKey() + ", Value = " +
			// entry.getValue());

		}
		String toBeSign = sb.substring(0, sb.length() - 1);
		toBeSign = "POST&/token/sdkToken&" + toBeSign;
		System.out.println("待签名字符串 " + toBeSign);
		String signRes = hmac.digestHex(toBeSign);
		header.put(sign, signRes);
		String res = Http.postJson("http://172.21.44.193:9094/terminal/token/sdkToken", "", header);
		System.out.println("请求结果 " + res);
	}

	@Test
	public void testPhoneAddress() {
		String phone = "0571885288520571885288520571885288520571885288520571885288521111";
		String name = "中国移动中国移动中国移动中国移动中国移动中国移动中国移动中国移动中国移动中国移动中国移动中国移动中国移动中国移动中国移动中国移";
		List<Address> list = Lists.newArrayList();
		for (int i = 0; i < 100; i++) {
			JSONObject json = new JSONObject();
			json.put("phone", phone);
			json.put("name", name);
			Address address = Address.builder().number(phone).nickName(name).build();
			list.add(address);
		}
		SerializeDeserializeWrapper<List<Address>> wrapper = SerializeDeserializeWrapper.builder(list);
		System.out.println("对象大小：" + GraphLayout.parseInstance(wrapper).totalSize());
		System.out.println("------------------------------------------");
		byte[] jaBb = ProtostuffUtil.serialize(wrapper);
		System.out.println("list对象pb序列化后长度：" + jaBb.length);
		System.out.println("序列化后的对象大小：" + GraphLayout.parseInstance(jaBb).totalSize());
		SerializeDeserializeWrapper<?> wrapperRes = ProtostuffUtil.deserialize(jaBb, SerializeDeserializeWrapper.class);
		System.out.println("Pb反序列化对象的字符串 = " + JSON.toJSONString(wrapperRes.getData()));
		System.out.println("反序列化后的对象大小：" + GraphLayout.parseInstance(wrapperRes).totalSize());
		// System.out.println("Pb反序列化数据读取res = " + res);
		Date date = DateUtil.date();
		// DatePattern.CHINESE_DATE_TIME_FORMATTER
		System.out.println(DateUtil.format(date, "HH:mm"));
	}

	private static String CHINESE = "从全面实施重大人才工程到深化人才发展体制机制改革从作出加快建设人才强国的重大决策到部署全方位培养引进用好人才……党的十八大以来以习近平同志为核心的党中央统揽伟大斗争、伟大工程、伟大事业、伟大梦想广开进贤之路、广纳天下英才领导推动新时代人才工作取得历史性成就、发生历史性变革奏响了人才发展与民族复兴同频共振的铿锵乐章全国人才资源总量快速壮大专业技术人才从2010年的5550.4万人增长到2019年7839.8万人成为全球规模最宏大、门类最齐全的人才资源大国人才素质整体提升截至2019年底主要劳动年龄人口受过高等教育的比例从2010年的12.5%提高到21.2%专业技术人才中本科及以上学历人员的比例由35.9%提高到48%人才创新成绩单愈发亮丽人才发展与科技创新相互成就从“嫦娥”飞天到“蛟龙”入海从“天眼”探空到“墨子”传信从北斗组网到神威超算从5G商用全面推进到新冠疫苗加速研制……一批重大科技创新成果喷涌而出一些前沿领域开始进入并跑、领跑阶段中国科技实力实现历史性跨越我国在全球创新版图中的位势节节攀升人才国际竞争优势稳步增强我国研发人员总量连续8年稳居世界首位国际专利申请量以68720件稳居世界第一《2020年全球创新指数排名》显示我国排名从2015年的第29位快速上升到第14位化学、材料、工程科学、生命科学等学科领域高水平科学家数量增长迅速、进入世界前列……百年大党风华正茂千秋伟业人才为先随着中华民族伟大复兴进入关键时期一支规模宏大、素质优良、梯次合理、作用突出的人才队伍正加速集结释放出磅礴力量神州大地百舸争流、千帆竞发、万马奔腾正成为各类人才创新创造、大有可为、大有作为的热土谋篇布局抓纲举要习近平总书记为人才事业创新发展按下快进键功以才成业由才广党的十八大以来面对错综复杂的国际局势和艰巨繁重的改革发展稳定任务习近平总书记以马克思主义政治家、思想家、战略家的深刻洞察力、敏锐判断力和战略定力把人才工作摆在治国理政大局的关键位置亲自关怀、亲自谋划、亲自部署、亲自推动作出一系列重要论述和指示批示为新时代人才工作指明了前进航向、注入了强劲动力新思想领航定向办好中国的事情关键在党关键在人关键在人才“我们比历史上任何时期都更接近实现中华民族伟大复兴的宏伟目标我们也比历史上任何时期都更加渴求人才”“人才资源作为经济社会发展第一资源的特征和作用更加明显人才竞争已经成为综合国力竞争的核心”2013年10月在欧美同学会成立100周年庆祝大会上习近平总书记对时代发展大势作出科学判断深刻阐明了人才工作与实现中华民族伟大复兴中国梦之间的内在联系深刻阐明了人才资源、人才竞争在综合国力竞争中的关键作用从2012年在广东考察工作时指出哪个国家拥有人才上的优势哪个国家最后就会拥有实力上的优势到2014年明确批示择天下英才而用之关键是要坚持党管人才原则遵循社会主义市场经济规律和人才成长规律从2014年与北京师范大学师生代表座谈到2016年出席全国高校思想政治工作会议再到2021年赴清华大学考察习近平总书记对党管人才作出专门强调对教育和人才始终念兹在兹反复强调党和国家事业发展对高等教育的需要对科学知识和优秀人才的需要比以往任何时候都更为迫切新目标振奋人心在2018年全国组织工作会议上习近平总书记明确指出要加快实施人才强国战略确立人才引领发展的战略地位努力建设一支矢志爱国奉献、勇于创新创造的优秀人才队伍“中国要强盛、要复兴就一定要大力发展科学技术努力成为世界主要科学中心和创新高地我们比历史上任何时期都更接近中华民族伟大复兴的目标我们比历史上任何时期都更需要建设世界科技强国！”2018年5月在中国科学院第十九次院士大会、中国工程院第十四次院士大会上习近平总书记为科技工作和人才工作锚定了新目标时隔3年在2021年两院院士大会、中国科协十大上习近平总书记强调激发各类人才创新活力建设全球人才高地新任务直面挑战新一轮科技革命和产业变革正在加速演进科技和人才成为国际战略博弈主战场“我国经济社会发展和民生改善比过去任何时候都更加需要科学技术解决方案都更加需要增强创新这个第一动力”2020年9月在科学家座谈会上习近平总书记把对科学技术和创新的重要性提到了一个新的高度今年习近平总书记向我国广大科技工作者发出号召“要以与时俱进的精神、革故鼎新的勇气、坚忍不拔的定力面向世界科技前沿、面向经济主战场、面向国家重大需求、面向人民生命健康把握大势、抢占先机直面问题、迎难而上肩负起时代赋予的重任努力实现高水平科技自立自强！”从中共十八届中央政治局第九次集体学习到2018年北京大学师生座谈会再到深圳经济特区建立40周年庆祝大会……习近平总书记反复强调培养造就具有国际水平的战略科技人才、科技领军人才、卓越工程师、青年科技人才和高水平创新团队的重要性新要求精准指导2014年5月在上海主持召开外国专家座谈会时习近平总书记深刻指出我们比历史上任何时期都更需要广开进贤之路、广纳天下英才要实行更加开放的人才政策不唯地域引进人才不求所有开发人才不拘一格用好人才2016年4月在网络安全和信息化工作座谈会上习近平总书记强调要“改革人才引进各项配套制度构建具有全球竞争力的人才制度体系”不断提高“全球配置人才资源能力”2018年4月在庆祝海南建省办经济特区30周年大会上习近平总书记指出“吸引人才、留住人才、用好人才最好的环境是良好体制机制”要“坚持五湖四海广揽人才在深化人才发展体制机制改革上有突破实行更加积极、更加开放、更加有效的人才政策”";

	private static String randomName(int len) {
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<len;i++) {
			sb.append(CHINESE.charAt(RandomUtil.randomInt(CHINESE.length()-1)));
		}
		return sb.toString();
	}
	
	/**
	 * 15W极端字符串长度 占用38KB 4W常规长度字符串占用 10KB
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @throws UtilException
	 * @throws UnsupportedEncodingException
	 */
	@Test
	public void testPhoneAddressZip() throws UtilException, UnsupportedEncodingException {
		// String phone =
		// "0571885288520571885288520571885288520571885288520571885288521111";
		// String name =
		// "中国移动中国移动中国移动中国移动中国移动中国移动中国移动中国移动中国移动中国移动中国移动中国移动中国移动中国移动中国移动中国移";
		List<Address> list = Lists.newArrayList();
		for (int i = 0; i < 1; i++) {
			String phone = "188"+RandomUtil.randomString("0123456789", 8);
			String name = randomName(5);
					//RandomUtil.randomString(64);
			Address address = Address.builder().number(phone).nickName(name).build();
			list.add(address);
		}
		String originalStr = JSON.toJSONString(list);
		System.out.println("原始字符串：" + originalStr);
		System.out.println("原始字符串长度：" + originalStr.length());
		System.out.println("JVM内存占用大小：" + GraphLayout.parseInstance(originalStr).totalSize());

		byte[] desKey = SecureUtil.generateKey(SymmetricAlgorithm.DESede.getValue()).getEncoded();
		System.out.println("base64编码的desKey："+Base64.encode(desKey));
		SymmetricCrypto des = new SymmetricCrypto(SymmetricAlgorithm.DESede, desKey);
		// 加密
		byte[] desEnc = des.encrypt(originalStr);
		System.out.println("加密后字节长度：" + desEnc.length);
		System.out.println("加密后JVM内存占用大小：" + GraphLayout.parseInstance(desEnc).totalSize());

		byte[] bb = ZipUtil.gzip(desEnc);
		//byte[] bb = ZipUtil.gzip(originalStr.getBytes("UTF-8"));
		System.out.println("压缩后字节长度：" + bb.length);
		System.out.println("压缩后JVM内存占用大小：" + GraphLayout.parseInstance(bb).totalSize());

		byte[] unzipByte = ZipUtil.unGzip(bb);
		System.out.println("解压后字节长度：" + unzipByte.length);
		System.out.println("解压后JVM内存占用大小：" + GraphLayout.parseInstance(unzipByte).totalSize());

		// 解密
		byte[] desDec = des.decrypt(unzipByte);
		System.out.println("3DES解密字节大小：" + desDec.length);
	}

}

@ToString
@Getter
@Setter
@Builder
class Address implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 550305812083661224L;
	private String number;
	private String nickName;
}
