/**
 * 
 */
package com.sshtg.util;

import java.io.IOException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * ���ܽ��ܹ�����
 * 
 * @since 2014-11-14
 * @author Tany
 *
 */
public final class CryptUtils {
	private static String Algorithm = "DES"; 
    private static byte[] DEFAULT_KEY=new byte[] {-53, 122, -42, -88, -110, -123, -60, -74}; 
    private static String VALUE_ENCODING="UTF-8"; 
     
    /**
     * ������Կ
     * 
     * @return byte[] �������ɵ���Կ
     * @throws exception
     *             �ӳ��쳣.
     */ 
    public static byte[] getSecretKey() throws Exception { 
        KeyGenerator keygen = KeyGenerator.getInstance(Algorithm); 
        SecretKey deskey = keygen.generateKey(); 
        // if (debug ) System.out.println ("������Կ:"+byte2hex (deskey.getEncoded()));  
        return deskey.getEncoded(); 
    } 
 
    /**
     * ��ָ�������ݸ����ṩ����Կ���м���
     * 
     * @param input
     *            ��Ҫ���ܵ�����
     * @param key
     *            ��Կ
     * @return byte[] ���ܺ������
     * @throws Exception
     */ 
    public static byte[] encryptData(byte[] input, byte[] key) throws Exception { 
        SecretKey deskey = new javax.crypto.spec.SecretKeySpec(key, Algorithm); 
        // if (debug )  
        // {  
        // System.out.println ("����ǰ�Ķ�����:"+byte2hex (input ));  
        // System.out.println ("����ǰ���ַ���:"+new String (input ));  
        //  
        // }  
        Cipher c1 = Cipher.getInstance(Algorithm); 
        c1.init(Cipher.ENCRYPT_MODE, deskey); 
        byte[] cipherByte = c1.doFinal(input); 
        // if (debug ) System.out.println ("���ܺ�Ķ�����:"+byte2hex (cipherByte ));  
        return cipherByte; 
 
    } 
     
    public static byte[] encryptData(byte[] input) throws Exception { 
        return encryptData(input, DEFAULT_KEY); 
    } 
 
    /**
     * ���������Ѽ��ܵ�����ͨ��ָ������Կ���н���
     * 
     * @param input
     *            �����ܵ�����
     * @param key
     *            ��Կ
     * @return byte[] ���ܺ������
     * @throws Exception
     */ 
    public static byte[] decryptData(byte[] input, byte[] key) throws Exception { 
        SecretKey deskey = new javax.crypto.spec.SecretKeySpec(key, Algorithm); 
        // if (debug ) System.out.println ("����ǰ����Ϣ:"+byte2hex (input ));  
        Cipher c1 = Cipher.getInstance(Algorithm); 
        c1.init(Cipher.DECRYPT_MODE, deskey); 
        byte[] clearByte = c1.doFinal(input); 
        // if (debug )  
        // {  
        // System.out.println ("���ܺ�Ķ�����:"+byte2hex (clearByte ));  
        // System.out.println ("���ܺ���ַ���:"+(new String (clearByte )));  
        //  
        // }  
        return clearByte; 
 
    } 
     
    public static byte[] decryptData(byte[] input) throws Exception { 
        return decryptData(input, DEFAULT_KEY); 
    } 
 
    /**
     * �ֽ���ת����16�����ַ���
     * 
     * @param byte[] b ����Ҫת�����ֽ���
     * @return String ����ת�����16�����ַ���
     */ 
    public static String byte2hex(byte[] bytes) { 
        StringBuilder hs = new StringBuilder(); 
        for(byte b : bytes) 
            hs.append(String.format("%1$02X", b)); 
        return hs.toString(); 
    } 
 
    public static byte[] hex2byte(String content) { 
        int l=content.length()>>1; 
        byte[] result=new byte[l]; 
        for(int i=0;i<l;i++) { 
            int j=i<<1; 
            String s=content.substring(j, j+2); 
            result[i]=Integer.valueOf(s, 16).byteValue(); 
        } 
        return result; 
    } 
     
    /**
     * ���ֽ�����ת��Ϊbase64�����ַ���
     * @param buffer
     * @return
     */ 
    public static String bytesToBase64(byte[] buffer) { 
        //BASE64Encoder en=new BASE64Encoder();  
        return Base64.encode(buffer); 
//      return encoder.encode(buffer);  
    } 
     
