package si.igea.companies.conf;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import si.igea.companies.conf.prop.AppProps;

import javax.sql.DataSource;
import java.io.IOException;

@Log4j2
@PropertySource("classpath:companies.app.properties")
@EnableWebMvc
@Configuration
@ComponentScan({"si.igea.companies"})
public class SpringWebConfig implements WebMvcConfigurer {

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private AppProps appProps;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }

    @Bean
    public PropertiesFactoryBean sqlQueries() {
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        try {
            bean.setLocations(ResourcePatternUtils.getResourcePatternResolver(resourceLoader).getResources("classpath:/sql/*.xml"));
        } catch (IOException e) {
            log.error("", e);
        }
        return bean;
    }

    @Bean(name = "dataSource", destroyMethod = "")
    public DataSource dataSource() {
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        return dsLookup.getDataSource(appProps.getDatasourceJndiName());
    }
}