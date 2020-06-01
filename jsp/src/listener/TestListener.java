package listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * @author ${林锋鹏}
 * @Title: TestListener
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/19 11:09
 */
public class TestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("销毁！");
        }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("初始化！");
    }
}
