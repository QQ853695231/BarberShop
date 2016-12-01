package barbershop.actions;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.Servlet;

import org.apache.struts2.ServletActionContext;

import barbershop.model.ConsumerCost;
import barbershop.model.Employee;
import barbershop.model.Salary;
import barbershop.model.Vip;
import barbershop.serviceimpl.EmployeeAndBossServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BossAction{
	private EmployeeAndBossServiceImpl employeeAndBossServiceImpl;
	private String number[];
	private Vip deletedvip;
	private Employee modifyemployee;
	
	private SimpleDateFormat format=new SimpleDateFormat("yyyy��MM��dd��");
	
	private String modifytel;
	private String modifysex;
	private String modifyrealname;
	private String modifypassword;
	private String modifyposition;
	private String modifyaddress;
	private double modifysalary;
	
	private Employee employee;
	
	private String querynum;
	private String delnum;
	
	private String editnum;
	
	private String realname;
	private String sex;
	private String tel;
	private String password;
	private String position;
	private String address;
	private double salary;
	
	private String salaryphone;
	private String salaryname;
	private Double shouldbepay;
	private Double actuallypay;
	private String note;
	
	private String salarynum;
	private String date;
	private String name;
	//ɾ�����ʼ�¼
	private String salaryid;
	private Salary delsalary;
	//��ѯӪҵ��
	private String querytime;
	private String queryway;
	//��ʱ���ѯ��Ա�����¼
	private String viptransactquerytime;
	private String viptransactqueryway;
	//��ѯ���Ѽ�¼������
	private String costdate;
	private String querycostway;
	private int page;
	private Integer delcostid;
	public void deleUserCostById(){
		ConsumerCost cost=new ConsumerCost();
		cost.setId(delcostid);
		employeeAndBossServiceImpl.deleteUserCost(cost);
	}
	public void getUserCostdata() throws IOException
	{
		setEncoding();
		System.out.println("Ҫ��ʾ���ǵ�"+page+"ҳ");
		JSONArray array=employeeAndBossServiceImpl.getUserCostdata(page);
		System.out.println("���ɵ�json������"+array.toString());
		ServletActionContext.getResponse().getWriter().println(array.toString());
		closeStream();
	}
	public void queryUserCost() throws ParseException, IOException
	{
		setEncoding();
		String usercostquerysql=generateQuerySQL("From ConsumerCost as c where c.time",costdate,querycostway);
		querycostway="";
		ServletActionContext.getRequest().getSession().setAttribute("querysql", usercostquerysql);
		int buttonnumber=employeeAndBossServiceImpl.TotalUserCost(usercostquerysql);
		ServletActionContext.getResponse().getWriter().println(buttonnumber);
		closeStream();
	}
	public void queryVipTransactByDate() throws ParseException, IOException
	{
		
		setEncoding();
		String viptransactsql=generateQuerySQL("From Vip as v where v.card_time", viptransactquerytime,viptransactqueryway);
		System.out.println("���ɵĻ�Ա�����ѯ����ǣ�"+viptransactsql);
		viptransactqueryway="";
		JSONArray array=employeeAndBossServiceImpl.queryVipTransactByCondition(viptransactsql);
		 if(array!=null)
		 {
			 ServletActionContext.getResponse().getWriter().println(array.toString());
		 }
		closeStream();
		
	}
	public String generateQuerySQL(String sql,String time,String way) throws ParseException
	{
		Date date=new SimpleDateFormat("yyyy-MM-dd").parse(time);
		if(way==null || way.length()==0)
		{
			System.out.println("��ָ�������ڲ�ѯ");
			//��ָ�������ڲ�ѯ
			time="";
			sql=sql+"=\'"+format.format(date)+"\'";
		}
		else 
		{
			if(way.equals("year"))
			{
				sql=sql+" like \'"+format.format(date).substring(0, 5)+"%\'";
			}
			else
			{
				sql=sql+" like \'"+format.format(date).substring(0, 8)+"%\'";
			}
		}
		return sql;
	}
	public void willBeEdit() throws IOException
	{
		setEncoding();
		Employee fromsession=(Employee) ServletActionContext.getRequest().getSession().getAttribute("willbemodified");
			if(fromsession!=null)
			{
				ServletActionContext.getRequest().getSession().setAttribute("oldphone", fromsession.getTel());
				System.out.println("�༭Ա����Ϣ��̨JSON����"+JSONObject.fromObject(fromsession).toString());
				ServletActionContext.getResponse().getWriter().println(JSONObject.fromObject(fromsession).toString());
			}
		closeStream();
	}
	public String  editEmployee() throws IOException
	{
		Employee willbeedit=employeeAndBossServiceImpl.queryEmployee(editnum);
		ServletActionContext.getRequest().getSession().setAttribute("willbemodified", willbeedit);
		return "editpage";
	}
	public void listAllVip() throws IOException{
		System.out.println("������ListAllVip����");
		setEncoding();
		JSONArray json=employeeAndBossServiceImpl.listAllVip();
		if(!json.isEmpty()){
			ServletActionContext.getResponse().getWriter().println(json.toString());
			return ;
		}
		ServletActionContext.getResponse().getWriter().println("��ѯʧ�ܣ�");
		closeStream();
	}
		
	public void addEmployee() throws IOException{
		System.out.println("����addEmployee����"); 
		setEncoding();
		employee=new Employee(password, position, realname, sex, address, salary, tel);
		if(employeeAndBossServiceImpl.queryEmployee(tel)==null)
		{
			if(employeeAndBossServiceImpl.addEmployee(employee).equals("success"))
			{
			ServletActionContext.getResponse().getWriter().println("Ա����ӳɹ���");
			}
			else
			{
			
				ServletActionContext.getResponse().getWriter().println("Ա�����ʧ�ܣ�");
			}
		}
		else{
		ServletActionContext.getResponse().getWriter().println("�ֻ����Ѿ���ʹ�ã�");
		}
		closeStream();
	}
	public String deleteVip()
	{
		for (String vipnum : number) {
			deletedvip=new Vip();
			deletedvip.setVip_number(vipnum);
			employeeAndBossServiceImpl.deleteVip(deletedvip);
		}
		return "vipmangement";
	}
	public void queryEmployee() throws IOException{
		setEncoding();
		Employee  queryout=employeeAndBossServiceImpl.queryEmployee(querynum);
		System.out.println("Ҫ��ѯ��Ա���ֻ�����"+querynum);
		System.out.println(queryout);
		if(queryout!=null)
		{
			ServletActionContext.getRequest().getSession().setAttribute("oldphone", queryout.getTel());
			ServletActionContext.getResponse().getWriter().println(JSONObject.fromObject(queryout).toString());
		}
		
		//ServletActionContext.getResponse().getWriter().println("���޴���");
		closeStream();
	}
	public String   deleteEmployee(){
		/*setEncoding();
		if(employeeAndBossServiceImpl.deleteEmployee(delnum).equals("success"))
		{
			ServletActionContext.getResponse().getWriter().println("ɾ���ɹ���");
		}
		else
		{
		ServletActionContext.getResponse().getWriter().println("ɾ��ʧ�ܣ�");
		
		}
		closeStream();*/
		employeeAndBossServiceImpl.deleteEmployee(delnum);
		return "back";
	}
	
	public void giveSalary() throws IOException{
		setEncoding();
		 Salary empsalary=new Salary(salaryphone, salaryname, shouldbepay, actuallypay, note, format.format(new Date()));
		 if(employeeAndBossServiceImpl.giveSalary(empsalary).equals("success"))
			ServletActionContext.getResponse().getWriter().println("���ʷ��ųɹ���");
		 else {
			 ServletActionContext.getResponse().getWriter().println("����ʧ�ܣ�");
		}
		 closeStream();
	}
	public void conditionQuery() throws IOException{
		setEncoding();
		System.out.println("����������ѯ"+salarynum+date+name);
		System.out.println(date.equals(""));
		String hql="From Salary as s where ";
			if(salarynum.length()>0 && date.equals("") && name.equals("") )
				hql=hql+"s.employeenum=\'"+salarynum+"\'";
			
			else if (salarynum.equals("") && date.length()>0 && name.equals("")) {
				String arr[]=date.split("-");
				hql=hql+"s.paytime like \'"+arr[0]+"��"+arr[1]+"��"+"%\'";
			}
			else if (salarynum.equals("") && date.equals("") && name.length()>0 ) {
				hql=hql+"s.employeename=\'"+name+"\'";
			}
			else if (salarynum.length()>0 && date.length()>0 && name.equals("")) {
				String arr[]=date.split("-");
				hql=hql+"s.employeenum=\'"+salarynum+"\' and  ";
				hql=hql+"s.paytime like \'"+arr[0]+"��"+arr[1]+"��"+"%\'";
				
			}
			else if (salarynum.length()>0 && date.equals("") && name.length()>0) {
				hql=hql+"s.employeenum=\'"+salarynum+"\' and  ";
				hql=hql+"s.employeename=\'"+name+"\'";
			}
			
			else if (salarynum.equals("") && date.length()>0 && name.length()>0) {
				String arr[]=date.split("-");
				hql=hql+"s.paytime like \'"+arr[0]+"��"+arr[1]+"��"+"%\' and ";
				hql=hql+"s.employeename=\'"+name+"\'";
			}
			else {
				String arr[]=date.split("-");
				hql=hql+"s.paytime like \'"+arr[0]+"��"+arr[1]+"��"+"%\' and ";
				hql=hql+"s.employeename=\'"+name+"\' and ";
				hql=hql+"s.employeenum=\'"+salarynum+"\'";
			}
		System.out.println("sql���"+hql);
		List<Salary> querylist=employeeAndBossServiceImpl.querycondition(hql);
			if (querylist!=null) {
				System.out.println(querylist.toString());
				ServletActionContext.getResponse().getWriter().println(JSONArray.fromObject(querylist).toString());
			}
		closeStream();
	}
	//��ѯӪҵ���
	public void queryTotalMoney() throws IOException, ParseException{
		setEncoding();
		Date date=new SimpleDateFormat("yyyy-MM-dd").parse(querytime);
		System.out.println("�������ڳɹ�");
		String sql="Select sum(consumer.cost) From ConsumerCost as consumer where consumer.time";
		//��ǰ̨û��ѡ���ѯ��ʽ
		if(queryway==null || queryway.length()==0)
		{
			//������������ѯ
			sql=sql+"=\'"+format.format(date)+"\'";
			System.out.println("�����ڲ�ѯ");
		}
		else if (queryway.equals("year")) {
			SimpleDateFormat yearformat=new SimpleDateFormat("yyyy��");
			sql=sql+" like \'"+yearformat.format(date)+"%\'";
			System.out.println("Ӫҵ�    ��    ģ����ѯ"+sql);
		}
		else {
			SimpleDateFormat monthformat=new SimpleDateFormat("yyyy��MM��");
			sql=sql+" like \'"+monthformat.format(date)+"%\'";
			System.out.println("Ӫҵ�   ��   ģ����ѯ"+sql);
		}
		double totalmoney=employeeAndBossServiceImpl.queryTotalMoney(sql);
		queryway="";
		ServletActionContext.getResponse().getWriter().println(totalmoney);
		closeStream();
	}
	
	public void listAllEmployee() throws IOException
	{
		setEncoding();
		JSONArray allemp=employeeAndBossServiceImpl.listAllEmployee();
		System.out.println("����Ա����Json���鴮");
		if(!allemp.isEmpty())
		{
			ServletActionContext.getResponse().getWriter().println(allemp.toString());
		}
		else
		{
			ServletActionContext.getResponse().getWriter().println("û��Ա����");
		}
		closeStream();
	}
	public void  delSalaryById() throws IOException {
		setEncoding();
		delsalary=new Salary();
		delsalary.setId(Integer.valueOf(salaryid));
		if(employeeAndBossServiceImpl.delSalaryRecord(delsalary).equals("success"))
		{
			ServletActionContext.getResponse().getWriter().println("��¼ɾ���ɹ�");
		}
		else
		{
			ServletActionContext.getResponse().getWriter().println("��¼ɾ��ʧ��");
		}
	closeStream();
	}
	public void modifyEmployee() throws IOException{
		setEncoding();
		System.out.println("����"+modifysalary);
		employee=new Employee(modifypassword, modifyposition, modifyrealname, modifysex, modifyaddress, modifysalary, modifytel);
		
		if(employeeAndBossServiceImpl.modifyEmployee(employee).equals("success"))
		{
			ServletActionContext.getResponse().getWriter().println("�޸ĳɹ���");
		}
		else {
			ServletActionContext.getResponse().getWriter().println("�޸�ʧ�ܣ�");
		}
		closeStream();
	}
	
	public Integer getDelcostid() {
		return delcostid;
	}
	public void setDelcostid(Integer delcostid) {
		this.delcostid = delcostid;
	}
	public EmployeeAndBossServiceImpl getEmployeeAndBossServiceImpl() {
		return employeeAndBossServiceImpl;
	}
	public void setEmployeeAndBossServiceImpl(EmployeeAndBossServiceImpl employeeAndBossServiceImpl) {
		this.employeeAndBossServiceImpl = employeeAndBossServiceImpl;
	}
	public String[] getNumber() {
		return number;
	}
	public void setNumber(String[] number) {
		this.number = number;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getQuerynum() {
		return querynum;
	}
	public void setQuerynum(String querynum) {
		this.querynum = querynum;
	}
	
	public String getDelnum() {
		return delnum;
	}
	public void setDelnum(String delnum) {
		this.delnum = delnum;
	}
	
	public String getModifypassword() {
		return modifypassword;
	}
	public void setModifypassword(String modifypassword) {
		this.modifypassword = modifypassword;
	}
	
	public String getSalaryphone() {
		return salaryphone;
	}
	public void setSalaryphone(String salaryphone) {
		this.salaryphone = salaryphone;
	}
	public String getSalaryname() {
		return salaryname;
	}
	public void setSalaryname(String salaryname) {
		this.salaryname = salaryname;
	}
	public Double getShouldbepay() {
		return shouldbepay;
	}
	public void setShouldbepay(Double shouldbepay) {
		this.shouldbepay = shouldbepay;
	}
	public Double getActuallypay() {
		return actuallypay;
	}
	public void setActuallypay(Double actuallypay) {
		this.actuallypay = actuallypay;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	public String getSalarynum() {
		return salarynum;
	}
	public void setSalarynum(String salarynum) {
		this.salarynum = salarynum;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSalaryid() {
		return salaryid;
	}
	public void setSalaryid(String salaryid) {
		this.salaryid = salaryid;
	}
	
	public String getQuerytime() {
		return querytime;
	}
	public void setQuerytime(String querytime) {
		this.querytime = querytime;
	}
	public String getQueryway() {
		return queryway;
	}
	public void setQueryway(String queryway) {
		this.queryway = queryway;
	}
	
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getViptransactquerytime() {
		return viptransactquerytime;
	}

	public void setViptransactquerytime(String viptransactquerytime) {
		this.viptransactquerytime = viptransactquerytime;
	}

	public String getViptransactqueryway() {
		return viptransactqueryway;
	}

	public void setViptransactqueryway(String viptransactqueryway) {
		this.viptransactqueryway = viptransactqueryway;
	}

	public Employee getModifyemployee() {
		return modifyemployee;
	}
	public void setModifyemployee(Employee modifyemployee) {
		this.modifyemployee = modifyemployee;
	}
	public String getModifytel() {
		return modifytel;
	}
	public void setModifytel(String modifytel) {
		this.modifytel = modifytel;
	}
	public String getModifysex() {
		return modifysex;
	}
	public void setModifysex(String modifysex) {
		this.modifysex = modifysex;
	}
	public String getModifyrealname() {
		return modifyrealname;
	}
	public void setModifyrealname(String modifyrealname) {
		this.modifyrealname = modifyrealname;
	}
	public String getModifyposition() {
		return modifyposition;
	}
	public void setModifyposition(String modifyposition) {
		this.modifyposition = modifyposition;
	}
	public double getModifysalary() {
		return modifysalary;
	}
	public void setModifysalary(double modifysalary) {
		this.modifysalary = modifysalary;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getModifyaddress() {
		return modifyaddress;
	}
	public void setModifyaddress(String modifyaddress) {
		this.modifyaddress = modifyaddress;
	}
	
	public String getEditnum() {
		return editnum;
	}
	public void setEditnum(String editnum) {
		this.editnum = editnum;
	}
	
	public String getCostdate() {
		return costdate;
	}
	public void setCostdate(String costdate) {
		this.costdate = costdate;
	}
	public String getQuerycostway() {
		return querycostway;
	}
	public void setQuerycostway(String querycostway) {
		this.querycostway = querycostway;
	}
	public void setEncoding(){
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
	}
	public void closeStream() throws IOException{
		ServletActionContext.getResponse().getWriter().flush();
		ServletActionContext.getResponse().getWriter().close();
	}
	
	
}
