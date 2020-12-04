package stock.tracker.stocks;

import stock.tracker.database.DataAccessObjectImplementation;
import stock.tracker.mappings.ChocolateMapping;
import stock.tracker.utils.Serializer;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class ChocolateStockTracker {

    public void addToStock(String chocolate, int qty) throws URISyntaxException {
        new DataAccessObjectImplementation().createChocolate(new ChocolateMapping(chocolate, qty));
    }

    public List<String> getStock() throws URISyntaxException {
        List<String> jsonObject = new ArrayList<>();
        new DataAccessObjectImplementation().getAllChocolates().forEach(chocolate -> {
            String json = new Serializer().fromObjectToJson(new ChocolateMapping(chocolate.getName(), chocolate.getQty()));
            jsonObject.add(json);
        });
        return jsonObject;
    }

    public List<ChocolateMapping> getChocolate(String chocolateName) throws URISyntaxException {
        return new DataAccessObjectImplementation().getChocolate(chocolateName);
    }

//    public void eatChocolate(String name) throws URISyntaxException {
//        new DataAccessObjectImplementation().update(name);
//    }
}
