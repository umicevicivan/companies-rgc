package si.igea.companies.bl.dao.companies;

import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Component;
import si.igea.companies.bl.AbstractJDBCDao;
import si.igea.companies.model.Company;
import si.igea.companies.model.Country;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

@Log4j2
@Component
public class CompaniesDao extends AbstractJDBCDao {

//    public List<Company> list() {
//        return Arrays.asList(new Company(1, "Igea"), new Company(2, "Igea holding"),  new Company(3, "Test"));
//    }
//
//    public Company get(Integer id) {
//        return new Company(1, "Igea");
//    }
	
	public List<Company> getCompanies(int page, int perPage) throws ClassNotFoundException {
		List<Company> lista = new ArrayList<Company>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/companiesdb", "admin", "admin");
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(
					"Select * from companies inner join countries on companies.countryId = countries.id limit " + (page-1) + "," + perPage + ";" );
			while (rs.next()) {
				Country count = new Country();
				count.setId(rs.getInt(5));
				count.setName(rs.getString(6));
				Company comp = new Company();
				comp.setId(rs.getInt("id"));
				comp.setName(rs.getString("name"));
				comp.setNumber(rs.getString("number"));
				comp.setCountryId(rs.getInt("countryId"));
				comp.setCountry(count);
				lista.add(comp);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return lista;

	}

	public List<Company> getCompaniesBySearch(String searchString) throws ClassNotFoundException {
		List<Company> lista = new ArrayList<Company>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/companiesdb", "admin", "admin");
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(
					"Select * from companies inner join countries on companies.countryId = countries.id where companies.name like '%"
							+ searchString + "%';");
			while (rs.next()) {
				Country count = new Country();
				count.setId(rs.getInt(5));
				count.setName(rs.getString(6));
				Company comp = new Company();
				comp.setId(rs.getInt("id"));
				comp.setName(rs.getString("name"));
				comp.setNumber(rs.getString("number"));
				comp.setCountryId(rs.getInt("countryId"));
				comp.setCountry(count);
				lista.add(comp);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return lista;
	}

	public boolean insertCompany(Company toInsert) throws ClassNotFoundException {
		boolean done = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/companiesdb", "admin", "admin");
			Statement stat = conn.createStatement();
			String sql = "insert into companies values (" + toInsert.getId() + ", '" + toInsert.getName() + "','"
					+ toInsert.getNumber() + "', " + toInsert.getCountryId() + ");";
			stat.execute(sql);
			done = true;
		} catch (Exception e) {
			System.err.println(e);
		}
		return done;
	}

	public Company getCompany(int id) throws ClassNotFoundException {
		Company comp = new Company();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/companiesdb", "admin", "admin");
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(
					"Select * from companies join countries on companies.countryId = countries.id where companies.id="
							+ id + ";");
			while (rs.next()) {
				Country count = new Country();
				count.setId(rs.getInt(5));
				count.setName(rs.getString(6));
				comp.setId(rs.getInt("id"));
				comp.setName(rs.getString("name"));
				comp.setNumber(rs.getString("number"));
				comp.setCountryId(rs.getInt("countryId"));
				comp.setCountry(count);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return comp;
	}

	public boolean updateCompany(Company toUpdate) throws ClassNotFoundException{
		boolean done = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/companiesdb", "admin", "admin");
			Statement stat = conn.createStatement();
			String sql = "update companies set id=" + toUpdate.getId() + ", name= '" + toUpdate.getName()
					+ "', number='" + toUpdate.getNumber() + "', countryId = " + toUpdate.getCountryId()
					+ " where id = " + toUpdate.getId() + ";";
			stat.execute(sql);
			done = true;
		} catch (Exception e) {
			System.out.println(e);
		}

		return done;
	}

	public int getTotalRecords()  {
		int totalRecords = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/companiesdb", "admin", "admin");
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("Select count(id) from companies");
			while (rs.next()) {
				totalRecords = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println(totalRecords);
		return totalRecords;
	}

}