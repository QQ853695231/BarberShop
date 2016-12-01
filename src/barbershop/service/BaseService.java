package barbershop.service;


public interface BaseService {
	String login(String tel,String role,String password);
	String logout();
}
