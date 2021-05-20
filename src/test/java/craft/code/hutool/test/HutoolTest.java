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

import org.junit.Test;


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
public class HutoolTest {

	/**
	 * 16进制转换 还有unicode转换等见源码
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 */
	@Test
	public void testConvert() {
		/**
		 * 字符串编码转换
		 */
		String a = "Hello 中国";
		String hex = Convert.toHex(a, CharsetUtil.CHARSET_UTF_8);
		String raw = Convert.hexToStr(hex, CharsetUtil.CHARSET_UTF_8);
		System.out.println(raw);
		/**
		 * 时间转换，毫秒转成XX小时等
		 */
		long b = 1000 * 60 * 60 * 24;
		long mins = Convert.convertTime(b, TimeUnit.MILLISECONDS, TimeUnit.HOURS);
		System.out.println(mins);
		/**
		 * 金额大小写转换
		 */
		double c = 288.66;
		System.out.println(Convert.digitToChinese(c));

	}

	@Test
	public void testDateTime() throws InterruptedException {
		/**
		 * 当前时间
		 */
		Date now = DateUtil.date();
		System.out.println(now);
		String nowString = DateUtil.now();
		System.out.println(nowString);
		/**
		 * 当前日期
		 */
		String nowDate = DateUtil.today();
		System.out.println(nowDate);

		/**
		 * 字符串转日期 yyyy-MM-dd HH:mm:ss yyyy-MM-dd HH:mm:ss yyyy-MM-dd HH:mm yyyy-MM-dd
		 * HH:mm:ss.SSS
		 * 
		 * DatePattern
		 */
		String dateStr = "2021-2-12";
		System.out.println(DateUtil.parse(dateStr));
		System.out.println(DateUtil.parse(dateStr, "yyyy-MM-dd"));

		/**
		 * 获取Date对象的某个部分
		 */
		System.out.println(DateUtil.year(now));
		System.out.println(DateUtil.month(now) + 1);
		System.out.println(DateUtil.dayOfMonth(now));
		System.out.println(DateUtil.hour(now, true));
		System.out.println(DateUtil.minute(now));
		System.out.println(DateUtil.second(now));

		/**
		 * 开始和结束时间
		 */
		System.out.println(DateUtil.beginOfDay(now));
		System.out.println(DateUtil.endOfDay(now));

		/**
		 * 时间偏移
		 */
		System.out.println(DateUtil.offsetHour(now, 3));
		System.out.println(DateUtil.yesterday());

	}

	@Test
	public void testDateTime2() throws InterruptedException {
		/**
		 * 当前时间
		 */
		Date now = DateUtil.date();
		System.out.println(now);
		String nowString = DateUtil.now();
		System.out.println(nowString);
		/**
		 * 时间差
		 */
		Date from = DateUtil.date();
		Date end = DateUtil.offsetMillisecond(from, 988989898);
		System.out.println(DateUtil.between(from, end, DateUnit.HOUR));
		System.out.println(DateUtil.between(from, end, DateUnit.DAY));
		/**
		 * 格式化时间差，默认精确到毫秒差距
		 */
		System.out.println("差：" + DateUtil.formatBetween(from, end));

		/**
		 * 计时器
		 */
		TimeInterval timer = DateUtil.timer();
		Thread.sleep(2000L);
		System.out.println(timer.interval() + " ms");
		timer.intervalRestart();
		System.out.println(timer.intervalMinute() + " min");

		/**
		 * 格式化字符串 DatePattern
		 */

		/**
		 * 农历
		 */
		ChineseDate chineseDate = new ChineseDate(DateUtil.parseDate("2020-08-28"));
		String cyclicalYmd = chineseDate.getCyclicalYMD();
		System.out.println(cyclicalYmd);

		/**
		 * 对JDK 8 LocalDateTime和LocalDate的支持
		 */
	}

	/**
	 * IO操作，文件流操作 字符串工具 16进制工具 escape工具 转义 hash算法工具 URL工具 XML工具 对象工具 Object 反射工具
	 * Reflect 泛型 分页 系统剪贴板工具clipboard 类工具 class 类加载工具classloader 枚举工具 命令行执行工具
	 * Runtime processor number 数字工具 数组工具 array 随机工具 random 唯一ID工具 压缩工具 zip 引用工具
	 * 身份证工具
	 * 
	 * DFA算法工具 中文分词 拼音工具
	 * 
	 * JSCH,FTP等 soap webservice
	 * 
	 * 加解密
	 * 
	 * 
	 */
	@Test
	public void testDemo() {
		String str = RuntimeUtil.execForStr("ifconfig");
		System.out.println(str);
		String id = "320112198009223312";
		System.out.println(IdcardUtil.getProvinceByIdCard(id));

		System.out.println(SecureUtil.md5("baidu.com"));
		System.out.println(MD5.create().digestHex16("baidu.com"));

		// System.out.println(SecureUtil.m);

	}

