package si.igea.companies.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import si.igea.companies.bl.dao.companies.CompaniesDao;
import si.igea.companies.bl.dao.companies.CountriesDao;
import si.igea.companies.model.Company;
import si.igea.companies.model.Country;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@ControllerAdvice
@RequestMapping("/companies")
public class CompaniesController {

	@Autowired
	private CompaniesDao companiesDao;
	@Autowired
	private CountriesDao countriesDao;
	
	private int totalRecords;
	private int perPage = 5;
	private List<Integer> totalPagesList = new ArrayList<Integer>();
	private int totalPages = 0;

//	@RequestMapping(value = "/list", method = RequestMethod.GET)
//	public String listCompanies(@RequestParam(required = false) String searchString, Model model) {
//
//        List<Company> companyList = companiesDao.list();
//		totalRecords = companiesDao.getTotalRecords();
//
//		if(searchString == null) {
//			try {
//				List<Company> companyList = companiesDao.getCompanies();
//
//				model.addAttribute("companies", companyList);
//			} catch (Exception e) {
//				System.out.println("greska: " + e);
//			}
//		}
//		else {
//			try {
//				List<Company> companyList = companiesDao.getCompaniesBySearch(searchString);
//				model.addAttribute("companies", companyList);
//
//			} catch (Exception e) {
//				System.out.println("greska: " + e);
//			}
//		}
//
//		return "companies/companies";
//	}
	
	@RequestMapping(value = "/list/{page}", method = RequestMethod.GET)
	public String listCompanies(@PathVariable("page") int page, @RequestParam(required = false) String searchString, Model model) {

		totalRecords = companiesDao.getTotalRecords();
		totalPages = (totalRecords / perPage);
		if(totalRecords % perPage != 0) {
			totalPages++;
		}
		totalPagesList.clear();
		for (int i = 1; i <= totalPages; i++) {
			totalPagesList.add(i);
		}
		model.addAttribute("pages", totalPagesList);
		
        if(page == 1){}    
        else{    
            page = (page - 1) * perPage + 1;    
        }  

		if(searchString == null || searchString == "") {
			try {
				List<Company> companyList = companiesDao.getCompanies(page, perPage);

				model.addAttribute("companies", companyList);
			} catch (Exception e) {
				System.out.println("Greska: " + e);
			}
		}
		else {
			try {
				List<Company> companyList = companiesDao.getCompaniesBySearch(searchString);
				model.addAttribute("companies", companyList);

			} catch (Exception e) {
				System.out.println("Greska: " + e);
			}
		}

		return "companies/companies";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addCompanies(@ModelAttribute("company") Company com, BindingResult result, ModelMap model) {
		try {
			List<Country> countryList = countriesDao.getCountries();
			int nextId = companiesDao.getNextId();
			model.addAttribute("nextId", nextId);
			model.addAttribute("countries", countryList);
		} catch (Exception e) {
			System.out.println("greska: " + e);
		}

		return "companies/company-add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String submit(@ModelAttribute("company") Company com, BindingResult result, ModelMap model) {
		boolean done = false;
		try {
			Country count = countriesDao.getCountry(com.getCountryId());
			com.setCountry(count);
			int nextId = companiesDao.getNextId();
			if( nextId == 0) {
				com.setId(nextId+1);
			}else {
				com.setId(nextId);
			}
			done = companiesDao.insertCompany(com);
		} catch (Exception e) {
			System.out.println(e);
		}
		if (done != false) {
			System.out.println("Bravo");
		}

		return "redirect:/companies/list/1";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editCompany(@PathVariable("id") int id, @ModelAttribute("company") Company com, BindingResult result, ModelMap model) {
		try {
			Company comp = companiesDao.getCompany(id);
			List<Country> countryList = countriesDao.getCountries();
			model.addAttribute("countries", countryList);
			model.addAttribute("comp", comp);
		} catch (Exception e) {
			System.out.println("greska: " + e);
		}

		return "companies/company-edit";
	}
	
	@RequestMapping(value = "/editComp/{id}", method = RequestMethod.POST)
	public String edit(@PathVariable("id") int id, @ModelAttribute("company") Company com, BindingResult result, ModelMap model) {
		com.setId(id);
		boolean done = false;
		try {
			done = companiesDao.updateCompany(com);
		} catch (Exception e) {
			System.out.println(e);
		}
		if (done != false) {
			System.out.println("Bravo");
		}

		return "redirect:/companies/list/1";
	}

}
