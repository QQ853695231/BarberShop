package barbershop.actions;

import java.io.IOException;
import java.util.Random;

import javax.transaction.Transactional;

import org.apache.struts2.ServletActionContext;

import barbershop.serviceimpl.CasherServiceImple;

@Transactional
public class GetCardNum {
	private CasherServiceImple casherServiceImple;
	private  String vipnum;
	
	public String getVipnum() {
		return vipnum;
	}

	public void setVipnum(String vipnum) {
		this.vipnum = vipnum;
	}

	public CasherServiceImple getCasherServiceImple() {
		return casherServiceImple;
	}

	public void setCasherServiceImple(CasherServiceImple casherServiceImple) {
		this.casherServiceImple = casherServiceImple;
	}

	public void getNumber() throws IOException{
	
	 System.out.println("����getNumber*******getNumber()");
		 for(;;)
		{
			 System.out.println("ѭ���ڲ�");
			vipnum=generateNumber();
			if(vipnum!=null)
				break;
		}
		ServletActionContext.getResponse().getWriter().println(vipnum);
		ServletActionContext.getResponse().getWriter().flush();
		ServletActionContext.getResponse().getWriter().close();
	}

	public String  generateNumber(){
		System.out.println("����generateNumber����");
		String generatednumber="";
		Random rand=new Random();
		for(int x=0;x<6;x++)
		{
			generatednumber=generatednumber+rand.nextInt(10);
		} 
		if(casherServiceImple.queryVipNumber(generatednumber))
		    {
			System.out.println("service�㷵����true");
			return generatednumber;
		    }
		return null;
		
	}

	public GetCardNum() {
	}

}
