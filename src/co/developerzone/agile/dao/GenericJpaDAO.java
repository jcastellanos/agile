package co.developerzone.agile.dao;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.zkoss.zkplus.jpa.JpaUtil;

public class GenericJpaDAO<T> implements GenericDAO<T> {
	
	private final Class<T> persistentClass;
	
	public GenericJpaDAO(final Class<T> persistentClass) {
		super();
		this.persistentClass = persistentClass;
	}
	
	protected EntityManager getEntityManager() {
		return JpaUtil.getEntityManager();
	}
	
	public Class<T> getEntityClass() {
		return persistentClass;
	}

	public int countAll() {
		EntityManagerHelper.log("count all " + persistentClass.getName() + " instances", Level.INFO, null);
		try {
			final String queryString = "select count(p.id) from " + persistentClass.getName() + " p";
			Query query = getEntityManager().createQuery(queryString);
			Long total = (Long) query.getSingleResult();
			return Integer.parseInt(total.toString());
		} catch (RuntimeException re) {
			EntityManagerHelper.log("count all failed", Level.SEVERE, re);
			throw re;
		}
	}

	
	/**
	 * Delete a persistent Area entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AreaDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Area entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(final Integer id) {
		EntityManagerHelper.log("deleting " + persistentClass.getName() + " instance", Level.INFO, null);
		if(id != null) {
			try {				
				getEntityManager().getTransaction().begin();
				T entity = getEntityManager().getReference(persistentClass, id);
				getEntityManager().remove(entity);
				getEntityManager().getTransaction().commit();
				EntityManagerHelper.log("delete successful", Level.INFO, null);
			} catch (RuntimeException re) {
				EntityManagerHelper.log("delete failed", Level.SEVERE, re);
				throw re;
			}
		}
		
	}
	
	public void delete(final String id) {
		EntityManagerHelper.log("deleting " + persistentClass.getName() + " instance", Level.INFO, null);
		if(id != null) {
			try {				
				getEntityManager().getTransaction().begin();
				T entity = getEntityManager().getReference(persistentClass, id);
				getEntityManager().remove(entity);
				getEntityManager().getTransaction().commit();
				EntityManagerHelper.log("delete successful", Level.INFO, null);
			} catch (RuntimeException re) {
				EntityManagerHelper.log("delete failed", Level.SEVERE, re);
				throw re;
			}
		}
		
	}

	
	/**
	 * Find all Area entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Area> all Area entities
	 */
	public List<T> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all " + persistentClass.getName() + " instances", Level.INFO, null);
		try {
			final String queryString = "select model from "+ persistentClass.getName() + " model order by id";
			Query query = getEntityManager().createQuery(queryString);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}
				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}		
	}

	
	public T findById(final Object id) {
		EntityManagerHelper.log("finding " + persistentClass.getName() + " instance with id: " + id, Level.INFO, null);
		try {
			T instance = getEntityManager().find(persistentClass, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Area entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Area property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Area> found by query
	 */
	public List<T> findByProperty(final String propertyName, Object value,
			int... rowStartIdxAndCount) {
		// TODO Auto-generated method stub
		EntityManagerHelper.log("finding " + persistentClass.getName() + " instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from " + persistentClass.getName() + " model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}
	
	/**
	 * Retorna todas las  entities aplicando busquedas por los properties utilizando AND
	 * 
	 * @param propertyName
	 *            the name of the Area property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Area> found by query
	 */
	public List<T> findByProperty(final Map<String, Object> properties,
			int... rowStartIdxAndCount) {
		// TODO Auto-generated method stub
		String log = "finding " + persistentClass.getName() + " instance with properties: ";
		for(String key: properties.keySet()) {
			log += key + ", value: " + properties.get(key);
		}
		EntityManagerHelper.log(log, Level.INFO, null);
		try {
			String queryString = "select model from " + persistentClass.getName() + " model where ";
			int i = 0;
			for(String key: properties.keySet()) {
				if(i == 0) {
					queryString += "model." + key + "= :propertyValue" + i;
				}
				else {
					queryString += " and model." + key + "= :propertyValue" + i;
				}
				i++;
			}
			Query query = getEntityManager().createQuery(queryString);
			i = 0;
			for(String key: properties.keySet()) {
				query.setParameter("propertyValue" + i, properties.get(key));
				i++;
			}
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}
	
	
	public int countByProperty(final String propertyName, Object value) {
		EntityManagerHelper.log("counting " + persistentClass.getName() + " instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select count(model.id) from " + persistentClass.getName() + " model where model."
			+ propertyName + "= :propertyValue";
			//final String queryString = "select count(p.id) from " + persistentClass.getName() + " p";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			Long total = (Long) query.getSingleResult();
			return Integer.parseInt(total.toString());
		} catch (RuntimeException re) {
			EntityManagerHelper.log("count all failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Area entity and return it or a copy of it to
	 * the sender. A copy of the Area entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = AreaDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Area entity to update
	 * @return Area the persisted Area entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public T save(final T entity) {
		EntityManagerHelper.log("save or update " + persistentClass.getName() + " instance", Level.INFO, null);
		try {
			getEntityManager().getTransaction().begin();
			T result = getEntityManager().merge(entity);
			getEntityManager().getTransaction().commit();
			EntityManagerHelper.log("save or update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save or update failed", Level.SEVERE, re);
			throw re;
		}	
	}
	
	public List<T> query(String sentenceJpa) {
		EntityManagerHelper.log("query Jpa " + persistentClass.getName() + " instance", Level.INFO, null);
		try {
			Query query = getEntityManager().createQuery(sentenceJpa);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("query failed", Level.SEVERE, re);
			throw re;
		}	
	}
	
	
	public List<T> query(String sentenceJpa, Object... objects) {
		EntityManagerHelper.log("query Jpa " + persistentClass.getName() + " instance", Level.INFO, null);
		try {
			Query query = getEntityManager().createQuery(sentenceJpa);
			for(int i=0;i<objects.length;i++){
				int j=i+1;
				query.setParameter(j, objects[i]);
			}			
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("query failed", Level.SEVERE, re);
			throw re;
		}	
	}
	
	public List<T> queryNative(String sentenceSql) {
		EntityManagerHelper.log("query Jpa " + persistentClass.getName() + " instance", Level.INFO, null);
		try {
			Query query = getEntityManager().createNativeQuery(sentenceSql);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("query failed", Level.SEVERE, re);
			throw re;
		}	
	}
	
	public List<T> queryNative(String sentenceSql, Object... objects) {
		EntityManagerHelper.log("query Jpa " + persistentClass.getName() + " instance", Level.INFO, null);
		try {
			Query query = getEntityManager().createNativeQuery(sentenceSql);
			for(int i=0;i<objects.length;i++){
				int j=i+1;
				query.setParameter(j, objects[i]);
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("query failed", Level.SEVERE, re);
			throw re;
		}	
	}
	
}
