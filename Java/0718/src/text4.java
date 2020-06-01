import java.util.Scanner;

public class text4 {
    public static void main(String[] args) {
        System.out.println("请输入基本工资：");
           Scanner input = new Scanner(System.in);
           int   basicSalary = input.nextInt();
           float priceSubsidies = 1200;
           float allowance = 750;
           float salary = basicSalary+priceSubsidies +allowance;
           System.out.println("该员工的工资细目为");
           System.out.println("基本工资："+basicSalary);
           System.out.println("物价津贴："+priceSubsidies);
           System.out.println("房屋津贴："+allowance);
           System.out.println("员工薪水是："+salary);

           }
           }
