package neu.edu.pojo;

import java.math.BigDecimal;
import java.util.*;

public class Cart {
//    private Integer totalCount;
//    private BigDecimal totalPrice;
    private Map<Integer,CartItem> cartItems=new LinkedHashMap<Integer, CartItem>();

    public Cart() {
    }

    public Cart(Map<Integer, CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Integer getTotalCount() {
       Integer totalCount=0;
       for(Map.Entry<Integer,CartItem>entry:cartItems.entrySet() )
       {
           totalCount+=entry.getValue().getCount();
       }
       return totalCount;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice=new BigDecimal(0);
        for(Map.Entry<Integer,CartItem>entry:cartItems.entrySet() )
        {
            totalPrice=totalPrice.add(entry.getValue().getTotalPrice());

        }
        return totalPrice;
    }

    public Map<Integer, CartItem> getCartItems() {
        return cartItems;
    }



    public void setCartItems(Map<Integer, CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public void addItem(CartItem item)
    {
        CartItem cartItem=cartItems.get(item.getId());
        if(cartItem==null)
        {
            cartItems.put(item.getId(),item);
        }
        else
        {
            cartItem.setCount(cartItem.getCount()+1);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }

    public void remove(Integer id)
    {
        cartItems.remove(id);
    }

    public void clear()
    {
        cartItems.clear();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", cartItems=" + cartItems +
                '}';
    }

    public void updateCount(Integer id, Integer count)
    {
        CartItem cartItem=cartItems.get(id);
        if(cartItem!=null)
        {
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }
}
