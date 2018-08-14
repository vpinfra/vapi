package com.vpinfra.core.utils;

import com.vpinfra.core.common.Constant;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

/**
 * 加密算法通用工具
 *
 * @author 尹俊峰
 * @date 2016年8月22日
 * @since 2.1.1
 */
public final class CryptUtil {
    
    private static final LogUtil logger = LogUtil.newInstance(CryptUtil.class);

    /**
     * 算法名称
     */
    private static final String ALGORITHOM_RSA = "RSA";

    private static final String ALGORITHOM_MD5 = "MD5";

    private static final String ALGORITHOM_SHA5 = "SHA5";

    private static final String ALGORITHOM_SHA1 = "SHA-1";

    /**
     * 密钥大小
     */
    private static final int KEY_SIZE = 1024;

    private static final int DEFAULT_STR_RADIX = 16;

    private CryptUtil() {
    }

    /**
     * 获取目标字符串 MD5 hash 的 byte 数组.
     *
     * @param src 目标字符串
     * @return 目标字符串的 MD5 hash 的 byte 数组
     * @throws NoSuchAlgorithmException
     */
    public static byte[] md5(final String src) throws NoSuchAlgorithmException {
        return hash(src, ALGORITHOM_MD5);
    }

    /**
     * 获取目标字符串 MD5 的 hash 字符串.
     *
     * @param src 目标字符串
     * @return 目标字符串的 MD5 的 hash 字符串
     * @throws NoSuchAlgorithmException
     */
    public static String md5HexStr(final String src) throws NoSuchAlgorithmException {
        return hashHexStr(src, ALGORITHOM_MD5);
    }

    /**
     * 获取目标字符串的 sha5 加密 byte 数组.
     *
     * @param src 目标字符串
     * @return 目标字符串的 sha5 加密 byte 数组
     * @throws NoSuchAlgorithmException
     */
    public static byte[] sha5(final String src) throws NoSuchAlgorithmException {
        return hash(src, ALGORITHOM_SHA5);
    }

