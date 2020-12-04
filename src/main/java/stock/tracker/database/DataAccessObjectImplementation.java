package stock.tracker.database;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import stock.tracker.mappings.ChocolateMapping;

import java.net.URISyntaxException;
import java.util.List;

import static spark.Spark.port;

public class DataAccessObjectImplementation implements DataAccessObject {
    private final Jdbi jdbi = StockDatabaseConnection.getJdbiDatabaseConnection();

    public DataAccessObjectImplementation() throws URISyntaxException {
        try {

            jdbi.installPlugin(new SqlObjectPlugin());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createChocolate(ChocolateMapping chocolate) {
        jdbi.useExtension(DataAccessObject.class, dao -> dao.createChocolate(chocolate));
    }

    @Override
    public List<ChocolateMapping> getAllChocolates() {
        return jdbi.withExtension(DataAccessObject.class, DataAccessObject::getAllChocolates);
    }

    @Override
    public List<ChocolateMapping> getChocolate(String name) {
        return jdbi.withExtension(DataAccessObject.class, dao -> dao.getChocolate(name));
    }


    @Override
    public void updateQty(int qty, String name) {
        jdbi.useExtension(DataAccessObject.class, dao -> {
            if (dao.getChocolate(name).size() != 0) {

            }
            dao.updateQty(qty, name);
        });
    }

}
