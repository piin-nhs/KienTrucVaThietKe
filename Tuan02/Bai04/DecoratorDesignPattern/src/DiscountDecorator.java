public class DiscountDecorator extends PaymentDecorator {

    private final double discount;

    public DiscountDecorator(Payment payment, double discount) {
        super(payment);
        this.discount = discount ;
    }

    @Override
    public void pay(double amount) {
        double discountAmount = amount * discount;
        double total = amount - discountAmount;
        System.out.println("Đã áp dụng mã giảm giá (" + (discount * 100) + "%): -" + String.format("%.2f", discountAmount) + " VND");
        super.pay(total);
    }
}
