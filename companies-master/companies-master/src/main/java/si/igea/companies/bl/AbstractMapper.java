package si.igea.companies.bl;


import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.RowMapper;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

@Log4j2
public abstract class AbstractMapper<T> implements RowMapper<T> {

    private ResultSet rs;

    public AbstractMapper() {
        super();
    }

    protected abstract T mapRow() throws SQLException;

    @Override
    public T mapRow(ResultSet rs, int rowNum) throws SQLException {

        this.rs = rs;

        return mapRow();
    }

    public int getRowNum() throws SQLException {
        return rs.getRow();
    }

    public ResultSet getResultSet() {
        return rs;
    }

    protected Integer getInteger(String columnLabel) throws SQLException {
        Integer value = rs.getInt(columnLabel);
        return rs.wasNull() ? null : value;
    }

    protected Long getLong(String columnLabel) throws SQLException {
        Long value = rs.getLong(columnLabel);
        return rs.wasNull() ? null : value;
    }

    protected Double getDouble(String columnLabel) throws SQLException {
        Double value = rs.getDouble(columnLabel);
        return rs.wasNull() ? null : value;
    }

    protected Byte getByte(String columnLabel) throws SQLException {
        Byte value = rs.getByte(columnLabel);
        return rs.wasNull() ? null : value;
    }

    protected Float getFloat(String columnLabel) throws SQLException {
        Float value = rs.getFloat(columnLabel);
        return rs.wasNull() ? null : value;
    }

    protected String getString(String columnLabel) throws SQLException {
        return rs.getString(columnLabel);
    }

    protected Date getDate(String columnLabel) throws SQLException {
        return rs.getDate(columnLabel);
    }

    protected Timestamp getTimestamp(String columnLabel) throws SQLException {
        return rs.getTimestamp(columnLabel);
    }

    protected Boolean getBoolean(String columnLabel) throws SQLException {
        return rs.getBoolean(columnLabel);
    }

    protected Boolean getBooleanFromInt(String columnLabel) throws SQLException {
        return rs.getInt(columnLabel) == 1;
    }

    protected byte[] getBytes(String columnLabel) throws SQLException {
        return rs.getBytes(columnLabel);
    }

    protected InputStream getBinaryStream(String columnLabel) throws SQLException {
        return rs.getBinaryStream(columnLabel);
    }
}