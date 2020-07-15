package cn.com.eju.deal.common.util;

import java.security.Key;  
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;  
import sun.misc.BASE64Decoder;  
import sun.misc.BASE64Encoder;  
 
public class WebConfigRSAUtil {  
     
   /** 指定加密算法为RSA */  
   private static final String ALGORITHM = "RSA";  
   private static String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDTUJvlB+Fjefx4wYNAHeA+UHFJdDtlXoNxoV1q"
   		+"k0nuTft0Cgzx2jUwBuFZ0ftPt9e8TlKyapyOuEoR+ThWR9INx3WesZq9fHe9DwAUW6OzzI8xkZh7"
   		+"leg7FRTIFtlPQq7KLUQdmTArca/FowAkkUmW1N/JiFfqFdStq1KZEskNowIDAQAB";

   private static String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANNQm+UH4WN5/HjBg0Ad4D5QcUl0"
   		+"O2Veg3GhXWqTSe5N+3QKDPHaNTAG4VnR+0+317xOUrJqnI64ShH5OFZH0g3HdZ6xmr18d70PABRb"
   		+"o7PMjzGRmHuV6DsVFMgW2U9CrsotRB2ZMCtxr8WjACSRSZbU38mIV+oV1K2rUpkSyQ2jAgMBAAEC"
   		+"gYAefvmgmfyLEKXA/5xjemxMUeungCC6RmON2I3UvaDjxVlOq8ymcqlOXw/SmJ0+Kff2cw84NG6X"
   		+"ojz+Tws0WHUVyM2p9+iFz7bifUByBghYK2CojTqnE3ilbqmZi1VzUebAtff5Gr7JYqDbV+kBrX0R"
   		+"UiMmVMpVRXpt+vbg5DNCoQJBAPIMGjSaH2695OXR4Bqp1vhC+QwgBQ172tixn9OnYbX/mXNlEtIO"
   		+"tmZpgNwzIzwpnedxUvea394YvnVc7/Ik9MkCQQDffvzk31Ug7LmSSJZHFU2ZG1byoFoAOsPAxXWK"
   		+"aJXsjcXmZ7OaQanXMOLvgAmn4C43hz5uExyFJ2e6yAeP78ELAkEAwXVU2Kg66UtTkTCfGxCWZhU5"
   		+"cyyAfVd6Ampt4UBOQ6YIYwfs9AGYXeCDgvxIdESbFaoo8whRV/WU/L8MdRB+qQJAFOXa38vCOgHo"
   		+"mDURWDQ1vUtBJg64W60jALN1+istWaEoEBNCR/o711NK5Poj7P6ZU7fhrok68XhtyplJm7vfYQJB"
   		+"AL86mtb3wz/F4ksmvJrBQGq0INpA/MDquzUDaZ+1KTAx1lRE2Cc3XXsSfMaHOVL9CfPmHHNW8lO8"
   		+"baGcjPUSP3o=";

     
   /** 
    * 加密方法 
    * @param source 源数据 
    * @return 
    * @throws Exception 
    */  
   public static String encrypt(String source) throws Exception {  
         
       Key publicKey = getPublicKey(PUBLIC_KEY);  
 
       /** 得到Cipher对象来实现对源数据的RSA加密 */  
       Cipher cipher = Cipher.getInstance(ALGORITHM);  
       cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
       byte[] b = source.getBytes();  
       /** 执行加密操作 */  
       byte[] b1 = cipher.doFinal(b);  
       BASE64Encoder encoder = new BASE64Encoder();  
       return encoder.encode(b1);  
   }  
 
   /** 
    * 解密算法 
    * @param cryptograph    密文 
    * @return 
    * @throws Exception 
    */  
   public static String decrypt(String cryptograph) throws Exception {  
         
       Key privateKey = getPrivateKey(PRIVATE_KEY);  
 
       /** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */  
       Cipher cipher = Cipher.getInstance(ALGORITHM);  
       cipher.init(Cipher.DECRYPT_MODE, privateKey);  
       BASE64Decoder decoder = new BASE64Decoder();  
       byte[] b1 = decoder.decodeBuffer(cryptograph);  
 
       /** 执行解密操作 */  
       byte[] b = cipher.doFinal(b1);  
       return new String(b);  
   }  
     
   public static PublicKey getPublicKey(String key) throws Exception {
       byte[] keyBytes;
       keyBytes = (new BASE64Decoder()).decodeBuffer(key);
       X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
       KeyFactory keyFactory = KeyFactory.getInstance("RSA");
       PublicKey publicKey = keyFactory.generatePublic(keySpec);
       return publicKey;
   } 
   
   public static PrivateKey getPrivateKey(String key) throws Exception {
       byte[] keyBytes;
       keyBytes = (new BASE64Decoder()).decodeBuffer(key);
       PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
       KeyFactory keyFactory = KeyFactory.getInstance("RSA");
       PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
       return privateKey;
  }
}   