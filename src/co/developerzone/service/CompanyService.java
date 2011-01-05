package co.developerzone.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import co.developerzone.agile.dao.GenericDAO;
import co.developerzone.agile.dao.GenericJpaDAO;
import co.developerzone.agile.domain.Company;
import co.developerzone.agile.domain.User;

public class CompanyService {
	
	public static int countAll(HttpServletRequest request) {
		GenericDAO<Company> dao = new GenericJpaDAO<Company>(Company.class);
		return dao.countAll();
	}
	
	public static List<Company> findAll(HttpServletRequest request, final int... rowStartIdxAndCount) {
		GenericDAO<Company> dao = new GenericJpaDAO<Company>(Company.class);
		return dao.findAll(rowStartIdxAndCount);
	}

	public static Company save(HttpServletRequest request, final Company entity) {
		GenericDAO<Company> dao = new GenericJpaDAO<Company>(Company.class);
		if(entity.getId() == null) {
			User user = UserService.getLoggedUser(request);
			entity.setOwner(user);
			entity.setCreated(new Date());
		}
		return dao.save(entity);
	}
	/**
	 * MŽtodo que retorna la compa–ia de acuerdo a la URL del request
	 * @param request
	 * @return
	 */
	// TODO Agregar logica para devolver la compa–ia de acuerdo a la URL del request
	public static Company getCompany(HttpServletRequest request) {
		GenericDAO<Company> dao = new GenericJpaDAO<Company>(Company.class);
		return dao.findById(1); // HARDCODE
	}
	
}
