package cn.com.eju.pmls.utils;

import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * Des加解密工具类
 */
public class DesUtil {
    static AlgorithmParameterSpec iv = null;// 加密算法的参数接口，IvParameterSpec是它的一个实现
    private static SecretKey key = null;


    public DesUtil(String desKey, String desIv) throws Exception {
        byte[] DESkey = desKey.getBytes();
        byte[] DESIV = desIv.getBytes();
        DESKeySpec keySpec = new DESKeySpec(DESkey);// 设置密钥参数
        iv = new IvParameterSpec(DESIV);// 设置向量
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
        key = keyFactory.generateSecret(keySpec);// 得到密钥对象
    }

    /**
     * 加密
     * @param data 待加密的数据
     * @return 加密后的数据
     * @throws Exception
     */
    public String encode(String data) throws Exception {
        Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");// 得到加密对象Cipher
        enCipher.init(Cipher.ENCRYPT_MODE, key, iv);// 设置工作模式为加密模式，给出密钥和向量
        byte[] pasByte = enCipher.doFinal(data.getBytes("utf-8"));
        return Base64.getEncoder().encodeToString(pasByte);
    }

    /**
     * 解密
     * @param data  解密前的数据
     * @return 解密后的数据
     * @throws Exception
     */
    public String decode(String data) throws Exception {
        Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        deCipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] pasByte = deCipher.doFinal(Base64.getDecoder().decode(data));
        return new String(pasByte, "UTF-8");
    }
}
