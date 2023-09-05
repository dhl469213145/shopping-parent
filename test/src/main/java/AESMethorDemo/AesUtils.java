package AESMethorDemo;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AesUtils {

    private static final String KEY_ALGORITHM = "AES";

    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";// 默认的加密算法

    /**
     * AES 加密操作
     * @author 溪云阁
     * @param content 待加密内容
     * @param password 加密密码
     * @return String 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String password) {
        try {
            // 创建密码器
            final Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            // 设置为UTF-8编码
            final byte[] byteContent = content.getBytes("utf-8");
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password));
            // 加密
            final byte[] result = cipher.doFinal(byteContent);
            // 通过Base64转码返回
            return Base64.encodeBase64String(result);

        }
        catch (final Exception ex) {
            ex.printStackTrace();
//            throw new CommonRuntimeException(ex.fillInStackTrace());
            return null;
        }
    }

    /**
     * AES 解密操作
     * @author 溪云阁
     * @param content
     * @param password
     * @return String
     */
    public static String decrypt(String content, String password) {
        try {
            // 实例化
            final Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            // 使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password));
            // 执行操作
            final byte[] result = cipher.doFinal(Base64.decodeBase64(content));
            // 采用UTF-8编码转化为字符串
            return new String(result, "utf-8");

        }
        catch (final Exception ex) {
            ex.printStackTrace();
            return "";
//            throw new CommonRuntimeException(ex.fillInStackTrace());
        }

    }

    /**
     * 生成加密秘钥
     * @author 溪云阁
     * @param password 加密的密码
     * @return SecretKeySpec
     */
    private static SecretKeySpec getSecretKey(final String password) {
        // 返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);
            // AES 要求密钥长度为 128
            kg.init(128, new SecureRandom(password.getBytes()));
            // 生成一个密钥
            final SecretKey secretKey = kg.generateKey();
            // 转换为AES专用密钥
            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
        }
        catch (final NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return null;
//            throw new CommonRuntimeException(ex.fillInStackTrace());
        }
    }

    public static void main(String[] args) {
        final String content = "this is a bad day";
        String AESStr = encrypt(content, "xy934yrn9342u0ry4br8cn-9u2");
        System.out.println(AESStr);

//        final String str = "V9JofCHn02eyXRiDb1VuseRSuOgEQftROwudMPWwMAO2Wk5K7aYZ4Vtm6xiTn5i5";
        System.out.println(decrypt(AESStr, "xy934yrn9342u0ry4br8cn-9u2"));
    }

}
