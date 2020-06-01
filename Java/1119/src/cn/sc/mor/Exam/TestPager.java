package cn.sc.mor.Exam;

import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: TestPager
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/19 16:01
 */
public class TestPager {
    private Integer id;
    private String name;
    private List<Question> list;

    public TestPager(){

    }
    public TestPager(Integer id, String name, List<Question> list) {
        this.id = id;
        this.name = name;
        this.list = list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getList() {
        return list;
    }

    public void setList(List<Question> list) {
        this.list = list;
    }
}
