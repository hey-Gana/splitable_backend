package splitable.backend.billscanner.model;
import java.util.List;

public class BillResponse {
    private List<BillItem> items;
    private double subtotal;
    private double tax;
    private double total;

    //constructor
    public BillResponse() {
    }

    public BillResponse(List<BillItem> items, double subtotal, double tax, double total) {
        this.items = items;
        this.subtotal = subtotal;
        this.tax = tax;
        this.total = total;
    }

    // Getter for items
    public List<BillItem> getItems() {
        return items;
    }

    // Setter for items
    public void setItems(List<BillItem> items) {
        this.items = items;
    }

    // Getter for subtotal
    public double getSubtotal() {
        return subtotal;
    }

    // Setter for subtotal
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    // Getter for tax
    public double getTax() {
        return tax;
    }

    // Setter for tax
    public void setTax(double tax) {
        this.tax = tax;
    }

    // Getter for total
    public double getTotal() {
        return total;
    }

    // Setter for total
    public void setTotal(double total) {
        this.total = total;
    }
}
