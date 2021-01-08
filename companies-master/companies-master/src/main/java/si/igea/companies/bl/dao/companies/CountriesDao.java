package si.igea.companies.bl.dao.companies;

import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Component;
import si.igea.companies.bl.AbstractJDBCDao;
import si.igea.companies.model.Country;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

@Log4j2
@Component
public class CountriesDao extends AbstractJDBCDao {

    
    public List<Country> getCountries() throws ClassNotFoundException{
		List<Country> lista = new ArrayList<Country>();
    	try {
    		Class.forName("com.mysql.jdbc.Driver");  
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/companiesdb", "admin", "admin");
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("Select * from countries");
			while (rs.next()) {
				Country count = new Country();
				count.setId(rs.getInt("id"));
				count.setName(rs.getString("name"));
				lista.add(count);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return lista;

    }
    
	public Country getCountry(int id) throws ClassNotFoundException{
		Country count = new Country();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/companiesdb", "admin", "admin");
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("Select * from countries where id=" + id + ";");
			while (rs.next()) {
				count.setId(rs.getInt("id"));
				count.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return count;
	}
        
}