    /**
     * ��base64������ַ�������Ϊ�ֽ�����
     * @param value
     * @return
     * @throws IOException 
     */ 
    public static byte[] base64ToBytes(String value) throws IOException { 
        //return Base64.decodeToByteArray(value);  
//      System.out.println(decoder.decodeBuffer(value));  
//      return decoder.decodeBuffer(value);  
        return Base64.decode(value); 
    } 
     
    /**
     * ���ܸ������ַ���
     * @param value
     * @return ���ܺ��base64�ַ���
     */ 
    public static String encryptString(String value) { 
        return encryptString(value, DEFAULT_KEY); 
    } 
     
    /**
     * ���ݸ�������Կ�����ַ���
     * @param value �����ܵ��ַ���
     * @param key ��BASE64��ʽ���ڵ���Կ
     * @return ���ܺ��base64�ַ���
     * @throws IOException 
     */ 
    public static String encryptString(String value, String key) throws IOException { 
        return encryptString(value, base64ToBytes(key)); 
    } 
     
    /**
     * ���ݸ�������Կ�����ַ���
     * @param value �����ܵ��ַ���
     * @param key �ֽ�������ʽ����Կ
     * @return ���ܺ��base64�ַ���
     */ 
    public static String encryptString(String value, byte[] key) { 
        try { 
            byte[] data=value.getBytes(VALUE_ENCODING); 
            data=CryptUtils.encryptData(data, key); 
            return bytesToBase64(data); 
        } catch (Exception e) { 
            // TODO Auto-generated catch block  
            e.printStackTrace(); 
            return null; 
        } 
    } 
     
    /**
     * �����ַ���
     * @param value base64��ʽ���ڵ�����
     * @return ����
     */ 
    public static String decryptString(String value) { 
        return decryptString(value, DEFAULT_KEY); 
    } 
     
    /**
     * �����ַ���
     * @param value base64��ʽ���ڵ�����
     * @param key base64��ʽ���ڵ���Կ
     * @return ����
     * @throws IOException 
     */ 
    public static String decryptString(String value, String key) throws IOException { 
        String s=decryptString(value, base64ToBytes(key)); 
        return s;  
    } 
     
