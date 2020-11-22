package neu.edu.test;

import neu.edu.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcUtilsTest {
    @Test
    public void testJdbcutils(){
        Connection conntection = JdbcUtils.getConntection();
        String sql="insert into t_user(username,password,email) values ('liu','123','saddasdasdsad')";
        String sql_query= "select * from t_user where username = ?";

        try {
            PreparedStatement pst = conntection.prepareStatement(sql_query);
            pst.setString(1,"liu");
            ResultSet resultSet = pst.executeQuery();
            while(resultSet.next())
            {
                System.out.println(resultSet.getString("id")+resultSet.getString("password"));
            }

            //System.out.println(pst.executeUpdate());
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println(conntection);
        JdbcUtils.close(conntection);

    }
}
