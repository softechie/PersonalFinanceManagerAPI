package com.javaj2eefsd.workshop.util;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.javaj2eefsd.workshop.api.ApiException;
import com.javaj2eefsd.workshop.model.User;
import com.javaj2eefsd.workshop.util.PFMConstants;
import com.javaj2eefsd.workshop.util.PasswordHasher;
import com.javaj2eefsd.workshop.dao.BankAccountDaoImpl;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;
public class AESEncryption {
	
	 static final String ALGO = "AES";
	    public static final byte[] keyValue =
	            new byte[]{'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y'};
	    
	public static String encrypt(String accountNumber) throws Exception {
		
		  Key key = generateKey();	       
		byte[] plainTextByte = accountNumber.toString().getBytes();

		Cipher c = Cipher.getInstance(ALGO);
		
		c.init(Cipher.ENCRYPT_MODE, key);
		
		byte[] encryptedByte = c.doFinal(plainTextByte);

		Base64.Encoder encoder = Base64.getEncoder();

		String encryptedText = encoder.encodeToString(encryptedByte);

		System.out.println("encoded text:" + encryptedText);

		return (encryptedText);
	}		
	public static String decrypt(String encryptedText) throws Exception {	
        Key key = generateKey();
        
        Cipher c = Cipher.getInstance(ALGO);
        
        c.init(Cipher.DECRYPT_MODE, key);
        
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedText);
        
        byte[] decValue = c.doFinal(decordedValue);
        
       // System.out.print(decValue);
        return new String(decValue);
    }	
	 private static Key generateKey() throws Exception {
		 
		 return new SecretKeySpec(keyValue,ALGO);
	    }
	
	  //return new SecretKeySpec(Arrays.copyOf(keyValue, 16),ALGO);
		/*
		 * public static String encrypt(String accountNumber, SecretKey key) throws
		 * Exception {
		 * 
		 * 
		 * byte[] plainTextByte = accountNumber.toString().getBytes();
		 * 
		 * Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		 * 
		 * cipher.init(Cipher.ENCRYPT_MODE, key);
		 * 
		 * byte[] encryptedByte = cipher.doFinal(plainTextByte);
		 * 
		 * Base64.Encoder encoder = Base64.getEncoder();
		 * 
		 * String encryptedText = encoder.encodeToString(encryptedByte);
		 * 
		 * System.out.println("encoded text:" + encryptedText);
		 * 
		 * return (encryptedText);
		 * 
		 * }
		 */

	/*
	 * public static final String key = "aesEncryptionKey";
	 *  public static final
	 * String initVector = "encryptionIntVec"; 
	 * public static String encrypt(String accountNumber) throws Exception {
	 * IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
	 * 
	 * SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"),"AES");
	 * 
	 * byte[] plain = accountNumber.toString().getBytes();
	 * 
	 * Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	 * 
	 * cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
	 * 
	 * byte[] cipherText = cipher.doFinal(plain);
	 * 
	 * Base64.Encoder encoder = Base64.getEncoder();
	 * 
	 * return encoder.encodeToString(cipherText);
	 * 
	 * }
	 */

	/*
	 * public static String decrypt(String encryptedText, SecretKey secretKey)
	 * throws Exception { Base64.Decoder decoder = Base64.getDecoder();
	 * 
	 * byte[] encryptedTextByte = decoder.decode(encryptedText);
	 * 
	 * Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	 * 
	 * cipher.init(Cipher.DECRYPT_MODE, secretKey);
	 * 
	 * byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
	 * 
	 * String decryptedText = new String(decryptedByte);
	 * 
	 * return decryptedText; }
	 */

	/*public static String decrypt(String encryptedText, Key key) throws Exception {
		//Key key2 = StoreKey.generateKey();
		
   
		Base64.Decoder decoder = Base64.getDecoder();

		byte[] encryptedTextByte = Arrays.toString(Base64.getDecoder().decode(encryptedText)).getBytes();
		//byte[] encryptedTextByte = decoder.decode(encryptedText);

		Cipher c = Cipher.getInstance(StoreKey.ALGO);

		c.init(Cipher.DECRYPT_MODE, key);

		byte[] decryptedByte = c.doFinal(encryptedTextByte);

		String decValue = new String((decryptedByte),"UTF-8");
				
		return new String(decValue);
	
	
     }*/
	
	
	/*
	 * public static String decrypt(String encryptedText) throws Exception {
	 * IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
	 * SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
	 * 
	 * Base64.Decoder decoder = Base64.getDecoder();
	 * 
	 * byte[] encryptedTextByte = decoder.decode(encryptedText);
	 * 
	 * Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	 * 
	 * cipher.init(Cipher.DECRYPT_MODE,skeySpec, iv);
	 * 
	 * byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
	 * 
	 * String decryptedText = new String(decryptedByte);
	 * 
	 * return decryptedText; }
	 */
	
	
}
