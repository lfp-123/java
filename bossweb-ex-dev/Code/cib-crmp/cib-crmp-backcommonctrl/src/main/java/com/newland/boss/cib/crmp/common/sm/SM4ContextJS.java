package com.newland.boss.cib.crmp.common.sm;

/**
 *
 */
public class SM4ContextJS {
    private int mode;

    private int[] sk;

    private boolean isPadding;

    public SM4ContextJS() {
        this.mode = 1;
        this.isPadding = true;
        this.sk = new int[32];
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int[] getSk() {
        return sk;
    }

    public void setSk(int[] sk) {
        this.sk = sk;
    }

    public boolean isPadding() {
        return isPadding;
    }

    public void setPadding(boolean padding) {
        isPadding = padding;
    }
}
