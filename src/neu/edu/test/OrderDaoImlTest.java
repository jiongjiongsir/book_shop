package neu.edu.test;

import neu.edu.dao.OrderDao;
import neu.edu.dao.impl.OrderDaoIml;
import neu.edu.pojo.Order;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

public class OrderDaoImlTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void saveOrder() {
        OrderDao orderDao=new OrderDaoIml();
        orderDao.saveOrder(new Order("1234567",new Date(),new BigDecimal(100),0,1));
    }
}