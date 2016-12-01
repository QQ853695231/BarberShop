package barbershop.daoimpl;



import java.util.List;

import org.hibernate.SessionFactory;

import barbershop.dao.BaseDao;

public class BaseDaoImpl<T>  implements BaseDao<T> {
	public SessionFactory sf ;
    public SessionFactory getSf() {
		return sf;
	}
   
	public void setSf(SessionFactory sf) {
		System.out.println("执行BaseDaoImpl的setsf方法");
		this.sf = sf;
		System.out.println("sf="+sf);
	}

	@Override
	public T get(Class<T> entityclass, String primary) {
		return null;
	}
	@Override
	public String save(T entity) {
		return null;
	}

	@Override
	public String delete(T entity) {
		return null;
	}

	@Override
	public String update(T entity) {
		return null;
	}

	@Override
	public List<T> executeQuery(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveother(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
