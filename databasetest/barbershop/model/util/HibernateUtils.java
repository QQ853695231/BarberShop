package barbershop.model.util;
import java.text.SimpleDateFormat;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class HibernateUtils {
	public   SessionFactory sessionFactory;
    public Configuration configuration;
    public Session session;
    
    	@SuppressWarnings("deprecation")
		public HibernateUtils(){
    		System.out.println("ִ�й��캯��");
            configuration = new Configuration().configure();
            sessionFactory=configuration.buildSessionFactory();
            System.out.println("sessionFactory" +sessionFactory);
            System.out.println("���캯��ִ�����");
    	}
    	
    
    public  Session currentSession() {
        session =sessionFactory.openSession();
        return session;
    }
    public  void closeSession(Session session) {
        if (session != null) {
            session.close();
        }
 
    }
    @Test
    public void test(){
    	SimpleDateFormat format=new SimpleDateFormat("yyyy��MM��");
    	String t2="2016.03";
    	t2.replace("\\.", "a");
    	System.out.println(t2);
    	
    }
}