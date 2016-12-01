package barbershop.serviceimpl;


import org.apache.struts2.ServletActionContext;

import barbershop.daoimpl.EmployAndBossDaoImpl;
import barbershop.model.Employee;

public class UserServiceImple extends BaseServiceImpl{
	private EmployAndBossDaoImpl EABDao;
	public final String SUCCESS="success";
	public final String FAILSURE="failsure";
	@Override
	public String logout() {
		ServletActionContext.getRequest().getSession().invalidate();
		return SUCCESS;
		
	}
	@Override
	public String login(String tel, String role, String password) {
		if(tel.length()==0 || password.length()==0)
		{
			return FAILSURE;
		}
		
		Employee internalperson=EABDao.get(Employee.class,tel);
		//System.out.println("��������ڲ�Ա��"+internalperson);
		System.out.println("��������ڲ�Ա��"+internalperson.getPosition());
		System.out.println("��������ڲ�Ա��1"+internalperson==null);
		System.out.println("��������ڲ�Ա��2"+!internalperson.getPosition().equals(role));
		System.out.println("��������ڲ�Ա��3"+!internalperson.getPassword().equals(password));
		
		if(internalperson==null || !internalperson.getPosition().equals(role) || !internalperson.getPassword().equals(password))
			{
			System.out.println("�����ݿ���û�в����");
			return FAILSURE;	
			}
		ServletActionContext.getRequest().getSession().setAttribute("username", internalperson.getRealname());
		System.out.println("�û�session�е�username"+ServletActionContext.getRequest().getSession().getAttribute("username"));
		return SUCCESS;
	}
	public EmployAndBossDaoImpl getEABDao() {
		return EABDao;
	}
	public void setEABDao(EmployAndBossDaoImpl eABDao) {
		EABDao = eABDao;
	}
	
}
