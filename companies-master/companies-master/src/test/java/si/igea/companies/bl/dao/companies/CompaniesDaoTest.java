package si.igea.companies.bl.dao.companies;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import si.igea.companies.config.TestConfig;
import si.igea.companies.model.Company;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class CompaniesDaoTest {

    @Autowired
    private CompaniesDao companiesDao;

    private Company testCompany;

    @Before
    public void setUp() {
//        testCompany = new Company(1, "Igea");
    }

    @Test
    public void get() {
//        Company company = companiesDao.get(testCompany.getId());
//        Assert.assertEquals(company, testCompany);
    }

}
