package neu.edu.test;

import neu.edu.pojo.Cart;
import neu.edu.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CartTest {

    @Test
    public void addItem() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"2333",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"2333",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"2333",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"22222",2,new BigDecimal(100),new BigDecimal(200)));
        System.out.println(cart);
    }

    @Test
    public void remove() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"2333",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"2333",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"2333",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"22222",2,new BigDecimal(100),new BigDecimal(200)));
        cart.remove(2);
        System.out.println(cart);
    }

    @Test
    public void clear() {


    }

    @Test
    public void updateCount() {

        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"2333",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"2333",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"2333",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"22222",2,new BigDecimal(100),new BigDecimal(200)));
        cart.remove(2);
        cart.clear();
        cart.addItem(new CartItem(1,"2333",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"2333",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"22222",2,new BigDecimal(100),new BigDecimal(200)));
        cart.updateCount(1,5);
        System.out.println(cart);

    }
}