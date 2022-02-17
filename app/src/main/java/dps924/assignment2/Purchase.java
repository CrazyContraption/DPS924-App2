package dps924.assignment2;

import java.util.Date;

public class Purchase extends Purchasable {

    public Date Time;

    public Purchase(Product item, int quantity) {
        super(item, quantity);
        this.Time = new Date();
    }
    public Purchase(Purchasable item, int quantity) {
        super(item, quantity);
        this.Time = new Date();
    }
    public Purchase(Purchasable item) {
        this(item, item.Quantity);
    }
    public Purchase(String name, double price, int quantity) {
        this(new Product(name, price), quantity);
    }

    public double getTotal() {
        return this.Price * (double) this.Quantity;
    }
}
