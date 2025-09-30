package data;

public class ExpectedProduct {
    public String price, totalPrice;
    public int quantity;

    public ExpectedProduct(String price, int quantity, String totalPrice) {
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}
