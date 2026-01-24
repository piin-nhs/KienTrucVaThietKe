public class PaypalPayment implements Payment {

    @Override
    public void pay(double amount) {
        System.out.println("Thanh toán " + String.format("%.2f", amount) + " VND qua PayPal.");
    }
}
