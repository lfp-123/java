package com.newland.boss.cib.crmp.common.sm;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 */
public class SM4UtilsTest {
    @Test
    public void testEncryptDataECB() throws Exception {
        String plainText = "@#$@#$%^123Ad";
        SM4Utils sm4 = new SM4Utils();
        String cipherText = sm4.encryptDataECB(plainText);
        Assert.assertEquals(cipherText, "QOIWbROgzVG5jS0TY6FqRw==");
        plainText = sm4.decryptDataECB(cipherText);
        Assert.assertEquals(plainText, "@#$@#$%^123Ad");

        plainText = "newland";
        cipherText = sm4.encryptDataECB(plainText);
        Assert.assertEquals(cipherText, "2cKymVDXwGvjUBuIScUUUg==");
        plainText = sm4.decryptDataECB(cipherText);
        Assert.assertEquals(plainText, "newland");

        System.out.println(sm4.encryptDataECB("cib"));
    }

}