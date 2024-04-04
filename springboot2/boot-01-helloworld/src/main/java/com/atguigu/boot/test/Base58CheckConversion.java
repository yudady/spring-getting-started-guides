package com.atguigu.boot.test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Base58CheckConversion {

    private static final String ALPHABET = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";

    public static BigInteger toBigInteger(String value, int base) {
        if (base < 1 || base > 16) {
            throw new IllegalArgumentException("Base must be between 1 and 16.");
        }
        return new BigInteger(value, base);
    }

    public static String convertToBase58(String hexString) {
        BigInteger address = toBigInteger(hexString, 16);

        // Add version byte (0x00 in your case)
        address = address.shiftLeft(8).add(BigInteger.ZERO);

        // Calculate checksum
        byte[] hash0 = sha256(address.toByteArray());
        byte[] hash1 = sha256(hash0);
        byte[] checksum = Arrays.copyOfRange(hash1, 0, 4);

        // Append checksum
        byte[] addressBytes = Arrays.copyOf(hash0, hash0.length + 4);
        System.arraycopy(checksum, 0, addressBytes, hash0.length, 4);

        // Convert to base58
        StringBuilder sb = new StringBuilder();
        while (address.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] divMod = address.divideAndRemainder(BigInteger.valueOf(58));
            sb.append(ALPHABET.charAt(divMod[1].intValue()));
            address = divMod[0];
        }

        // Add leading '1's for leading zero bytes
        for (byte b : addressBytes) {
            if (b == 0) {
                sb.append(ALPHABET.charAt(0));
            } else {
                break;
            }
        }

        return sb.reverse().toString();
    }

    private static byte[] sha256(byte[] input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(input);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found.", e);
        }
    }

    public static void main(String[] args) {
        String hexString = "TNNqZuYhMfQvooC4kJwTsMJEQVU3vWGa5u";
        String base58String = convertToBase58(hexString);
        System.out.println("Base58Check: " + base58String);
    }
}
