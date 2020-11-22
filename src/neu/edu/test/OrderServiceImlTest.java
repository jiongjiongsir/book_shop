package neu.edu.test;

import neu.edu.pojo.Cart;
import neu.edu.pojo.CartItem;
import neu.edu.service.OrderService;
import neu.edu.service.impl.OrderServiceIml;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceImlTest {

    @Test
    public void createOrder() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"2333",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"2333",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"2333",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"22222",2,new BigDecimal(100),new BigDecimal(200)));
        OrderService orderService=new OrderServiceIml();
        System.out.println("订单号是"+orderService.createOrder(cart,1));

    }
}