package barbershop.daoimpl;


import java.util.List;

import org.hibernate.Query;

import barbershop.model.ConsumerCost;
import barbershop.model.Employee;
import barbershop.model.Salary;
import barbershop.model.Vip;

public class EmployAndBossDaoImpl extends BaseDaoImpl<Employee> {
	
	@Override
	public Employee get(Class<Employee> entityclass, String primary) {
		if(primary!=null && entityclass!=null)
		{
			System.out.println("UserDaoImpl****get****方法：entityclass和primary正确！");
			return  (Employee) getSf().openSession().get(entityclass, primary);
		}
			System.out.println("UserDaoImpl****get****方法参数有null,entityclass"+entityclass+"primary:"+primary);
			return null;
	}

	@Override
	public String save(Employee entity) {
		if(entity!=null)
		{	
			System.out.println("UserDaoImpl****save****方法中参数entity正确");
			System.out.println(entity.getPassword()+entity.getPosition()+entity.getRealname());
			getSf().getCurrentSession().save(entity);
			return DAOSUCCESS;
		}
		System.out.println("UserDaoImpl****save****方法中参数entity为空"+entity);
		return DAOFAILURE;
	}

	@Override
	public String delete(Employee entity) {
		if(entity!=null)
		{
			System.out.println("UserDaoImpl****save****方法中参数entity正确");
			getSf().getCurrentSession().delete(entity);
			return DAOSUCCESS;
		}
	return DAOFAILURE;
		
	}

	@Override
	public String update(Employee entity) {
		if(entity!=null)
		{
			System.out.println("UserDaoImpl****save****方法中参数entity正确");
			getSf().getCurrentSession().update(entity);
			return DAOSUCCESS;
		}
		System.out.println("UserDaoImpl****save****方法中参数entity为空");
		return DAOFAILURE;
	}

	@Override
	public List<Employee> executeQuery(String sql) {
		// TODO Auto-generated method stub
		return null;
	}
	@SuppressWarnings("unchecked")
	public List<ConsumerCost> queryConsumerCost(String query,int page)
	{
		Query usercostquery= getSf().getCurrentSession().createQuery(query);
		if(page==0)
		{
			return usercostquery.list();
		}
		else if(page==1)
				
		{
			usercostquery.setFirstResult(0);
			usercostquery.setMaxResults(10);
			return usercostquery.list();
		}
		else {
			{
				usercostquery.setFirstResult((page-1)*10);
				usercostquery.setMaxResults(10);
				return usercostquery.list();
			}
		}
	}
	@Override
	public String saveother(Object obj) {
		if(sf.getCurrentSession().save(obj)!=null)
		  return DAOSUCCESS;
		else
			return  DAOFAILURE;
	}
	@SuppressWarnings("unchecked")
	public List<Vip> queryVipTransactByCondition(String sql)
	{
		return getSf().getCurrentSession().createQuery(sql).list();
	}
	@SuppressWarnings("unchecked")
	public List<Employee> listAllEmployee()
	{
		return getSf().getCurrentSession().createCriteria(Employee.class).list();
	}
	@SuppressWarnings("unchecked")
	public List<Vip> listAllVip(){
		return getSf().getCurrentSession().createCriteria(Vip.class).list();
		
	}
	public String deleteVip(Object deletedvip)
	{
		getSf().getCurrentSession().delete(deletedvip);
		return DAOSUCCESS;
	}
	@SuppressWarnings("unchecked")
	public List<Salary> querycondition(String condition)
	{
		return getSf().getCurrentSession().createQuery(condition).list();
	}
	public Object queryToalMoney(String sql)
	{
		Object totalmoney=getSf().getCurrentSession().createQuery(sql).uniqueResult();
		
		return totalmoney;
	}
}
