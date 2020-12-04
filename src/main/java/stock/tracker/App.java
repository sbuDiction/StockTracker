package stock.tracker;

import stock.tracker.routing.AppRouting;

import java.net.URISyntaxException;

import static spark.Spark.staticFiles;

public class App {
    public static void main(String[] args) throws URISyntaxException {


        try {
            staticFiles.location("/client");
            new AppRouting();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
