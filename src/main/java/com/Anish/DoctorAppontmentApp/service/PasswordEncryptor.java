package com.Anish.DoctorAppontmentApp.service;

import jakarta.xml.bind.DatatypeConverter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryptor {

    public static String encryptor(String unHashedPassword) throws NoSuchAlgorithmException {
        MessageDigest md5=MessageDigest.getInstance("MD5");
        md5.update(unHashedPassword.getBytes());
        byte[]digested= md5.digest();;
        return DatatypeConverter.printHexBinary(digested);
    }
}
