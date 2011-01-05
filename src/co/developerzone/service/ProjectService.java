package co.developerzone.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import co.developerzone.agile.dao.GenericDAO;
import co.developerzone.agile.dao.GenericJpaDAO;
import co.developerzone.agile.domain.Project;
import co.developerzone.agile.domain.User;

public class ProjectService {
	
	public static int countAll(HttpServletRequest request) {
		GenericDAO<Project> dao = new GenericJpaDAO<Project>(Project.class);
		return dao.countAll();
	}
	
	public static List<Project> findAll(HttpServletRequest request, final int... rowStartIdxAndCount) {
		GenericDAO<Project> dao = new GenericJpaDAO<Project>(Project.class);
		return dao.findAll(rowStartIdxAndCount);
	}

	public static Project save(HttpServletRequest request, final Project entity) {
		GenericDAO<Project> dao = new GenericJpaDAO<Project>(Project.class);
		if(entity.getId() == null) {
			User user = UserService.getLoggedUser(request);
			entity.setOwner(user);
			entity.setCompany(CompanyService.getCompany(request));
			entity.setCreated(new Date());
		}
		return dao.save(entity);
	}

	
}
