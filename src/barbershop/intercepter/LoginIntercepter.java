package barbershop.intercepter;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginIntercepter implements Interceptor {

	private static final long serialVersionUID = -8129113617874838714L;

	@Override
	public void destroy() {
		System.out.println("���������٣�");
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		System.out.println("��������ʼ����");
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		return null;
	}

}
