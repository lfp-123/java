package com.seecen.work1;


import java.lang.reflect.Method;

/**
 * @author ${林锋鹏}
 * @Title: TestObject
 * @ProjectName Java
 * @date 2019/8/15 14:36
 */
public class TestObject {
    public static void main(String[] args) throws Exception {
        Student student = new Student("李白", 18);
        student.setGender("男");
        student.setHeight(180);
        String json = getJson(student);
        System.out.println(json);

    }
    public static  String getJson(Object object) throws Exception {
        String jsonValue =null;
        StringBuilder jsonStr = new StringBuilder("{");
        Class<?> aClass = object.getClass();
        //获取所有得方法！！
        Method[] methods = aClass.getMethods();
        //对象名。Method
        for (Method method: methods ) {
           if(method.getName().startsWith("get")){
               String jsonKey = method.getName().substring(3).toLowerCase();
               if(jsonKey.equals("class")){
                   continue;
               }
               //获取属性
               try{
                    jsonValue = method.invoke(object).toString();
               }catch (Exception e){
                    jsonValue= null;
               }
                jsonStr.append("\""+jsonKey+"\"");
                jsonStr.append(":");
                jsonStr.append("\""+jsonValue+"\"");
                jsonStr.append(",");
           }
        }
        jsonStr.deleteCharAt(jsonStr.length()-1);
        jsonStr.append("}");
        return  jsonStr.toString();

    }
}
