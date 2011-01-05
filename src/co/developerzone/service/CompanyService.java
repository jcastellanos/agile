package co.developerzone.service;

import java.util.List;

import co.developerzone.agile.dao.GenericDAO;
import co.developerzone.agile.dao.GenericJpaDAO;
import co.developerzone.agile.domain.Company;

public class CompanyService {
	
	public static int countAll() {
		GenericDAO<Company> dao = new GenericJpaDAO<Company>(Company.class);
		return dao.countAll();
	}
	
	public static List<Company> findAll(final int... rowStartIdxAndCount) {
		GenericDAO<Company> dao = new GenericJpaDAO<Company>(Company.class);
		return dao.findAll(rowStartIdxAndCount);
	}

	public static Company save(final Company entity) {
		GenericDAO<Company> dao = new GenericJpaDAO<Company>(Company.class);
		return dao.save(entity);
	}

	
}
