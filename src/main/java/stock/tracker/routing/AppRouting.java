package stock.tracker.routing;

import stock.tracker.api.StockApi;
import stock.tracker.utils.Serializer;


import static spark.Spark.*;

public class AppRouting {
    StockApi api = new StockApi();

    public AppRouting() {
        get("/api/get", api.getAllStock());
        post("/add_choco", api.addStock());
    }
}
