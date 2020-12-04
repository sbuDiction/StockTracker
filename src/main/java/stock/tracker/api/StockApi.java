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

    private Object handle1(Request request, Response response) throws URISyntaxException {

        return chocolateStockTracker.getStock();
    }

    private Object handle2(Request request, Response response) throws URISyntaxException {
        String item = request.queryParams("item");
        String qty = request.queryParams("qty");

        chocolateStockTracker.addToStock(item,Integer.parseInt(qty));
        return "Success";
    }
}