    /**
     * �����ַ���
     * @param value base64��ʽ���ڵ�����
     * @param key �ֽ�������ʽ���ڵ���Կ
     * @return ����
     */ 
    public static String decryptString(String value, byte[] key) { 
        try { 
            byte[] data=base64ToBytes(value); 
            data=CryptUtils.decryptData(data, key); 
            return new String(data, VALUE_ENCODING); 
        }catch(Exception e) { 
            e.printStackTrace(); 
            return null; 
        } 
    } 



/**
  * ������Կ
  *
  * @return byte[] �������ɵ���Կ
  * @throws exception
  *             �ӳ��쳣.
  */
// if (debug ) System.out.println ("������Կ:"+byte2hex (deskey.getEncoded
// ()));
/**
  * ��ָ�������ݸ����ṩ����Կ���м���
  *
  * @param input
  *            ��Ҫ���ܵ�����
  * @param key
  *            ��Կ
  * @return byte[] ���ܺ������
  * @throws Exception
  */
// if (debug )
// {
// System.out.println ("����ǰ�Ķ�����:"+byte2hex (input ));
// System.out.println ("����ǰ���ַ���:"+new String (input ));
//
// }
// if (debug ) System.out.println ("���ܺ�Ķ�����:"+byte2hex (cipherByte ));
/**
  * ���������Ѽ��ܵ�����ͨ��ָ������Կ���н���
  *
  * @param input
  *            �����ܵ�����
  * @param key
  *            ��Կ
  * @return byte[] ���ܺ������
  * @throws Exception
  */
// if (debug ) System.out.println ("����ǰ����Ϣ:"+byte2hex (input ));
// if (debug )
// {
// System.out.println ("���ܺ�Ķ�����:"+byte2hex (clearByte ));
// System.out.println ("���ܺ���ַ���:"+(new String (clearByte )));
//
// }
/**
  * �ֽ���ת����16�����ַ���
  *
  * @param byte[] b ����Ҫת�����ֽ���
  * @return String ����ת�����16�����ַ���
  */
/**
  * ���ֽ�����ת��Ϊbase64�����ַ���
  * @param buffer
  * @return
  */
//BASE64Encoder en=new BASE64Encoder();
//  return encoder.encode(buffer);
/**
  * ��base64������ַ�������Ϊ�ֽ�����
  * @param value
  * @return
  * @throws IOException
  */
//return Base64.decodeToByteArray(value);
//  System.out.println(decoder.decodeBuffer(value));
//  return decoder.decodeBuffer(value);
/**
  * ���ܸ������ַ���
  * @param value
  * @return ���ܺ��base64�ַ���
  */
/**
  * ���ݸ�������Կ�����ַ���
  * @param value �����ܵ��ַ���
  * @param key ��BASE64��ʽ���ڵ���Կ
  * @return ���ܺ��base64�ַ���
  * @throws IOException
  */
/**
  * ���ݸ�������Կ�����ַ���
  * @param value �����ܵ��ַ���
  * @param key �ֽ�������ʽ����Կ
  * @return ���ܺ��base64�ַ���
  */
// TODO Auto-generated catch block
/**
  * �����ַ���
  * @param value base64��ʽ���ڵ�����
  * @return ����
  */
/**
  * �����ַ���
  * @param value base64��ʽ���ڵ�����
  * @param key base64��ʽ���ڵ���Կ
  * @return ����
  * @throws IOException
  */
/**
  * �����ַ���
  * @param value base64��ʽ���ڵ�����
  * @param key �ֽ�������ʽ���ڵ���Կ
  * @return ����
  */
//����  
//����  

//import javax.crypto.Cipher;
//import javax.crypto.KeyGenerator;
//import javax.crypto.SecretKey;
//
//import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
//
//
//public class CryptUtils {
// private static String Algorithm = "DES";
// private static byte[] DEFAULT_KEY=new byte[] {-53, 122, -42, -88, -110, -123, -60, -74};
// private static String VALUE_ENCODING="UTF-8";
//
// 
// /**
//  * ������Կ
//  *
//  * @return byte[] �������ɵ���Կ
//  * @throws exception
//  *             �ӳ��쳣.
//  */
// public static byte[] getSecretKey() throws Exception {
//  KeyGenerator keygen = KeyGenerator.getInstance(Algorithm);
//  SecretKey deskey = keygen.generateKey();
//  // if (debug ) System.out.println ("������Կ:"+byte2hex (deskey.getEncoded
//  // ()));
//  return deskey.getEncoded();
//
// }
//
// /**
//  * ��ָ�������ݸ����ṩ����Կ���м���
//  *
//  * @param input
//  *            ��Ҫ���ܵ�����
//  * @param key
//  *            ��Կ
//  * @return byte[] ���ܺ������
//  * @throws Exception
//  */
// public static byte[] encryptData(byte[] input, byte[] key) throws Exception {
//  SecretKey deskey = new javax.crypto.spec.SecretKeySpec(key, Algorithm);
//  // if (debug )
//  // {
//  // System.out.println ("����ǰ�Ķ�����:"+byte2hex (input ));
//  // System.out.println ("����ǰ���ַ���:"+new String (input ));
//  //
//  // }
//  Cipher c1 = Cipher.getInstance(Algorithm);
//  c1.init(Cipher.ENCRYPT_MODE, deskey);
//  byte[] cipherByte = c1.doFinal(input);
//  // if (debug ) System.out.println ("���ܺ�Ķ�����:"+byte2hex (cipherByte ));
//  return cipherByte;
//
// }
// 
// public static byte[] encryptData(byte[] input) throws Exception {
//  return encryptData(input, DEFAULT_KEY);
// }
//
// /**
//  * ���������Ѽ��ܵ�����ͨ��ָ������Կ���н���
//  *
//  * @param input
//  *            �����ܵ�����
//  * @param key
//  *            ��Կ
//  * @return byte[] ���ܺ������
//  * @throws Exception
//  */
// public static byte[] decryptData(byte[] input, byte[] key) throws Exception {
//  SecretKey deskey = new javax.crypto.spec.SecretKeySpec(key, Algorithm);
//  // if (debug ) System.out.println ("����ǰ����Ϣ:"+byte2hex (input ));
//  Cipher c1 = Cipher.getInstance(Algorithm);
//  c1.init(Cipher.DECRYPT_MODE, deskey);
//  byte[] clearByte = c1.doFinal(input);
//  // if (debug )
//  // {
//  // System.out.println ("���ܺ�Ķ�����:"+byte2hex (clearByte ));
//  // System.out.println ("���ܺ���ַ���:"+(new String (clearByte )));
//  //
//  // }
//  return clearByte;
//
// }
// 
// public static byte[] decryptData(byte[] input) throws Exception {
//  return decryptData(input, DEFAULT_KEY);
// }
//
// /**
//  * �ֽ���ת����16�����ַ���
//  *
//  * @param byte[] b ����Ҫת�����ֽ���
//  * @return String ����ת�����16�����ַ���
//  */
// public static String byte2hex(byte[] bytes) {
//  StringBuilder hs = new StringBuilder();
//  for(byte b : bytes)
//   hs.append(String.format("%1$02X", b));
//  return hs.toString();
// }
//
// public static byte[] hex2byte(String content) {
//  int l=content.length()>>1;
//  byte[] result=new byte[l];
//  for(int i=0;i<l;i++) {
//   int j=i<<1;
//   String s=content.substring(j, j+2);
//   result[i]=Integer.valueOf(s, 16).byteValue();
//  }
//  return result;
// }
// 
// /**
//  * ���ֽ�����ת��Ϊbase64�����ַ���
//  * @param buffer
//  * @return
//  */
// public static String bytesToBase64(byte[] buffer) {
//  //BASE64Encoder en=new BASE64Encoder();
//  return Base64.encode(buffer);
////  return encoder.encode(buffer);
// }
// 
// /**
//  * ��base64������ַ�������Ϊ�ֽ�����
//  * @param value
//  * @return
//  * @throws IOException
//  */
// public static byte[] base64ToBytes(String value) throws IOException {
//  //return Base64.decodeToByteArray(value);
////  System.out.println(decoder.decodeBuffer(value));
////  return decoder.decodeBuffer(value);
//  return Base64.decode(value);
// }
// 
// /**
//  * ���ܸ������ַ���
//  * @param value
//  * @return ���ܺ��base64�ַ���
//  */
// public static String encryptString(String value) {
//  return encryptString(value, DEFAULT_KEY);
// }
// 
// /**
//  * ���ݸ�������Կ�����ַ���
//  * @param value �����ܵ��ַ���
//  * @param key ��BASE64��ʽ���ڵ���Կ
//  * @return ���ܺ��base64�ַ���
//  * @throws IOException
//  */
// public static String encryptString(String value, String key) throws IOException {
//  return encryptString(value, base64ToBytes(key));
// }
// 
// /**
//  * ���ݸ�������Կ�����ַ���
//  * @param value �����ܵ��ַ���
//  * @param key �ֽ�������ʽ����Կ
//  * @return ���ܺ��base64�ַ���
//  */
// public static String encryptString(String value, byte[] key) {
//  try {
//   byte[] data=value.getBytes(VALUE_ENCODING);
//   data=CryptUtils.encryptData(data, key);
//   return bytesToBase64(data);
//  } catch (Exception e) {
//   // TODO Auto-generated catch block
//   e.printStackTrace();
//   return null;
//  }
// }
// 
// /**
//  * �����ַ���
//  * @param value base64��ʽ���ڵ�����
//  * @return ����
//  */
// public static String decryptString(String value) {
//  return decryptString(value, DEFAULT_KEY);
// }
// 
// /**
//  * �����ַ���
//  * @param value base64��ʽ���ڵ�����
//  * @param key base64��ʽ���ڵ���Կ
//  * @return ����
//  * @throws IOException
//  */
// public static String decryptString(String value, String key) throws IOException {
//  String s=decryptString(value, base64ToBytes(key));
//  return s;
// }
// 
// /**
//  * �����ַ���
//  * @param value base64��ʽ���ڵ�����
//  * @param key �ֽ�������ʽ���ڵ���Կ
//  * @return ����
//  */
// public static String decryptString(String value, byte[] key) {
//  try {
//   byte[] data=base64ToBytes(value);
//   data=CryptUtils.decryptData(data, key);
//   return new String(data, VALUE_ENCODING);
//  }catch(Exception e) {
//   e.printStackTrace();
//   return null;
//  }
// }
//}
// 
//
//CryptHelper.java
//
//[java]
//package com.gdie.lab.crypt; 
// 
//import javax.crypto.Cipher; 
//import javax.crypto.SecretKey; 
//import javax.crypto.SecretKeyFactory; 
//import javax.crypto.spec.DESKeySpec; 
//import javax.crypto.spec.IvParameterSpec; 
// 
//import org.springframework.util.DigestUtils; 
// 
//public class CryptHelper{ 
//     
//    private static String CRYPT_KEY = "zhongqian"; 
//     
//    //����  
//    private static Cipher ecip; 
//    //����  
//    private static Cipher dcip; 
//     
//    static { 
//        try { 
//            String KEY = DigestUtils.md5DigestAsHex(CRYPT_KEY.getBytes()).toUpperCase(); 
//            KEY = KEY.substring(0, 8); 
//            byte[] bytes = KEY.getBytes(); 
//            DESKeySpec ks = new DESKeySpec(bytes);  
//            SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");  
//            SecretKey sk = skf.generateSecret(ks);  
//            IvParameterSpec iv2 = new IvParameterSpec(bytes);  
// 
//            ecip = Cipher.getInstance("DES/CBC/PKCS5Padding"); 
//            ecip.init(Cipher.ENCRYPT_MODE, sk, iv2); 
// 
//            dcip = Cipher.getInstance("DES/CBC/PKCS5Padding"); 
//            dcip.init(Cipher.DECRYPT_MODE, sk, iv2); 
//        }catch(Exception ex) { 
//            ex.printStackTrace(); 
//        } 
//    } 
//     
//    public static String encrypt(String content) throws Exception { 
//        byte[] bytes = ecip.doFinal(content.getBytes("ascii")); 
//        return CryptUtils.byte2hex(bytes); 
//    } 
//     
//    public static String decrypt(String content) throws Exception { 
//        byte[] bytes  = CryptUtils.hex2byte(content); 
//        bytes = dcip.doFinal(bytes); 
//        return new String(bytes, "ascii"); 
//    } 
//    //test  
//    public static void main(String[] args) throws Exception { 
//        String password = "gly"; 
//        String en = encrypt(password); 
//        System.out.println(en); 
//        System.out.println(decrypt(en)); 
//         
//    } 
//} 
//
//package com.gdie.lab.crypt;
//
//import javax.crypto.Cipher;
//import javax.crypto.SecretKey;
//import javax.crypto.SecretKeyFactory;
//import javax.crypto.spec.DESKeySpec;
//import javax.crypto.spec.IvParameterSpec;
//
//import org.springframework.util.DigestUtils;
//
//public class CryptHelper{
// 
// private static String CRYPT_KEY = "zhongqian";
// 
// //����
// private static Cipher ecip;
// //����
// private static Cipher dcip;
// 
// static {
//  try {
//   String KEY = DigestUtils.md5DigestAsHex(CRYPT_KEY.getBytes()).toUpperCase();
//   KEY = KEY.substring(0, 8);
//   byte[] bytes = KEY.getBytes();
//   DESKeySpec ks = new DESKeySpec(bytes);
//   SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
//   SecretKey sk = skf.generateSecret(ks);
//   IvParameterSpec iv2 = new IvParameterSpec(bytes);
//
//   ecip = Cipher.getInstance("DES/CBC/PKCS5Padding");
//   ecip.init(Cipher.ENCRYPT_MODE, sk, iv2);
//
//   dcip = Cipher.getInstance("DES/CBC/PKCS5Padding");
//   dcip.init(Cipher.DECRYPT_MODE, sk, iv2);
//  }catch(Exception ex) {
//   ex.printStackTrace();
//  }
// }
// 
// public static String encrypt(String content) throws Exception {
//  byte[] bytes = ecip.doFinal(content.getBytes("ascii"));
//  return CryptUtils.byte2hex(bytes);
// }
// 
// public static String decrypt(String content) throws Exception {
//  byte[] bytes  = CryptUtils.hex2byte(content);
//  bytes = dcip.doFinal(bytes);
//  return new String(bytes, "ascii");
// }
// //test
// public static void main(String[] args) throws Exception {
//  String password = "gly";
//  String en = encrypt(password);
//  System.out.println(en);
//  System.out.println(decrypt(en));
//  
// }
//}
}
