public class ProcessingFeeDecorator extends PaymentDecorator {

    private final double fee;

    public ProcessingFeeDecorator(Payment payment, double fee) {
        super(payment);
        this.fee = fee;
    }

    @Override
    public void pay(double amount) {
        double total = amount + fee;
        System.out.println("Đã cộng phí xử lý: " + fee + " VND");
        super.pay(total);
    }
}
