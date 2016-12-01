package barbershop.serviceimpl;


import java.util.List;
import java.util.function.Consumer;

import javax.transaction.Transactional;

import org.apache.struts2.ServletActionContext;

import barbershop.daoimpl.EmployAndBossDaoImpl;
import barbershop.model.ConsumerCost;
import barbershop.model.Employee;
import barbershop.model.Salary;
import barbershop.model.Vip;
import net.sf.json.JSONArray;
@Transactional
public class  EmployeeAndBossServiceImpl {
	public final String SERVICESUCC="success";
	public final String SERVICEFAILURE="failure";
	private EmployAndBossDaoImpl EABDao;
	public EmployAndBossDaoImpl getEABDao() {
		return EABDao;
	}

	public void setEABDao(EmployAndBossDaoImpl eABDao) {
		EABDao = eABDao;
	}
	public void deleteUserCost(ConsumerCost cost)
	{
		EABDao.getSf().getCurrentSession().delete(cost);
	}
	public JSONArray  getUserCostdata(int page)
	{
		String sqlinsession=ServletActionContext.getRequest().getSession().getAttribute("querysql").toString();
		
		List<ConsumerCost> pageinfo=EABDao.queryConsumerCost(sqlinsession, page);
		System.out.println("查询出来的数据"+pageinfo.size());
		return JSONArray.fromObject(pageinfo);
	}
	public int TotalUserCost(String querysql)
	{
		int totalsize= EABDao.queryConsumerCost(querysql, 0).size();
		if(totalsize%10==0)
		{
			return totalsize/10;
		}
		else
		{
			return totalsize/10+1;
		}
		
	}
	public JSONArray queryVipTransactByCondition(String sql)
	{
		List<Vip> viplist=EABDao.queryVipTransactByCondition(sql);
		if(!viplist.isEmpty())
		{
			return JSONArray.fromObject(viplist);
		}
		return null;
	}
	public String giveSalary(Salary sal){
		 if(EABDao.saveother(sal).equals(SERVICESUCC))
			 	return SERVICESUCC;
		 else
		return SERVICEFAILURE;
	}
	public JSONArray listAllVip()
	{
		return JSONArray.fromObject(EABDao.listAllVip());
		
	}
	public JSONArray listAllEmployee()
	{
		return JSONArray.fromObject(EABDao.listAllEmployee());
	}
	public String deleteVip(Vip deletedvip) 
	{
		if(EABDao.deleteVip(deletedvip).equals(SERVICESUCC))
		{
			return SERVICESUCC;
		}
		return SERVICEFAILURE;
	}
	public String addEmployee(Employee employee)
	{
		if (EABDao.save(employee).equals(SERVICESUCC))
		{
			return SERVICESUCC;
		}
	return SERVICEFAILURE;
	}
	public Employee queryEmployee(String tel){
		Employee employee=EABDao.get(Employee.class,tel);
		if(employee!=null)
		{
			return employee;
		}
		return null;
	}
	public String deleteEmployee(String tel)
	{
		Employee tmp=new Employee();
		tmp.setTel(tel);
		 if(EABDao.delete(tmp).equals(SERVICESUCC))
			 return SERVICESUCC;
		return SERVICEFAILURE;
	}
	public String modifyEmployee(Employee employee)
	{
		
		String oldphone=ServletActionContext.getRequest().getSession().getAttribute("oldphone").toString();
			System.out.println("旧的手机号是"+oldphone);
		if(oldphone.equals(employee.getTel()))
			{
				/*Employee tmp=queryEmployee(oldphone);
				tmp.setPassword(employee.getPassword());
				tmp.setPosition(employee.getPosition());
				tmp.setRealname(employee.getRealname());
				tmp.setUsername(employee.getUsername());
				tmp.setAddress(employee.getAddress());
				tmp.setSalary(employee.getSalary());
				tmp.setSex(employee.getSex());
				*/
				EABDao.update(employee);
				System.out.println("执行完成");
				return SERVICESUCC;
			}
			else
			{
				System.out.println("执行else");
				deleteEmployee(oldphone);
				addEmployee(employee);
				return SERVICESUCC;
			}
	}
	public List<Salary> querycondition(String condition){
		List<Salary> sallist=EABDao.querycondition(condition);
		if(sallist!=null)
			return sallist;
		else
			return null;
		
	}

	public String  delSalaryRecord(Salary delsalary) {
			if(EABDao.deleteVip(delsalary).equals(SERVICESUCC))
				return SERVICESUCC;
			else
				return SERVICEFAILURE;
	}
	public double queryTotalMoney(String sql)
	{
		Object totalmoney=EABDao.queryToalMoney(sql);
		if(totalmoney==null)
			return 0;
		else
		 return Double.parseDouble(totalmoney.toString());
			
		
	}
}


	

