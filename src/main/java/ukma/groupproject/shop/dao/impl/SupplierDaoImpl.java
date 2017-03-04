package ukma.groupproject.shop.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ukma.groupproject.shop.dao.SupplierDao;
import ukma.groupproject.shop.model.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SupplierDaoImpl implements SupplierDao {

    private static final String SQL_SELECT_SUPPLIERS = "SELECT id, name FROM sh_suppliers s";
    private static final String SQL_SELECT_SUPPLIER_BY_ID = SQL_SELECT_SUPPLIERS + " WHERE id=?";
    private static final String SQL_INSERT_SUPPLIER =  "INSERT INTO sh_suppliers (name) VALUES (?)";
    private static final String SQL_UPDATE_SUPPLIER = "UPDATE sh_suppliers SET name=? WHERE id=?";
    private static final String SQL_DELETE_SUPPLIER = "DELETE FROM sh_suppliers WHERE id=?";
    private static final String SQL_SELECT_SUPPLIERS_BY_ITEM = SQL_SELECT_SUPPLIERS
            + " INNER JOIN sh_suppliers_items si ON s.id=si.supplier_id"
            + " WHERE si.item_id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Supplier find(Long key) {
        List<Supplier> suppliers = jdbcTemplate.query(SQL_SELECT_SUPPLIER_BY_ID, new SupplierRowMapper(), key);
        return suppliers.isEmpty() ? null : suppliers.get(0);
    }

    @Override
    public List<Supplier> findAll() {
        return null;
    }

    @Override
    public void persist(Supplier entity) {

    }

    @Override
    public void update(Supplier entity) {

    }

    @Override
    public void delete(Long key) {

    }

    private static class SupplierRowMapper implements RowMapper<Supplier> {

        @Override
        public Supplier mapRow(ResultSet rs, int i) throws SQLException {
            Supplier supplier = new Supplier();
            supplier.setId(rs.getLong("id"));
            supplier.setName(rs.getString("name"));
            return supplier;
        }
    }
}
