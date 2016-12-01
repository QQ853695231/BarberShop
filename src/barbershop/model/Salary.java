package barbershop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="t_salary")
public class Salary {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String employeenum;
	private String employeename;
	private Double shouldbepay;
	private Double actuallypay;
	private String note;
	private String paytime;
	
	
	
	
	public Salary() {
	}
	
	public Salary(String employeenum, String employeename, Double shouldbepay, Double actuallypay, String note,String paytime) {
		this.employeenum = employeenum;
		this.employeename = employeename;
		this.shouldbepay = shouldbepay;
		this.actuallypay = actuallypay;
		this.note = note;
		this.paytime=paytime;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmployeenum() {
		return employeenum;
	}
	public void setEmployeenum(String employeenum) {
		this.employeenum = employeenum;
	}
	public String getEmployeename() {
		return employeename;
	}
	public void setEmployeename(String employeename) {
		this.employeename = employeename;
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

	public String getPaytime() {
		return paytime;
	}

	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}
	
	
	
	
	
}
