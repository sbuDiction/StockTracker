package stock.tracker.exceptions;

public class ChocolateQtyUpdatedException extends StockTrackerException {
    private final String message;

    public ChocolateQtyUpdatedException(String chocolateName) {
        this.message = "Chocolate: " + chocolateName + " has already been added now updating quantity.";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
