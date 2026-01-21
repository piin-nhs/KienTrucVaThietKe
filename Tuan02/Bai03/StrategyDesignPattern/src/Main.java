public class Main {
    public static void main(String[] args) {

        Product book = new Product("Book", 100, new VATTax());
        System.out.println("Thuế sách: " + book.getTax());
        System.out.println("Tổng tiền: " + book.getTotalPrice());

        Product wine = new Product("Wine", 100, new ExciseTax());
        System.out.println("Thuế rượu: " + wine.getTax());
        System.out.println("Tổng tiền: " + wine.getTotalPrice());

        wine.setTaxStrategy(new LuxuryTax());
        System.out.println("Thuế mới: " + wine.getTax());
        System.out.println("Tổng tiền: " + wine.getTotalPrice());

    }
}
