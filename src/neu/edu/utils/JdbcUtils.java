package neu.edu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class JdbcUtils {
    private static DataSource dataSource;
    static {
        Properties properties=new Properties();


        try{
            InputStream inputStream=JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static Connection getConntection()
    {
        Connection conn=null;
        try {
            conn = dataSource.getConnection();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return conn;
    }
    public  static void close(Connection conn)
    {
        if(conn!=null)
        {
            try {
            conn.close();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }



}

