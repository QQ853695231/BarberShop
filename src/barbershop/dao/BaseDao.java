package barbershop.dao;

import java.util.List;

public interface BaseDao<T> {
	public String DAOSUCCESS="success";
	public String DAOFAILURE="failure";
	 T get(Class<T> entityclass,String primary);
	 String save( T entity);
	 String delete(T entity);
	 String update( T entity);
	 List<T> executeQuery(String sql);
	 public String saveother(Object obj);
}