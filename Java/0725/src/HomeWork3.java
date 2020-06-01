import jdk.nashorn.internal.runtime.NumberToString;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/25 19:35
 */
public class HomeWork3 {
    public static void main(String[] args) {
        String id="";
        String  phoneNum ="";
        Scanner scanner = new Scanner(System.in);
      aa:  while (true){
            try{
                System.out.println("请输入身份证号：");
                long idnumber= scanner.nextLong();
                id = String.valueOf(idnumber);
                }catch (InputMismatchException e){
                System.out.println("请输入正确的数字："); continue aa;
            }

            try{
                System.out.println("请输入手机号：");
                long  phone = scanner.nextLong();
                phoneNum = String.valueOf(phone);
            }catch (InputMismatchException e){
                System.out.println("请输入正确的数字：");continue aa;
            }




            System.out.println("请输入座机号：");
            String tleNum = scanner.next();


            if(!(id.length()==16||id.length()==18)){
                System.out.println("身份证号码必须是16位或18位！");
                continue;
            }else if(!(phoneNum.length()==11)){
                System.out.println("手机号必须是11位！");continue;
            } else if(!(tleNum.length()==12&&tleNum.indexOf("-")==4)){
                System.out.println("座机号必须是XXXX-XXXXXXX格式！");continue;
            }else {
                System.out.println("注册成功！");
                break;
            }
        }
    }
}
