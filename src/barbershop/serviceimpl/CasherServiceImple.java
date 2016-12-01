package barbershop.serviceimpl;



import java.util.List;

import javax.transaction.Transactional;


import barbershop.daoimpl.CasherDaoImple;
import barbershop.model.ConsumerCost;
import barbershop.model.CreditExchange;
import barbershop.model.Vip;
import net.sf.json.JSONObject;
@Transactional
public class CasherServiceImple {
	public final String SERVICESUCC="success";
	public final String SERVICEFAILURE="failure";
	private CasherDaoImple casherDaoImple;
	private List<Vip> querylist;
	
	public List<Vip> getQuerylist() {
		return querylist;
	}

	public void setQuerylist(List<Vip> querylist) {
		this.querylist = querylist;
	}

	public CasherDaoImple getCasherDaoImple() {
		return casherDaoImple;
	}

	public void setCasherDaoImple(CasherDaoImple casherDaoImple) {
		this.casherDaoImple = casherDaoImple;
	}

	public String addVip(Vip newVip) {
		String sql="From Vip where vip_phone=\'"+newVip.getVip_phone()+"\'";
		if (newVip.getVip_phone().equals("") || newVip.getVip_phone()==null) {
			System.out.println("CasherServiceImple的********addVIP()*******方法的（手机号）参数不正确！");
			return SERVICEFAILURE;
		}
		List<Vip> tempvip=casherDaoImple.executeQuery(sql);
		if(tempvip.isEmpty())
		{
			casherDaoImple.save(newVip);
		System.out.println("CasherServiceImple___________新的VIP添加成功！_________");
		return SERVICESUCC;
		}
		return SERVICEFAILURE;
	}

	public String recharge(String vipnum, double amout) {
			Vip vip=casherDaoImple.get(Vip.class, vipnum);
			if(vip==null)
			{
				System.out.println("****************充值用户不存在********************！");
				return SERVICEFAILURE;
			}
		Double vipBalance=vip.getCard_balance();
		vipBalance=vipBalance+amout;
		vip.setCard_balance(vipBalance);
		casherDaoImple.update(vip);
		System.out.println("CasherServiceImple___________VIP充值成功！_________");
		return SERVICESUCC;
	}

	public double queryRestMoney(Vip vipnum) {
		Vip queryVip=casherDaoImple.get(Vip.class,vipnum.getVip_number());
		if(queryVip==null)
			{
			System.out.println("****************查询余额的用户不存在********************！");
			return 0;
			}
		double vipBalance=queryVip.getCard_balance();
		System.out.println("CasherServiceImple___________VIP查询余额成功！____________");
		return vipBalance;
	}
	public double queryRestMoneyByPhone(String phonenumber)
	{
		String sql="From Vip where vip_phone="+phonenumber;
		 querylist=casherDaoImple.executeQuery(sql);
		 if(querylist.size()==0)
			 return 0;
		return querylist.get(0).getCard_balance().doubleValue();
	}
	public boolean queryVipNumber(String num) {
		if(casherDaoImple.get(Vip.class, num)==null)
			{
			System.out.println("新生成的会员卡号可用");
			return true;
			}
		System.out.println("新生成的会员卡号不可用");
		return false;
	}
	public String  beforeConsumer(String num)
	{
		String sql="From Vip where vip_number=\'"+num+"\' or vip_phone=\'"+num+"\'";
		if(casherDaoImple.executeQuery(sql).size()!=0)
			{
			String json=JSONObject.fromObject(casherDaoImple.executeQuery(sql).get(0)).toString();
			System.out.println("生成的json串为："+json);
			return json;
			}
		else
	      return "unexist";
	}
	public String consumerCost(ConsumerCost cost){
		System.out.println("进入consumercost方法");
		if(casherDaoImple.saveother(cost).equals(SERVICESUCC))
			{
				if(cost.getNumber()!=null)
				{
					Vip tempvip=casherDaoImple.executeQuery("From Vip where vip_number=\'"+cost.getNumber()+"\' or vip_phone=\'"+cost.getNumber()+"\'").get(0);
					tempvip.setCard_balance(tempvip.getCard_balance()-cost.getCost());
					System.out.println(tempvip.getCard_score());
					tempvip.setCard_score(tempvip.getCard_score()+cost.getCost().intValue()/10);
			        casherDaoImple.update(tempvip);
				}
			  return SERVICESUCC;
			}
		else 
			{return SERVICEFAILURE;}
	}
	public String lostCard(String name,String phone,String newvipnum)
	{
		String sql="From Vip where vip_name=\'"+name+"\' and vip_phone=\'"+phone+"\'";
		List<Vip> lostperson=casherDaoImple.executeQuery(sql);
		if(lostperson.size()!=0)
		{
			Vip queryoutvip=lostperson.get(0);
			Vip refreshvip=new Vip(newvipnum, queryoutvip.getVip_name(),queryoutvip.getVip_phone(), queryoutvip.getCard_level(), queryoutvip.getCard_balance(), queryoutvip.getCard_score(),queryoutvip.getCard_time());
			casherDaoImple.delete(queryoutvip);
			casherDaoImple.save(refreshvip);
			return SERVICESUCC;
		}
		
		return SERVICEFAILURE;
	}
	public String creditExchange(CreditExchange exchange){
		String sql="From Vip where vip_number=\'"+exchange.getExchangenumber()+"\' or vip_phone=\'"+exchange.getExchangenumber()+"\'";
		Vip queryexchangevip=casherDaoImple.executeQuery(sql).get(0);
		queryexchangevip.setCard_score(queryexchangevip.getCard_score()-exchange.getCostcredit());
		exchange.setExchangename(queryexchangevip.getVip_name());
		casherDaoImple.saveother(exchange);
		return SERVICESUCC;
	}
	public String modifyVipInfo(Vip modifyvip){
		String vipnum=modifyvip.getVip_number();
		Vip query=casherDaoImple.get(Vip.class,vipnum);
		query.setCard_balance(modifyvip.getCard_balance());
		query.setCard_score(modifyvip.getCard_score());
		query.setCard_level(modifyvip.getCard_level());
		query.setVip_name(modifyvip.getVip_name());
		query.setVip_phone(modifyvip.getVip_phone());
		casherDaoImple.update(query);
		return SERVICESUCC;
	}
}
