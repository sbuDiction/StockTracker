package stock.tracker.database;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import stock.tracker.exceptions.ChocolateQtyUpdatedException;
import stock.tracker.exceptions.EatingChocolateException;
import stock.tracker.mappings.ChocolateMapping;

import java.util.List;

/**
 * @author Sibusiso Nkosi
 */

public interface DataAccessObject {

    @SqlUpdate("insert into chocolate (name, qty) values (:name, :qty)")
    void createChocolate(@BindBean ChocolateMapping chocolate);

    @SqlQuery("select * from chocolate order by name")
    @RegisterBeanMapper(ChocolateMapping.class)
    List<ChocolateMapping> getAllChocolates();

    @SqlQuery("select * from chocolate where name = ?")
    @RegisterBeanMapper(ChocolateMapping.class)
    List<ChocolateMapping> getChocolate(String name);

    @SqlUpdate("update chocolate set qty = qty -1 where name = ?")
    void eatChocolate(String name)throws EatingChocolateException;

    @SqlUpdate("update chocolate set qty = qty + 1 where name = ?")
    void updateStock(String name)throws ChocolateQtyUpdatedException;
}
