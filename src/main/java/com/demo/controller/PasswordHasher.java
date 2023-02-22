package com.demo.controller;

import jakarta.servlet.http.HttpServlet;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Base64.Encoder;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordHasher extends HttpServlet {
    public static final int ITERATIONS = 10000;
    public static final int KEY_LENGTH = 256;

    public static String hashPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();

        PBEKeySpec spec = new PBEKeySpec(chars, salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return ITERATIONS + ":" + toBase64(salt) + ":" + toBase64(hash);
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    private static String toBase64(byte[] array) {
        Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(array);
    }
}
