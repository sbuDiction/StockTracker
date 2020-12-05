package stock.tracker.exceptions;

public class StockTrackerException extends Exception {
    private final String message;

    public StockTrackerException(){
        this.message = "Sorry something went wrong in the stock tracker backend.";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
