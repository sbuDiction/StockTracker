package stock.tracker.stocks;

import stock.tracker.database.DataAccessObjectImplementation;
import stock.tracker.exceptions.ChocolateQtyUpdatedException;
import stock.tracker.exceptions.EatingChocolateException;
import stock.tracker.mappings.ChocolateMapping;
import stock.tracker.utils.ObjectToJson;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class ChocolateStockTracker {
    private String message;

    public void addToStock(String chocolate, int qty) throws URISyntaxException {
        new DataAccessObjectImplementation().createChocolate(new ChocolateMapping(chocolate, qty));
    }

    public List<String> getStock() throws URISyntaxException {
        List<String> jsonObject = new ArrayList<>();
        new DataAccessObjectImplementation().getAllChocolates().forEach(chocolate -> {
            String json = new ObjectToJson().fromObjectToJson(new ChocolateMapping(chocolate.getName(), chocolate.getQty()));
            jsonObject.add(json);
        });
        return jsonObject;
    }

    public List<ChocolateMapping> getChocolate(String chocolateName) throws URISyntaxException {
        return new DataAccessObjectImplementation().getChocolate(chocolateName);
    }

    public void eatChocolate(String name) throws URISyntaxException {
        try {
            new DataAccessObjectImplementation().eatChocolate(name);
        } catch (EatingChocolateException e) {
            setMessage(e.getMessage());
        }
    }

    public void updateStock(String name) throws URISyntaxException {
        try {
            new DataAccessObjectImplementation().updateStock(name);
        } catch (ChocolateQtyUpdatedException e) {
            setMessage(e.getMessage());
        }
    }

    private void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
