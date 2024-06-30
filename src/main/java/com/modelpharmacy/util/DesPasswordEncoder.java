/*
package com.modelpharmacy.util;

import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class DesPasswordEncoder implements PasswordEncoder {

    private static final String ALGORITHM = "DES";

    private final SecretKey secretKey;

    public DesPasswordEncoder() {
        // Initialize DES secret key
        this.secretKey = generateSecretKey();
    }

    @Override
    public String encode(CharSequence rawPassword) {
        try {
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(rawPassword.toString().getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error encoding password with DES", e);
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        try {
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encodedPassword));
            String decryptedPassword = new String(decryptedBytes);
            return decryptedPassword.equals(rawPassword.toString());
        } catch (Exception e) {
            throw new RuntimeException("Error decoding password with DES", e);
        }
    }

    private SecretKey generateSecretKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
            return keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating DES secret key", e);
        }
    }

    public String decrypt(String encryptedPassword) {
        try {
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error while decrypting password", e);
        }
    }

    */
/*public static void main(String[] args) {
        DESPasswordEncoder encoder = new DESPasswordEncoder();

        // Encrypt password
        String encodedPassword = encoder.encode("mySecretPassword");
        System.out.println("Encoded Password: " + encodedPassword);

        // Verify password
        boolean matches = encoder.matches("mySecretPassword", encodedPassword);
        System.out.println("Password Matches: " + matches);
    }*//*

}
*/