    /**
     * 获取目标字符串的 SHA-1 加密字符串.
     *
     * @param decript 目标字符串
     * @return 目标字符串的 SHA-1 加密字符串
     * @throws NoSuchAlgorithmException
     */
    public static String sha1HexStr(String decript) throws NoSuchAlgorithmException {
        String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance(ALGORITHOM_SHA1);
            digest.update(decript.getBytes(Constant.DEFAULT_CHARSET));
            byte[] messageDigest = digest.digest();
            // Create Hex String
            StringBuilder hexString = new StringBuilder();

            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            result = hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            logger.error("sha1加密失败", e);
        } catch (UnsupportedEncodingException e) {
            logger.error("sha1加密失败", e);
        }
        return result;
    }

    /**
     * 获取目标字符串的 sha5 加密的字符串.
     *
     * @param src 目标字符串
     * @return 目标字符串的 sha5 加密的字符串
     * @throws NoSuchAlgorithmException
     */
    public static String sha5HexStr(final String src) throws NoSuchAlgorithmException {
        return hashHexStr(src, ALGORITHOM_SHA5);
    }

    /**
     * 获取目标字符串 对应算法 的 hash 字符串.
     *
     * @param src 目标字符串
     * @param algorithm 加密算法
     * @return 加密字符串
     * @throws NoSuchAlgorithmException
     */
    private static String hashHexStr(final String src, final String algorithm) throws NoSuchAlgorithmException {
        byte[] result = hash(src, algorithm);
        return new String(Hex.encodeHex(result, false));
    }

    /**
     * 执行指定的hash方法.
     *
     * @param src 目标字符串
     * @param algorithm hash算法名
     * @return 返回执行结果的 byte[],当algorithm指定的hash算法不存在时返回null
     * @throws NoSuchAlgorithmException
     */
    private static byte[] hash(final String src, final String algorithm) throws NoSuchAlgorithmException {

        byte[] result = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

            result = messageDigest.digest(src.getBytes(Constant.DEFAULT_CHARSET));

        } catch (NoSuchAlgorithmException e) {
            logger.error("字符串 hash 失败", e);
        } catch (UnsupportedEncodingException e) {
            logger.error("字符串 hash 加密失败", e);
        }
        return result;
    }

    /**
     * 生成并返回 RSA 密钥对.
     *
     * @return 返回 rsa 密钥对，当产生失败时返回 null
     * @throws GeneralSecurityException
     */
    public static synchronized KeyPair generateKeyPair() throws GeneralSecurityException {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHOM_RSA);
        keyPairGen.initialize(KEY_SIZE, new SecureRandom());

        return keyPairGen.genKeyPair();
    }

    /**
     * 使用指定的私钥解密数据.
     *
     * @param privateKey 给定的私钥
     * @param data 要解密的数据
     * @return 原数据
     */
    public static byte[] rsaDecrypt(final RSAPrivateKey privateKey, final byte[] data) {

        BigInteger dExponent = privateKey.getPrivateExponent();
        BigInteger modulus = privateKey.getModulus();

        BigInteger c = new BigInteger(data);

        return c.modPow(dExponent, modulus).toByteArray();

    }

    /**
     * 使用给定的私钥解密给定的字符串.
     *
     * 若私钥为 {@code null}，或者 {@code encrypttext} 为 {@code null}或空字符串则返回 {@code null}。 私钥不匹配时，返回 {@code null}.
     *
     * @param privateKey 给定的私钥
     * @param encrypttext 密文
     * @return 原文字符串。
     */
    public static String rsaDecrypt(final RSAPrivateKey privateKey, final String encrypttext) {
        if (privateKey == null || StringUtils.isBlank(encrypttext)) {
            return null;
        }
        BigInteger c = new BigInteger(encrypttext, DEFAULT_STR_RADIX);

        byte[] data = c.modPow(privateKey.getPrivateExponent(), privateKey.getModulus()).toByteArray();

        return new String(data);
    }

    /**
     * 使用指定的公钥加密数据.
     *
     * @param publicKey 给定的公钥
     * @param data 要加密的数据
     * @return 加密后的数据。
     */
    public static byte[] rsaEncrypt(final RSAPublicKey publicKey, final byte[] data) {

        BigInteger m = new BigInteger(data);

        BigInteger c = m.modPow(publicKey.getPublicExponent(), publicKey.getModulus());

        return c.toByteArray();
    }

    /**
     * 使用给定的公钥加密给定的字符串.
     *
     * 若 {@code publicKey} 为 {@code null}，或者 {@code plaintext} 为 {@code null} 则返回 {@code null}.
     *
     * @param publicKey 给定的公钥
     * @param plaintext 字符串
     * @return 给定字符串的密文
     */
    public static String rsaEncrypt(final RSAPublicKey publicKey, final String plaintext) {
        if (publicKey == null || plaintext == null) {
            return null;
        }
        byte[] data = new byte[]{};
        try {
            data = plaintext.getBytes(Constant.DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            logger.error("rsa加密失败", e);
        }
        byte[] enData = rsaEncrypt(publicKey, data);

        return new String(Hex.encodeHex(enData, false));

    }

    /**
     * 使用默认的私钥解密由JS加密（使用此类提供的公钥加密）的字符串.
     *
     * @param encrypttext 密文
     * @return {@code encrypttext} 的原文字符串
     */
    public static String decryptStringByJs(final RSAPrivateKey privateKey, final String encrypttext) {

        String text = rsaDecrypt(privateKey, encrypttext);

        if (text == null) {
            return null;
        }

        return StringUtils.reverse(text);
    }

    /**
     * 返回已初始化的默认的公钥.
     *
     * @param keyPair
     * @return
     */
    public static RSAPublicKey getPublicKey(final KeyPair keyPair) {
        if (keyPair != null) {
            return (RSAPublicKey) keyPair.getPublic();
        }
        return null;
    }

    /**
     * 返回已初始化的默认的私钥.
     *
     * @param keyPair
     * @return
     */
    public static RSAPrivateKey getPrivateKey(final KeyPair keyPair) {
        if (null != keyPair) {
            return (RSAPrivateKey) keyPair.getPrivate();
        }
        return null;
    }

    /**
     * 使用模和指数生成 RSA 公钥.
     *
     * 注意：【此代码用了默认补位方式，为 RSA/None/PKCS1Padding，不同 JDK 默认的补位方式可能不同，如 Android 默认是 RSA
     * /None/NoPadding】
     *
     * @param modulus 模
     * @param exponent 指数
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static RSAPublicKey getPublicKey(String modulus, String exponent) throws GeneralSecurityException {
        BigInteger b1 = new BigInteger(modulus, 16);
        BigInteger b2 = new BigInteger(exponent, 16);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHOM_RSA);
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(b1, b2);
        return (RSAPublicKey) keyFactory.generatePublic(keySpec);
    }

    /**
     * 根据 rsa 公钥进行 base64 加密.
     *
     * @param key 公钥
     * @param password 待加密字符串
     * @return
     */
    public static String encryptBase64Password(PublicKey key, String password) 
            throws GeneralSecurityException, UnsupportedEncodingException {
        Cipher rsa = Cipher.getInstance(ALGORITHOM_RSA);
        rsa.init(Cipher.ENCRYPT_MODE, key);

        byte[] encodedPassword = rsa.doFinal(password.getBytes("ASCII"));
        return DatatypeConverter.printBase64Binary(encodedPassword);
    }
}
