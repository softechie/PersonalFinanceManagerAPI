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
	
		
		 public static String encrypt(String accountNumber, SecretKey key) throws Exception {
		 
		  byte[] plainTextByte = accountNumber.toString().getBytes();
		  
		  Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		  
		  cipher.init(Cipher.ENCRYPT_MODE, key);
		 
		  byte[] encryptedByte = cipher.doFinal(plainTextByte);
		  
		  Base64.Encoder encoder = Base64.getEncoder();
		  
		  String encryptedText = encoder.encodeToString(encryptedByte);
		  
		  System.out.println("encoded text:" + encryptedText);
		  
		  return (encryptedText);
		  
		  }
		 

}
