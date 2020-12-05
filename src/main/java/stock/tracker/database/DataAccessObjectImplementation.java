package stock.tracker.database;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import spark.Service;
import stock.tracker.exceptions.ChocolateQtyUpdatedException;
import stock.tracker.exceptions.EatingChocolateException;
import stock.tracker.mappings.ChocolateMapping;

import java.net.URISyntaxException;
import java.util.List;

import static spark.Spark.port;


public class DataAccessObjectImplementation implements DataAccessObject {
    private final Jdbi jdbi = StockDatabaseConnection.getJdbiDatabaseConnection();

    public DataAccessObjectImplementation() throws URISyntaxException {
        try {
            jdbi.installPlugin(new SqlObjectPlugin());
//            port(StockDatabaseConnection.getHerokuAssignedPort());
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
    public void eatChocolate(String name) throws EatingChocolateException {
        jdbi.useExtension(DataAccessObject.class, dao -> {
            dao.eatChocolate(name);
        });
    }

    @Override
    public void updateStock(String name) throws ChocolateQtyUpdatedException {
        jdbi.useExtension(DataAccessObject.class, dao -> {
            if (dao.getChocolate(name).size() == 1) {
                dao.updateStock(name);
                throw new ChocolateQtyUpdatedException(name);
            }
        });
    }

}
