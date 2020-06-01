/**
 * Created by Enzo Cotter on 2019/7/29.
 */
public class Students {

    String[] studengs = new String[30];
    public  void addStudents(String name){
        int i=0;
        for(;i<studengs.length;i++){
            if(studengs[i]==null){
                break;
            }
        }
        if(i<studengs.length){
            studengs[i]=name;
        }
    }
    public void show(){
        System.out.println("*************************");
        System.out.println("\t\t客户姓名列表：");
        System.out.println("*************************");
        for (String name:studengs) {
            if (name==null)
            {break;}
            System.out.print(name+"\t");
        }

    }
    public void search(String name ) {

        for (int i = 0; i < studengs.length; i++) {
            if(studengs[i]==null) break;
            if (studengs[i].equals(name)) {
                System.out.println("找到了！");break;
            }else {
                System.out.println("没这人！");
            }
        }
    }


     public void midfy(String name,String newname){
         for (int i = 0; i < studengs.length; i++) {
             if(name.equals(studengs[i])){
                 studengs[i]=newname;
                 System.out.println("找到并修改成功！");break;
             }else {
                 System.out.println("修改失败!");
             }
         }
     }
}
