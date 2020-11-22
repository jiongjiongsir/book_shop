package neu.edu.dao.impl;

import neu.edu.dao.OrderDao;
import neu.edu.pojo.Order;

public class OrderDaoIml  extends BaseDao implements OrderDao{

    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";

        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }
}
