package com.atguigu.boot.test;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.tron.common.crypto.Sha256Sm3Hash;
import org.tron.common.utils.Base58;

import java.util.Arrays;

/**
 * @author liukai
 * @since 2022/03/02.
 */
public class Base58CheckTest {

    private static byte[] decode58Check(String input) {
        byte[] decodeCheck = Base58.decode(input);
        if (decodeCheck.length <= 4) {
            return null;
        }
        byte[] decodeData = new byte[decodeCheck.length - 4];
        System.arraycopy(decodeCheck, 0, decodeData, 0, decodeData.length);
        byte[] hash0 = Sha256Sm3Hash.hash(decodeData);
        byte[] hash1 = Sha256Sm3Hash.hash(hash0);
        if (hash1[0] == decodeCheck[decodeData.length]
                && hash1[1] == decodeCheck[decodeData.length + 1]
                && hash1[2] == decodeCheck[decodeData.length + 2]
                && hash1[3] == decodeCheck[decodeData.length + 3]) {
            return decodeData;
        }
        return null;
    }

    public static String encodeUsingApacheCommons(byte[] bytes)
            throws DecoderException {
        return Hex.encodeHexString(bytes);
    }

    public static byte[] decodeUsingApacheCommons(String hexString)
            throws DecoderException {
        return Hex.decodeHex(hexString.toCharArray());
    }

    /**
     * {
     * "address": "TJfTWgzey3d3QHbrautjRmANNULfk91VFj",
     * "hexAddress": "415f5dc2006f98936ca054ea49b4d38d292c9429c8"
     * }
     */
    public static void main(String[] args) throws Exception {
        String bash58CheckStr = "TJfTWgzey3d3QHbrautjRmANNULfk91VFj";
        byte[] bytes = decode58Check(bash58CheckStr);
        System.out.println("---------");
        System.out.println(bash58CheckStr);
        System.out.println(Arrays.toString(bytes));
        System.out.println(encodeUsingApacheCommons(bytes));
        System.out.println("---------");

    }
}