	@Test
	public void testEncDec() {
		String cont = "Hello";
		String key = "Yw/Vz4kpJUv0+E/4/LuZEA==";
		System.out.println("aes key = " + key);
		AES aes = SecureUtil.aes(Base64.decode(key));
		byte[] encrypt = aes.encrypt(cont);
		String enc = Base64.encode(encrypt);
		System.out.println("密文:" + enc);
		byte[] decrypt = aes.decrypt(enc);
		System.out.println(new String(decrypt));

		String str = SecureUtil.md5(cont);
		System.out.println("MD5: " + str);
		System.out.println("MD5 16位: " + DigestUtil.md5Hex16(cont));
		System.out.println("SHA-256:" + DigestUtil.sha256Hex("Admin@888" + "9348ierj8truigjf03wioekrutgfhj"));

		// RSA
		RSA rsa = new RSA();
		String privateKey = rsa.getPrivateKeyBase64();
		String publicKey = rsa.getPublicKeyBase64();
		System.out.println("私钥：" + privateKey);
		System.out.println("公钥：" + publicKey);
		String rsaEnc = rsa.encryptBase64(cont, KeyType.PublicKey);
		System.out.println("加密后：" + rsaEnc);
		System.out.println("解密后：" + rsa.decryptStr(rsaEnc, KeyType.PrivateKey));
		PublicKey publicK = rsa.getPublicKey();
		String modulus = HexUtil.encodeHexStr(((RSAPublicKey) publicK).getModulus().toByteArray());
		String exponent = HexUtil.encodeHexStr(((RSAPublicKey) publicK).getPublicExponent().toByteArray());
		System.out.println(modulus);
		System.out.println(exponent);

	}

	@Test
	public void testNet() {
		String ip = "192.168.2.65";
		long ipLong = 2130706433L;
		String long2ip = NetUtil.longToIpv4(ipLong);
		System.out.println(long2ip);
		long ip2long = NetUtil.ipv4ToLong(ip);
		System.out.println(ip2long);
		// 端口探测
		System.out.println("端口是否连通：" + NetUtil.isUsableLocalPort(6379));
		System.out.println("端口是否有效：" + NetUtil.isValidPort(77777));
		// IP脱敏
		System.out.println(NetUtil.hideIpPart(ip));
		// 获取网卡信息
		System.out.println(NetUtil.getNetworkInterfaces());
		// eth4 (Realtek PCIe GBE Family Controller)
		System.out.println(NetUtil.getNetworkInterface("eth4"));
		System.out.println(NetUtil.LOCAL_IP);
	}

	@Test
	public void testDfa() {
		WordTree tree = new WordTree();
		tree.addWord("中国");
		tree.addWord("移动");
		tree.addWord("杭州");
		tree.addWord("移动通信");
		tree.addWord("杭州市");
		tree.addWord("中国人");

		String text = "中国移动在中国，都是中国人，移动杭研在杭州，杭州市";

		List<String> match = tree.matchAll(text, -1, false, false);
		System.out.println(match);
	}

	@Test
	public void testStr() {
		String str = "1w2w3w4w5w6w7";
		// limit 分割成多少个字符串
		System.out.println(StrSpliter.split(str, "w", 1, true, true));
		String a = null;
		System.out.println(StrUtil.trim(a) + "----");
	}

	@Test
	public void testUrl() {
		System.out.println(URLUtil.toURI("http://www.baidu.com/df/sdf/df").getHost());
	}

	@Test
	public void testUtil() {
		System.out.println(RandomUtil.randomInt(10));
	}
	
	private static int getMachinePiece() {
		// 机器码
		int machinePiece;
		try {
			StringBuilder netSb = new StringBuilder();
			// 返回机器所有的网络接口
			Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
			// 遍历网络接口
			while (e.hasMoreElements()) {
				NetworkInterface ni = e.nextElement();
				// 网络接口信息
				netSb.append(ni.toString());
			}
			// 保留后两位
			machinePiece = netSb.toString().hashCode() << 16;
		} catch (Throwable e) {
			// 出问题随机生成,保留后两位
			machinePiece = (RandomUtil.randomInt()) << 16;
		}
		return machinePiece;
	}
	
	private static int getProcessPiece() {
		// 进程码
		// 因为静态变量类加载可能相同,所以要获取进程ID + 加载对象的ID值
		final int processPiece;
		// 进程ID初始化
		int processId;
		try {
			// 获取进程ID
			final String processName =ManagementFactory.getRuntimeMXBean().getName();
			final int atIndex = processName.indexOf('@');
			if (atIndex > 0) {
				processId = Integer.parseInt(processName.substring(0, atIndex));
			} else {
				processId = processName.hashCode();
			}
		} catch (Throwable t) {
			processId = RandomUtil.randomInt();
		}

		final ClassLoader loader = ClassLoaderUtil.getClassLoader();
		// 返回对象哈希码,无论是否重写hashCode方法
		int loaderId = (loader != null) ? System.identityHashCode(loader) : 0;

		// 进程ID + 对象加载ID
		// 保留前2位
		final String processSb = Integer.toHexString(processId) + Integer.toHexString(loaderId);
		processPiece = processSb.hashCode() & 0xFFFF;

		return processPiece;
	}

	@Test
	public void testId() throws InterruptedException {
		int machine = getMachinePiece();
		int process = getProcessPiece();
		System.out.println(machine+"--"+process);
		System.out.println((machine&31)+"--"+(process&31));
		Snowflake sf = IdUtil.createSnowflake(machine&31, process&31);
		Map<Long,String> ids = MapUtil.newConcurrentHashMap();
		int counter = 10;
		int threadNum = 2;
		TimeInterval timer = DateUtil.timer();
		
		ConcurrencyTester tester = ThreadUtil.concurrencyTest(threadNum, () -> {
		    // 测试的逻辑内容
			for (int i = 0; i < counter; i++) {
				ids.put(sf.nextId(), "");
				//String temp = IdUtil.randomUUID();
				//ids.add(IdUtil.randomUUID());
			}
		});

		// 获取总的执行时间，单位毫秒
		Console.log(tester.getInterval());


		// 获取总的执行时间，单位毫秒
		System.out.println(timer.interval() + " ms");
		System.out.println("SIZE：" + ids.size());
		System.out.println(ids);
		System.out.println(Long.MAX_VALUE);
	}

}
