package dps924.assignment2;

public class Product {

    public String Name;
    public double Price;

    public Product(String name, double price) {
        this.Name = name;
        this.Price = price;
    }
    public Product(String name) {
        this(name, 0d);
    }
}
