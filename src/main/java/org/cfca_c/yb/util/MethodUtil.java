package org.cfca_c.yb.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.filechooser.FileSystemView;

import org.apache.commons.lang.StringUtils;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.tools.classfile.Annotation.element_value;

public class MethodUtil{
	/**
	 * 初始化
	 * @return
	 */
	public static MethodUtil getInit(){
		return new MethodUtil();
	}
	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
	
	/**
	 *  MD5 加密  
	 * 
	 */
	public static String MD5(String origin) {
		String resultString = MD5Encode(origin,"UTF-8");
		if(StringUtils.isNotBlank(resultString)){
			return resultString.toUpperCase();
		}		
		return resultString;
	}
	
	
	/**
	 * MD5 比较 匹配origin 加密后是否等于md5
	 * @param origin 密码， 未加密
	 * @param md5 已加密字符串
	 * @return
	 */
	public static boolean ecompareMD5(String origin,String md5) {
		String result =MD5(origin);
		return md5.equals(result);
	}
	
	
/*
 *  MD5 加密  
 * */
	public static String MD5Encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname))
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes()));
			else
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes(charsetname)));
		} catch (Exception exception) {
		
		}
		return resultString;
	}

	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	
	/**
	 * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]
	 * hexStr2ByteArr(String strIn) 互为可逆的转换过程
	 * 
	 * @param arrB
	 *            需要转换的byte数组
	 * @return 转换后的字符串
	 * @throws Exception
	 *             本方法不处理任何异常，所有异常全部抛出
	 */
	public static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}
	
	/**
	 * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)
	 * 互为可逆的转换过程
	 * 
	 * @param strIn
	 *            需要转换的字符串
	 * @return 转换后的byte数组
	 * @throws Exception
	 *             本方法不处理任何异常，所有异常全部抛出
	 * @author <a href="mailto:leo841001@163.com">LiGuoQing</a >
	 */
	public static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;

		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}
	
	public static Boolean isInValidTime(long timestamp)  {
		System.out.println(System.currentTimeMillis());
		System.out.println("网络时间:"+getWebsiteDatetime("http://www.ntsc.ac.cn"));
		System.out.println(timestamp);
		long beijingTime;
		try {
			beijingTime = getTimestampByDate(getWebsiteDatetime("http://www.ntsc.ac.cn"));
			if(timestamp>beijingTime){
				return true;
			}else{
				return false;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static long getTimestampByDate(String datestring) throws ParseException{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d;

		d = df.parse(datestring);
		System.out.println(d.getTime());
		return d.getTime();

		
	}
	
	/**
     * 获取指定网站的日期时间
     * 
     * @param webUrl
     * @return
     * @author SHANHY
     * @date   2015年11月27日
     */
    private static String getWebsiteDatetime(String webUrl){
        try {
            URL url = new URL(webUrl);// 取得资源对象
            URLConnection uc = url.openConnection();// 生成连接对象
            uc.connect();// 发出连接
            long ld = uc.getDate();// 读取网站日期时间
            Date date = new Date(ld);// 转换为标准时间对象
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);// 输出北京时间
            return sdf.format(date);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public static long getTimestampByDate(Date date){
		return date.getTime();
	}
	
	public static void main(String args[]){
		System.out.println("网络时间:"+getWebsiteDatetime("http://www.ntsc.ac.cn"));
		System.out.println(Utils3DES.DecryptString("MfqeUcAC6OJT8pDnj+mFPw=="));
		/*DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		try {
			d = df.parse("2016-12-28");
			System.out.println(d.getTime());

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
}