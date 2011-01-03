package co.developerzone.agile.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericDAO<T> {
	Class<T> getEntityClass();
	public T findById(final Object id);
	public List<T> findAll(final int... rowStartIdxAndCount); // myeclipse
	//List<T> findByExample(final T exampleInstance);
	//List<T> findByNamedQuery(final String queryName, Object... params);
	//List<T> findByNamedQueryAndNamedParams(final String queryName, final Map<String, ?extends Object> params);
	public int countAll();
	//int countByExample(final T exampleInstance);
	public T save(final T entity);
	public void delete(final Integer id);
	public void delete(final String id);
	//public Presupuesto update();
	public List<T> findByProperty(final String propertyName, final Object value, final int... rowStartIdxAndCount); // myeclipse
	public List<T> findByProperty(final Map<String, Object> properties, int... rowStartIdxAndCount);
	
	public int countByProperty(final String propertyName, final Object value);
	//query JPA
	public List<T> query(final String sentenceJpa);
	//query JPA
	public List<T> query(final String sentenceJpa,Object ... params);
	//query sql
	public List<T> queryNative(String sentenceSql);
	//query sql
	public List<T> queryNative(String sentenceSql,Object ... params);
	
	
}
