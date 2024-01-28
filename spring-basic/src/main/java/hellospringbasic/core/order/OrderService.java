package hellospringbasic.core.order;

public interface OrderService {
    Order createOrder(Long memId, String itemName, int itemPrice);
}
