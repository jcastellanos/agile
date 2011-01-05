package co.developerzone.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import co.developerzone.agile.dao.GenericDAO;
import co.developerzone.agile.dao.GenericJpaDAO;
import co.developerzone.agile.domain.User;

public class UserService {

	/**
	 * Retorna el Usuario logueado
	 * @param httpServletRequest
	 * @return
	 */
	public static User getLoggedUser(HttpServletRequest httpServletRequest) {
		User user = null;
		GenericDAO<User> dao = new GenericJpaDAO<User>(User.class);
		if(httpServletRequest != null && httpServletRequest.getUserPrincipal() != null) {
			List<User> usuario = dao.findByProperty("userName", httpServletRequest.getUserPrincipal().getName());
			if(usuario.size() == 1) {
				user = usuario.get(0);
			}
			
		}
		if(user == null) {
			//throw new RuntimeException("Error al consultar el usuario logueado");
			user = dao.findById(1);

		}
		return user;
	}
}
