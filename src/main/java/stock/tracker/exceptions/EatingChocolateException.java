package stock.tracker.exceptions;

public class EatingChocolateException extends StockTrackerException{
    private final String message;

    public EatingChocolateException(String name){
        this.message = "You ate: " + name;
    }
}
