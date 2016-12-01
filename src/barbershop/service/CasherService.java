package barbershop.service;

import barbershop.model.Vip;

public interface CasherService {
	public boolean queryVipNumber(String num);
	public final String SERVICESUCC="success";
	public final String SERVICEFAILURE="failure";
	public String addVip(Vip newVip);
	public String recharge(String vipnum,double amout);
	public double queryRestMoney(Vip vipnum);
//	public String  customCost( );
}
