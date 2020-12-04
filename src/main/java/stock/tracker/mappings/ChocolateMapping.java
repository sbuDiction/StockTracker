package stock.tracker.mappings;

import java.beans.ConstructorProperties;

public class ChocolateMapping {
    private String name;
    private int qty;

    public ChocolateMapping() {
        super();
    }

    @ConstructorProperties({"name", "qty"})
    public ChocolateMapping(String name, int qty) {
        this.name = name;
        this.qty = qty;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public int getQty() {
        return qty;
    }

    @Override
    public String toString() {
        return name;
    }
}
