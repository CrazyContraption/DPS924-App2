package dps924.assignment2;

import java.util.Date;

public class Purchasable extends Product {

    public int Quantity;

    public Purchasable(Product item, int quantity) {
        super(item.Name, item.Price);
        this.Quantity = quantity;
    }
    public Purchasable(String name, int quantity, double price) {
        super(name, price);
        this.Quantity = quantity;
    }

    public double getTotal() {
        return this.Price * (double) this.Quantity;
    }
}
