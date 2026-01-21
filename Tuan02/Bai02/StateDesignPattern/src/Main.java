public class Main {
    public static void main(String[] args) {

        Order order = new Order();
        Order order1 = new Order();

        order.process();
        order.process();
        order.process();

        System.out.println("-------------------------");
        order1.process();
        order1.setState(new CancelledOrderState());
        order1.process();
    }
}
