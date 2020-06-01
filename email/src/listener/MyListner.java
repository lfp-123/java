package listener;


import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * @author ${林锋鹏}
 * @Title: MyListner
 * @ProjectName Java
 * @Description: 实现对应的接口实现对应的监听器
 * @date 2019/12/17 17:12
 */
public class MyListner implements HttpSessionAttributeListener{
    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        String name = se.getName();
        Object value = se.getValue();
        System.out.println("session作用域的属性，属性名:"+name+"新增的值："+value);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        String name = se.getName();
        System.out.println("删除的属性，属性名："+name);
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        String name = se.getName();
        Object value = se.getValue();
        System.out.println("session作用域的属性，属性名:" + name + "替换的值：" + value);
    }
}
