package vttp.workshopDay24.day24Workshop.models;

public class Item {
    
    private String itemName;
    private int id;
    private Float price;
    private Float discount;
    private int quantity;

    public Item(String itemName, Float price, Float discount, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Float getPrice() {
        return price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }
    public Float getDiscount() {
        return discount;
    }
    public void setDiscount(Float discount) {
        this.discount = discount;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
