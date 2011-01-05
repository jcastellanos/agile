package co.developerzone.service;

import java.util.List;

import co.developerzone.agile.dao.GenericDAO;
import co.developerzone.agile.dao.GenericJpaDAO;
import co.developerzone.agile.domain.Project;

public class ProjectService {
	
	public static int countAll() {
		GenericDAO<Project> dao = new GenericJpaDAO<Project>(Project.class);
		return dao.countAll();
	}
	
	public static List<Project> findAll(final int... rowStartIdxAndCount) {
		GenericDAO<Project> dao = new GenericJpaDAO<Project>(Project.class);
		return dao.findAll(rowStartIdxAndCount);
	}

	public static Project save(final Project entity) {
		GenericDAO<Project> dao = new GenericJpaDAO<Project>(Project.class);
		return dao.save(entity);
	}

	
}
