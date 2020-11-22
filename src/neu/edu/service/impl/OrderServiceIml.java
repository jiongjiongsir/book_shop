package neu.edu.service.impl;

import neu.edu.dao.BookDao;
import neu.edu.dao.OrderDao;
import neu.edu.dao.OrderItemDao;
import neu.edu.dao.impl.BookDaoIml;
import neu.edu.dao.impl.OrderDaoIml;
import neu.edu.dao.impl.OrderItemDaoIml;
import neu.edu.pojo.*;
import neu.edu.service.OrderService;

import java.util.Date;
import java.util.Map;

public class OrderServiceIml implements OrderService {
    private OrderDao orderDao=new OrderDaoIml();
    private OrderItemDao orderItemDao=new OrderItemDaoIml();
    private BookDao bookDao=new BookDaoIml();
    /**
     * 
     * @param cart
     * @param userId 
     * @return java.lang.String
     * @author yuxin
     * @creed: Talk is cheap,show me the code
     * @date  
     */
    @Override
    public String createOrder(Cart cart, Integer userId) {
        Order order=new Order();
        order.setUserId(userId);
        order.setCreateTime(new Date());
        order.setPrice(cart.getTotalPrice());
        order.setStatus(0);
        String orderId=System.currentTimeMillis()+""+userId;
        order.setOrderId(orderId);
        orderDao.saveOrder(order);
        for(Map.Entry<Integer, CartItem>entry:cart.getCartItems().entrySet())
        {
            //获取每一个购物车中的商品项
            CartItem cartItem=entry.getValue();
            //转化每一个商品项为订单项
            OrderItem orderItem=new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            Book book=bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());
            bookDao.updateBook(book);
            //保存订单项到数据库
            orderItemDao.saveOrderItem(orderItem);
        }
        cart.clear();
        return orderId;
    }
}
