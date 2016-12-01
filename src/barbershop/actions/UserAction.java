package barbershop.actions;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import barbershop.model.UserInfoFromBrowser;
import barbershop.serviceimpl.UserServiceImple;

public class UserAction {

	private UserInfoFromBrowser userInfoFromBrowser;
	private UserServiceImple userServiceImple;
	private String info;
	
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public UserAction() {}
	public String  logOut(){
		
		userServiceImple.logout();
		return "logout";
	}
	public  String login() 
	{
		System.out.println("进入login方法");
		System.out.println("前台用户数据"+userInfoFromBrowser.getUsername()+userInfoFromBrowser.getPassword()+userInfoFromBrowser.getPower());
		String judeInfo=userServiceImple.login(
								userInfoFromBrowser.getUsername(),
								userInfoFromBrowser.getPower(),
								  userInfoFromBrowser.getPassword());
		if(judeInfo.equals("failsure"))
		{
			System.out.println("登录失败");
			try {
				info=URLEncoder.encode("用户名或者密码出错或者权限出错", "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return "loginpage";
		}
		else
		{
			if(userInfoFromBrowser.getPower().equals("管理员"))
			{				
			System.out.println("以管理员身份登录");
			return "manager";
			}
			return "casher";
		}
	}
	


	public UserServiceImple getUserServiceImple() {
		return userServiceImple;
	}
	public void setUserServiceImple(UserServiceImple userServiceImple) {
		this.userServiceImple = userServiceImple;
	}
	public UserInfoFromBrowser getUserInfoFromBrowser() {
		return userInfoFromBrowser;
	}
	public void setUserInfoFromBrowser(UserInfoFromBrowser userInfoFromBrowser) {
		this.userInfoFromBrowser = userInfoFromBrowser;
	}
	
	
}
