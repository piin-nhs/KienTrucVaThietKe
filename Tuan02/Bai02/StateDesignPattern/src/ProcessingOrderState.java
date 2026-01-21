public class ProcessingOrderState implements OrderState {

    @Override
    public void handle(Order order) {
        System.out.println("Đóng gói và vận chuyển...");
        order.setState(new DeliveredOrderState());
    }
}
