package com.newland.boss.cib.crmp.common.sm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * sm4实现
 */
public class SM4JS {
    public static final int SM4_ENCRYPT = 1;

    public static final int SM4_DECRYPT = 0;

    private int getUlongBe(byte[] b, int i) {
        return (b[i] & 0xff) << 24 | ((b[i + 1] & 0xff) << 16) | ((b[i + 2] & 0xff) << 8) | (b[i + 3] & 0xff) & 0xffffffff;
    }

    private void putUlongBe(long n, byte[] b, int i) {
        b[i] = (byte) (int) (0xFF & n >> 24);
        b[i + 1] = (byte) (int) (0xFF & n >> 16);
        b[i + 2] = (byte) (int) (0xFF & n >> 8);
        b[i + 3] = (byte) (int) (0xFF & n);
    }

    private int shl(int x, int n) {
        return (x & 0xFFFFFFFF) << n;
    }

    private int rotl(int x, int n) {
        return shl(x, n) | x >> (32 - n);
    }

    private void swap(int[] sk, int i) {
    	int t = sk[i];
        sk[i] = sk[(31 - i)];
        sk[(31 - i)] = t;
    }

    private static final byte[] SBOX_TABLE = SM4.SBOX_TABLE;

    private static final int[] FK = SM4.FK;

    private static final int[] CK = SM4.CK;

    private byte sm4Sbox(byte inch) {
        int i = inch & 0xFF;
        return SBOX_TABLE[i];
    }

    private int sm4Lt(int ka) {
    	int bb;
    	int c;
        byte[] a = new byte[4];
        byte[] b = new byte[4];
        putUlongBe(ka, a, 0);
        b[0] = sm4Sbox(a[0]);
        b[1] = sm4Sbox(a[1]);
        b[2] = sm4Sbox(a[2]);
        b[3] = sm4Sbox(a[3]);
        bb = getUlongBe(b, 0);
        c = bb ^ rotl(bb, 2) ^ rotl(bb, 10) ^ rotl(bb, 18) ^ rotl(bb, 24);
        return c;
    }

    private int sm4F(int x0, int x1, int x2, int x3, int rk) {
        return x0 ^ sm4Lt(x1 ^ x2 ^ x3 ^ rk);
    }

    private int sm4CalciRK(int ka) {
    	int bb;
    	int rk;
        byte[] a = new byte[4];
        byte[] b = new byte[4];
        putUlongBe(ka, a, 0);
        b[0] = sm4Sbox(a[0]);
        b[1] = sm4Sbox(a[1]);
        b[2] = sm4Sbox(a[2]);
        b[3] = sm4Sbox(a[3]);
        bb = getUlongBe(b, 0);
        rk = bb ^ rotl(bb, 13) ^ rotl(bb, 23);
        return rk;
    }

    private void sm4Setkey(int[] sk, byte[] key) {
    	int[] mk = new int[4];
    	int[] k = new int[36];
        int i = 0;
        mk[0] = getUlongBe(key, 0);
        mk[1] = getUlongBe(key, 4);
        mk[2] = getUlongBe(key, 8);
        mk[3] = getUlongBe(key, 12);
        k[0] = mk[0] ^ FK[0];
        k[1] = mk[1] ^ FK[1];
        k[2] = mk[2] ^ FK[2];
        k[3] = mk[3] ^ FK[3];
        for (; i < 32; i++) {
            k[(i + 4)] = (k[i] ^ sm4CalciRK(k[(i + 1)] ^ k[(i + 2)] ^ k[(i + 3)] ^ CK[i]));
            sk[i] = k[(i + 4)];
        }
    }

    private void sm4OneRound(int[] sk, byte[] input, byte[] output) {
        int i = 0;
        int[] ulbuf = new int[36];
        ulbuf[0] = getUlongBe(input, 0);
        ulbuf[1] = getUlongBe(input, 4);
        ulbuf[2] = getUlongBe(input, 8);
        ulbuf[3] = getUlongBe(input, 12);
        while (i < 32) {
            ulbuf[(i + 4)] = sm4F(ulbuf[i], ulbuf[(i + 1)], ulbuf[(i + 2)], ulbuf[(i + 3)], sk[i]);
            i++;
        }
        putUlongBe(ulbuf[35], output, 0);
        putUlongBe(ulbuf[34], output, 4);
        putUlongBe(ulbuf[33], output, 8);
        putUlongBe(ulbuf[32], output, 12);
    }

    private byte[] padding(byte[] input, int mode) {
        if (input == null) {
            return new byte[0];
        }

        byte[] ret;
        if (mode == SM4_ENCRYPT) {
            int p = 16 - input.length % 16;
            ret = new byte[input.length + p];
            System.arraycopy(input, 0, ret, 0, input.length);
            for (int i = 0; i < p; i++) {
                ret[input.length + i] = (byte) p;
            }
        } else {
            int p = input[input.length - 1];
            ret = new byte[input.length - p];
            System.arraycopy(input, 0, ret, 0, input.length - p);
        }
        return ret;
    }

    public void sm4SetkeyDec(SM4ContextJS ctx, byte[] key) throws SM4Exception {
        if (ctx == null) {
            throw new SM4Exception("ctx is null!");
        }

        if (key == null || key.length != 16) {
            throw new SM4Exception("key error!");
        }

        int i = 0;
        ctx.setMode(SM4_DECRYPT);
        sm4Setkey(ctx.getSk(), key);
        for (i = 0; i < 16; i++) {
            swap(ctx.getSk(), i);
        }
    }

    public byte[] sm4CryptEcb(SM4ContextJS ctx, byte[] input) throws SM4Exception, IOException {

        if (input == null) {
            throw new SM4Exception("input is null!");
        }

        if ((ctx.isPadding()) && (ctx.getMode() == SM4_ENCRYPT)) {
            input = padding(input, SM4_ENCRYPT);
        }

        int length = input.length;
        byte[] output = null;
        try (ByteArrayInputStream bins = new ByteArrayInputStream(input);
             ByteArrayOutputStream bous = new ByteArrayOutputStream()) {
            for (; length > 0; length -= 16) {
                byte[] in = new byte[16];
                byte[] out = new byte[16];
                while (bins.read(in) > 0) {
                    sm4OneRound(ctx.getSk(), in, out);
                    bous.write(out);
                }
            }

            output = bous.toByteArray();
            if (ctx.isPadding() && ctx.getMode() == SM4_DECRYPT) {
                output = padding(output, SM4_DECRYPT);
            }
        }
        return output;
    }

}
