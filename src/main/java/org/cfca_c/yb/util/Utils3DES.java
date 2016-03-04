package org.cfca_c.yb.util;

import java.io.IOException; 
import java.security.Key; 
import javax.crypto.Cipher; 
import javax.crypto.SecretKeyFactory; 
import javax.crypto.spec.DESedeKeySpec; 
import javax.crypto.spec.IvParameterSpec; 
import sun.misc.BASE64Decoder; 
import sun.misc.BASE64Encoder; 
 
public class Utils3DES { 
 
  private static final byte[] key = { 0x11, 0x22, 0x4F, 0x58, (byte) 0x83, 0x10, 0x21, 0x38, 0x21, 
0x25, 0x79, 0x51, (byte) 0xCB, (byte) 0xDD, 0x55, 0x66, 0x77, 0x29, 0x79, (byte) 0x98, 0x21, 0x54, 
0x36, (byte) 0xE2 }; 
  
  private static final byte[] keyiv = { 0x1A, 0x2B, 0x3C, 0x4D, 0x5E, 0x6F, 0x11, (byte) 0x99 }; 
 
  /** 
    * 加密 
    *   
    * @param encryptString 
    * @return 
    */ 
  public static String EncryptString(String encryptString) { 
   try { 
 
     byte[] data = encryptString.getBytes("UTF8"); 
      Key deskey = null;
      DESedeKeySpec spec = new DESedeKeySpec(key); 
      SecretKeyFactory keyfactory = SecretKeyFactory 
         .getInstance("desede"); 
      deskey = keyfactory.generateSecret(spec); 
      Cipher cipher = Cipher.getInstance("desede" + "/CBC/PKCS5Padding"); 
      IvParameterSpec ips = new IvParameterSpec(keyiv); 
      cipher.init(Cipher.ENCRYPT_MODE, deskey, ips); 
     byte[] bOut = cipher.doFinal(data); 
      BASE64Encoder base64en = new BASE64Encoder(); 
     return base64en.encode(bOut); 
    } catch (Exception e) { 
     // TODO Auto-generated catch block 
     return null; 
    } 
  } 
  /** 
   * 解密 
   *   
   * @param decryptString 
   * @return 
   */ 
 public static String DecryptString(String decryptString) { 
  try { 
    byte[] data = new BASE64Decoder().decodeBuffer(decryptString); 
     Key deskey = null; 
     String result = ""; 
     DESedeKeySpec spec = new DESedeKeySpec(key); 
     SecretKeyFactory keyfactory = SecretKeyFactory 
        .getInstance("desede"); 
     deskey = keyfactory.generateSecret(spec); 
     Cipher cipher = Cipher.getInstance("desede" + "/CBC/PKCS5Padding"); 
     IvParameterSpec ips = new IvParameterSpec(keyiv); 
     cipher.init(Cipher.DECRYPT_MODE, deskey, ips); 
    byte[] bOut = cipher.doFinal(data); 
     result = new String(bOut, "UTF8"); 
    return result; 
   } catch (Exception e) { 
    // TODO Auto-generated catch block 
    return null; 
   } 
 } 

 public static void main(String[] args) throws IOException { 
	 String strEnc = EncryptString("<?xml version='1.0' encoding='utf-8' ?><MoviePlayDetail><UserInfo><UserName name='用户代码'>31011510000001</UserName><UserPassword name='密码'>Password</UserPassword><UserPIN name='唯一识别码'>BC591D2E-E416-4F55-9FC6-F120CB39D79A</UserPIN></UserInfo><MovieInfo><NO name='序号：当日日期（六位）+当日播放顺序号（四位）'>1504221221</NO><MovieName name='影片名称'>乙方丙方</MovieName><MoviePlayStartTime name='播放开始时间'>2015-04-22 11:01:45</MoviePlayStartTime><MoviePlayEndTime name='播放结束时间'>2015-04-22 13:02:45</MoviePlayEndTime></MovieInfo></MoviePlayDetail>"); 
			     System.out.println("密文：" + strEnc); 
			  
			     String strDes = DecryptString(strEnc);// 把String 类型的密文解密 
			  
			     System.out.println("原文：" + strDes); 
			   }
 }