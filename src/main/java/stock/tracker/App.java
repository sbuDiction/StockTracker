package stock.tracker;

import stock.tracker.database.StockDatabaseConnection;
import stock.tracker.routing.AppRouting;

import java.net.URISyntaxException;

import static spark.Spark.port;
import static spark.Spark.staticFiles;

public class App {
    public static void main(String[] args) throws URISyntaxException {
        try {
            port(StockDatabaseConnection.getHerokuAssignedPort());
            staticFiles.location("/client");
            new AppRouting();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
