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
		System.out.println("����login����");
		System.out.println("ǰ̨�û�����"+userInfoFromBrowser.getUsername()+userInfoFromBrowser.getPassword()+userInfoFromBrowser.getPower());
		String judeInfo=userServiceImple.login(
								userInfoFromBrowser.getUsername(),
								userInfoFromBrowser.getPower(),
								  userInfoFromBrowser.getPassword());
		if(judeInfo.equals("failsure"))
		{
			System.out.println("��¼ʧ��");
			try {
				info=URLEncoder.encode("�û�����������������Ȩ�޳���", "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return "loginpage";
		}
		else
		{
			if(userInfoFromBrowser.getPower().equals("����Ա"))
			{				
			System.out.println("�Թ���Ա��ݵ�¼");
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
