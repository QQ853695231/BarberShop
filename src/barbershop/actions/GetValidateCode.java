package barbershop.actions;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class GetValidateCode extends ActionSupport {

	private static final long serialVersionUID = 1967783751142040215L;

	public void getValidateCode() {
		System.out.println("�����ȡ��֤��Ľ׶�");
		try {
		String code =ServletActionContext.getRequest().getSession().getAttribute("validatecode").toString();
		ServletActionContext.getResponse().setContentType("text/text;charset=utf-8");
		ServletActionContext.getResponse().getWriter().println(code);
		ServletActionContext.getResponse().getWriter().flush();		
		ServletActionContext.getResponse().getWriter().close();
		} catch (IOException e) {
			System.out.println("��ͻ��˻ظ���֤�����");
			e.printStackTrace();
		}
		
	}

}
