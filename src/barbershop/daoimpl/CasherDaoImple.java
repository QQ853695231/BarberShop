package barbershop.daoimpl;

import java.util.List;

import barbershop.model.Vip;

public class CasherDaoImple extends BaseDaoImpl<Vip>{

	public Vip get(Class<Vip> entityclass, String primary) {
		if(primary!=null && entityclass!=null)
		{
			System.out.println("CasherDaoImpl****get****传入的是类型是"+entityclass+"主键是"+primary);
			return (Vip) getSf().getCurrentSession().get(entityclass, primary);
		}
			System.out.println("CasherDaoImp****get****传入参数有Null,entityclass"+entityclass+"primary:"+primary);
			return null;
	}

	public String save(Vip entity) {
		if(entity!=null)
		{	
			System.out.println("CasherDaoImplImpl****save****entity参数正确");
			getSf().getCurrentSession().save(entity);
			return DAOSUCCESS;
		}
		System.out.println("CasherDaoImplImpl****save****参数有Null"+entity);
		return DAOFAILURE;
	}

	public String delete(Vip entity) {
		if(entity!=null)
		{
			System.out.println("CasherDaoImplImpl****delete****entity参数正确");
			getSf().getCurrentSession().delete(entity);
			return DAOSUCCESS;
		}
		System.out.println("CasherDaoImplImpl****delete****参数有Null"+entity);
		return DAOFAILURE;
	}

	public String update(Vip entity) {
			if(entity!=null)
			{
				System.out.println("CasherDaoImplImpl****update****entity参数正确");
				getSf().getCurrentSession().update(entity);
				return DAOSUCCESS;
			}
			System.out.println("CasherDaoImplImpl****update****参数有Null"+entity);
			return DAOFAILURE;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vip> executeQuery(String sql) {
		return getSf().getCurrentSession().createQuery(sql).list();
		
	}

	@Override
	public String saveother(Object obj) {
		if(getSf().getCurrentSession().save(obj)!=null)
		{
			return DAOSUCCESS;
		}
		return DAOFAILURE;
	}

	
	
	

}
