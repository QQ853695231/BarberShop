package barbershop.model.test;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import barbershop.model.Employee;
import barbershop.model.Vip;
import barbershop.model.util.HibernateUtils;

public class ConnTest {
		HibernateUtils utils=new HibernateUtils();
		Session session;
		@Before
		public void getSession() {
			session=utils.currentSession();
			session.getTransaction().begin();
		}
		@After
		public void close() {
			session.getTransaction().commit();
			session.close();
		}
		@Test
		public void create()
		{
//			Employee e=new Employee("张","1122", "1", "张甲龙","15531178347");
			
//			session.save(e);
		}
		@Test
		public void query() {
			
			Vip e=(Vip) session.get(Vip.class, "1222");
			System.out.println("查出来的数据是："+e.getVip_number());
		}
		@Test
		public void modify() {
			Employee e=(Employee) session.get(Employee.class, "15531178347");
			e.setRealname("Devil");
			session.update(e);
		}
		@Test
		public void delete() {
			Employee e=(Employee) session.get(Employee.class, "18330116421");
			session.delete(e);
		}
}
