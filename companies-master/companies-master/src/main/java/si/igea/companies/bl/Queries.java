package si.igea.companies.bl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Properties;

@Repository
@Log4j2
public class Queries {

    @Autowired
    private Properties sqlQueries;

    public String getSQL(String key) {
        return sqlQueries.getProperty(key);
    }
}