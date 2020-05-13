package com.xagu.xxb.common.tools;

import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

/**
 * @author xagu
 * Created on 2020/5/13
 * Email:xagu_qc@foxmail.com
 * Describe: AES加解密
 */
public class AesUtil {

    public static final String KEY_DOWNLOAD_EXAM = "fdkjsfhsjdfbdvyer345r34";

    private static final String KEY_ALGORITHM = "AES";
    /**
     * 默认的加密算法
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * AES 加密操作
     *
     * @param content 待加密内容
     * @param key     加密密钥
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String key) {
        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            byte[] byteContent = content.getBytes("utf-8");
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));
            // 加密
            byte[] result = cipher.doFinal(byteContent);
            //通过Base64转码返回
            return Base64Utils.encodeToString(result);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * AES 解密操作
     *
     * @param content
     * @param key
     * @return
     */
    public static String decrypt(String content, String key) throws Exception {

        //实例化
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

        //使用密钥初始化，设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key));

        //执行操作
        byte[] result = cipher.doFinal(Base64Utils.decodeFromString(content));


        return new String(result, StandardCharsets.UTF_8);

    }

    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static SecretKeySpec getSecretKey(final String key) throws Exception {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;


        kg = KeyGenerator.getInstance(KEY_ALGORITHM);

        SecureRandom random=null;
        random = SecureRandom.getInstance("SHA1PRNG","SUN");

        random.setSeed(key.getBytes());
        //AES 要求密钥长度为 128
        kg.init(128, random);

        //生成一个密钥
        SecretKey secretKey = kg.generateKey();
        // 转换为AES专用密钥
        return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);




    }

    public static void main(String[] args) throws Exception {
        String a = "FTNs4RsILV0EzEpo7cG8M+bh+lttESAg9lz0+K4n4Wy8652D6Z0w/AzjL+IyGonvQR+NtnyFlSsa+8PtRYvPz0fb0pJKhldtYHGFgp+PWi457gxAnZn/E3DMydYXTp0uaY+6QnsWMELFfFz6RHnBwHCVzugRV+FugfcULv7FIBUi1KyYfLQ1B16oE3ip45MG8g0PS9dHZjNEo+R47WckmaXKSnOfQu0rjmBfzXf6zcDpcumzwDj/cV38wBocaNlbZ8UZX/CvqqSOqNxvzgNBKg==";
        String decrypt = decrypt(a, KEY_DOWNLOAD_EXAM);
        System.out.println(decrypt);
    }


}