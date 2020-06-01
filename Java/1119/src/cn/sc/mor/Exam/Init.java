package cn.sc.mor.Exam;



import java.util.ArrayList;

import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: Init
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/19 16:04
 */
public class Init {
    public static List<TestPager> listPaper =new ArrayList<>();
    static{
        TestPager javas = new TestPager();

        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question(1,"说出重载的特点：","A:方法名不一致","B:","C:","D:...","C"));
        questions.add(new Question(2,"说出重载的特点：","A:方法名不一致","B:","C:","D:...","C"));
        questions.add(new Question(3,"说出重载的特点：","A:方法名不一致","B:","C:","D:...","C"));
        questions.add(new Question(4,"说出重载的特点：","A:方法名不一致","B:","C:","D:...","C"));
        questions.add(new Question(5,"说出重载的特点：","A:方法名不一致","B:","C:","D:...","C"));
        javas.setId(1);
        javas.setName("周考试卷");
        javas.setList(questions);
        listPaper.add(javas);
    }

}
