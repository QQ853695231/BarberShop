package barbershop.model;


public class UserInfoFromBrowser {
	private String username;
	private String password;
	private String power;
	
	public UserInfoFromBrowser() {	}
	public UserInfoFromBrowser(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	
	
}
