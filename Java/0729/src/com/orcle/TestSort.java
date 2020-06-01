/**
 * Created by Enzo Cotter on 2019/7/29.
 */
public class TestSort {
    public static void main(String[] args) {

        StudentsBiz st = new StudentsBiz();
        st.inputNames();
        String[] names = st.getNames();
        System.out.println("排序后：");
        for (String a : names) {
            System.out.println(a);
        }
    }
}
