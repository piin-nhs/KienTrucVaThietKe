public class CancelledOrderState implements OrderState {

    @Override
    public void handle(Order order) {
        System.out.println("Hủy đơn hàng và hoàn tiền.");
    }
}
