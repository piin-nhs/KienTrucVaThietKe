public class Main {
    public static void main(String[] args) {
        int originalAmount = 100;

        Payment payment = new CreditCardPayment();
        payment.pay(originalAmount);
        System.out.println("--------------------------------");

        payment = new ProcessingFeeDecorator(payment, 20);
        payment.pay(originalAmount);
        System.out.println("--------------------------------");

        payment = new DiscountDecorator(payment, 0.1);
        payment.pay(originalAmount);

    }
}
