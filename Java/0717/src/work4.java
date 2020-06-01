public class work4 {
    public static void main(String[] args) {
        String str ="你好zhhongguo";
        System.out.println("第一个字符串"+str);
        int n = str.length();
        System.out.println("第一个字符串的长度："+n);
        String str1 = "北京 上海";
        System.out.println("第二个字符串："+str1);
        String str2 = str.concat(str1);
        System.out.println("拼接str和str1后的字符串:"+str2);



    }
}
