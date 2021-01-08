package si.igea.companies.config;

import org.apache.taglibs.standard.tag.common.sql.DataSourceWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import si.igea.companies.bl.Queries;
import si.igea.companies.bl.dao.companies.CompaniesDao;

import javax.sql.DataSource;

@ComponentScan("si.igea.companies.bl.dao")
@ContextConfiguration
@Configuration
public class TestConfig {

    @Bean
    public CompaniesDao companiesDao() {
        return new CompaniesDao();
    }

    @Bean
    public Queries queries() {
        return new Queries();
    }

    @Bean
    public DataSource dataSource() {
        // TODO create real DS
        return new DataSourceWrapper();
    }
}
