package dao;

import entity.Person;
import util.DButil;

import java.awt.image.DataBufferUShort;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: PersonDao
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/12/11 11:22
 */
public class PersonDao {

    public List<Person> queryAll(Integer pageIndex,Integer pageCount){
        String sql ="select * from (select p.*,rownum r from person p)" +
                "where r between ? and ?";
        ResultSet show = DButil.show(sql,((pageIndex-1)*pageCount+1),(pageCount*pageIndex));
        List<Person> ps = new ArrayList<>();
        try {
            while (show.next()){
                String id = show.getString("id");
                String username = show.getString("username");
                String password = show.getString("password");
                Person person = new Person(id, username, password);
                ps.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DButil.close(DButil.rs,DButil.pare,DButil.conn);
        return ps;
    }
    public int queryCount(){
        String sql = "select count(*) from person";
        ResultSet show = DButil.show(sql);
        int result=0;
        try {
            if(show.next()){
                 result = show.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DButil.close(DButil.rs,DButil.pare,DButil.conn);
        return  result;
    }

}
