package splitable.backend.billscanner.model;

public class BillItem {

    private String itemName;
    private String price;

    public BillItem() {}

    public BillItem(String itemName, String price) {
        this.itemName = itemName;
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
