package neu.edu.service;

import neu.edu.pojo.Cart;
import neu.edu.pojo.Order;

public interface OrderService {
    public String createOrder(Cart cart,Integer userId);
}
