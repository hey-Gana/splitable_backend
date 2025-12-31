package splitable.backend.billscanner.model;

public class BillItemResponse {

    private String itemName;
    private double price;

    public BillItemResponse(String itemName, double price) {
        this.itemName = itemName;
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }
}
