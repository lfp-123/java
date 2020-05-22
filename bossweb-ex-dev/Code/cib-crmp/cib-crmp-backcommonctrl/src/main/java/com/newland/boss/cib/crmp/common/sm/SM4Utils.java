package com.newland.boss.cib.crmp.common.sm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * SM4������
 */
public class SM4Utils {

    private String secretKey = "NEWland123qweasd";

    public String encryptDataECB(String plainText) {
        try {
            SM4Context ctx = new SM4Context();
            ctx.setMode(SM4.SM4_ENCRYPT);

            byte[] keyBytes = secretKey.getBytes();
            SM4 sm4 = new SM4();
            sm4.sm4SetkeyEnc(ctx, keyBytes);
            byte[] encrypted = sm4.sm4CryptEcb(ctx, plainText.getBytes("GBK"));
            String cipherText = new BASE64Encoder().encode(encrypted);
            if (cipherText != null && cipherText.trim().length() > 0) {
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(cipherText);
                cipherText = m.replaceAll("");
            }
            return cipherText;
        } catch (Exception e) {
            return null;
        }
    }

    public String decryptDataECB(String cipherText) {
        try {
            SM4Context ctx = new SM4Context();
            ctx.setMode(SM4.SM4_DECRYPT);
            byte[] keyBytes = secretKey.getBytes();
            SM4 sm4 = new SM4();
            sm4.sm4SetkeyDec(ctx, keyBytes);
            byte[] decrypted = sm4.sm4CryptEcb(ctx, new BASE64Decoder().decodeBuffer(cipherText));
            return new String(decrypted, "GBK");
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * js前台解密算法，由于js是弱类型，不支持long类型，所以在java中的long类型改写为int类型了
     * @param cipherText
     * @param key
     * @return
     */
    public String decryptDataECB(String cipherText, String key) {
        try {
            SM4ContextJS ctx = new SM4ContextJS();
            ctx.setMode(SM4.SM4_DECRYPT);
            byte[] keyBytes = key.getBytes();
            SM4JS sm4 = new SM4JS();
            sm4.sm4SetkeyDec(ctx, keyBytes);
            byte[] decrypted = sm4.sm4CryptEcb(ctx, new BASE64Decoder().decodeBuffer(cipherText));
            return new String(decrypted, "UTF-8");
        } catch (Exception e) {
            return null;
        }
    }
}
