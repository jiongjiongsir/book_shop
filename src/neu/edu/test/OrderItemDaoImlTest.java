package neu.edu.test;

import neu.edu.dao.OrderItemDao;
import neu.edu.dao.impl.OrderItemDaoIml;
import neu.edu.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderItemDaoImlTest {

    @Test
    public void saveOrderItem() {
        OrderItemDao orderItemDao=new OrderItemDaoIml();
        orderItemDao.saveOrderItem(new OrderItem(null,"hahah",1,new BigDecimal(100),new BigDecimal(100),"1234567"));
        orderItemDao.saveOrderItem(new OrderItem(null,"Mybean",1,new BigDecimal(100),new BigDecimal(100),"1234567"));
        orderItemDao.saveOrderItem(new OrderItem(null,"C++",1,new BigDecimal(100),new BigDecimal(100),"1234567"));

    }
}