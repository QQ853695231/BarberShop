package barbershop.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_user")
public class Employee {
		//密码
		private String password;
		private String 	position;
		//管理员或者收银员的真实姓名
		private String realname;
		private String sex;
		private String address;
		private double salary;
		//此人的联系方式
		@Id
		private String tel;
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getPosition() {
			return position;
		}
		public void setPosition(String position) {
			this.position = position;
		}
		public String getRealname() {
			return realname;
		}
		public void setRealname(String realname) {
			this.realname = realname;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
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
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}
		public Employee( String password, String position, String realname, String sex, String address,
				double salary, String tel) {
			this.password = password;
			this.position = position;
			this.realname = realname;
			this.sex = sex;
			this.address = address;
			this.salary = salary;
			this.tel = tel;
		}
		public Employee() {
		}
		
		
	
	
		
}
