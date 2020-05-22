package com.newland.boss.cib.crmp.common.other;

import java.util.regex.Pattern;

/**
 * 描述:
 * 正则表达式
 *
 * @author weixc
 * create 2018-11-21 9:07
 */
public class PatternUtil {

    public static boolean checkUrl(String url) {
        String args = "^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\\\/])+$";
        Pattern pattern = Pattern.compile(args);
        return pattern.matcher(url).matches();
    }

    public static boolean checkNum(String arg) {
        String args = "^[0-9]*$";
        Pattern pattern = Pattern.compile(args);
        return pattern.matcher(arg).matches();
    }
}
