package stock.tracker.api;

import spark.Request;
import spark.Response;
import spark.Route;
import stock.tracker.stocks.ChocolateStockTracker;

import java.net.URISyntaxException;

public class StockApi {
    ChocolateStockTracker chocolateStockTracker = new ChocolateStockTracker();

    public Route getAllStock() {
        return this::handle1;
    }

    public Route addStock() {
        return this::handle2;
    }

    public Route eatChocolate() {
        return this::handle3;
    }

    private Object handle1(Request request, Response response) throws URISyntaxException {
        return chocolateStockTracker.getStock();
    }

    private Object handle2(Request request, Response response) throws URISyntaxException {
        String item = request.queryParams("item");
        String qty = request.queryParams("qty");

        if (chocolateStockTracker.getChocolate(item).size() == 1) {
            chocolateStockTracker.updateStock(item);
            System.out.println(chocolateStockTracker.getMessage());
        } else {
            chocolateStockTracker.addToStock(item, Integer.parseInt(qty));
        }
        response.redirect("index.html");
        return null;
    }

    private Object handle3(Request request, Response response) {
        request.queryMap().toMap().keySet().forEach(key -> {
            try {
                if (chocolateStockTracker.getChocolate(key).size() == 1) {
                    chocolateStockTracker.updateStock(key);
                } else {
                    chocolateStockTracker.eatChocolate(key);
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });
        response.redirect("index.html");
        return null;
    }

}
