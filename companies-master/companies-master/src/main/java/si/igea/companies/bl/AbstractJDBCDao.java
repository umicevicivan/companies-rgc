package si.igea.companies.bl;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcInsertOperations;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

public abstract class AbstractJDBCDao {

    @Autowired
    protected DataSource datasource;

    @Getter
    @Autowired
    protected Queries queries;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbcTemplate;
    private TransactionTemplate transactionTemplate;

    @Autowired
    public void createNamedParameterJdbcTemplate(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Autowired
    public void createJdbcTemplate(@Qualifier("dataSource") DataSource myDatasource) {
        jdbcTemplate = new JdbcTemplate(myDatasource);
    }

    @Autowired
    public void createTransactionTemplate() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(datasource);
        transactionTemplate = new TransactionTemplate(transactionManager);
    }

    protected NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return namedParameterJdbcTemplate;
    }

    protected JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    protected TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    /**
     * Must be created for every insert.
     */
    protected SimpleJdbcInsertOperations getSimpleJdbcInsert(String schema, String tableName, String primaryKeyColumn, MapSqlParameterSource sqlParameterSource) {
        String[] columnNames = sqlParameterSource.getValues().keySet().toArray(new String[sqlParameterSource.getValues().size()]);
        return new SimpleJdbcInsert(datasource).withSchemaName(schema).withTableName(tableName).usingGeneratedKeyColumns(primaryKeyColumn).usingColumns(columnNames)
                .withoutTableColumnMetaDataAccess();
    }
}