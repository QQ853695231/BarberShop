package barbershop.actions;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import barbershop.model.ConsumerCost;
import barbershop.model.CreditExchange;
import barbershop.model.Vip;
import barbershop.serviceimpl.CasherServiceImple;
import net.sf.json.JSONObject;

public class CasherAction{
	
	private Vip newVip;
	private Vip queryBalanceVip;
	private Double amout;
	private String vipRechargeNum;
	
	private String newVipvip_number;
	private String newVipvip_name;
	private String newVipvip_phone;
	private String newVipcard_level;
	private String newVipcard_balance;
	
	
	
	
	private String queryvipnum;
	
	private String queryvipinfonum;
	private String modifyvipphone;
	private String modifyvipname;
	private String modifyvipnum;
	private String modifyviplevel;
	private String modifyvipbalance;
	private String modifyvipcredit;
	private Vip modifyvip;
	
	private String modifynum;
	
	
	private CasherServiceImple casherServiceImple;
	private ConsumerCost costobj;
	private double queryoutmoney;
	private String consumer_number;
	private GetCardNum supplycardnum;
	private SimpleDateFormat format=new SimpleDateFormat("yyyy年MM月dd日");
	
	
	/*******************普通客户与Vip消费*********************/
	private String costdnum;
	private String consumername;
	private String way;
	private String total;
	private String content;
	/*******************Vip挂失并重新生成会员号*********************/
	private String lostname;
	private String lostphone;
	/*******************Vip兑换物品所需变量*********************/
	private CreditExchange exchange;
	private String creditnum;
	private String exchangegoods;
	private Integer realcredit;
	
	public void   addVip() throws IOException
	{
		setEncoding();
		System.out.println("进入casheraction的addvip方法");
		newVip.setCard_time(format.format(new Date()));
		newVip.setVip_number(newVipvip_number);
		newVip.setVip_name(newVipvip_name);
		newVip.setVip_phone(newVipvip_phone);
		newVip.setCard_score(0);
		newVip.setCard_level(newVipcard_level);
		newVip.setCard_balance(Double.parseDouble(newVipcard_balance));
		String addinfo=casherServiceImple.addVip(newVip);
		if (addinfo.equals("success")) {
			ServletActionContext.getResponse().getWriter().println("注册成功！");
		}
		else {
			ServletActionContext.getResponse().getWriter().println("手机号已经注册！");
		}
		closeStream();
	}
	
	public void  reCharge() throws IOException
	{
		setEncoding();
		String charge=casherServiceImple.recharge(vipRechargeNum, amout);
		if (charge.equals("success"))
		{
			
			ServletActionContext.getResponse().getWriter().println("充值成功！");
			return;
		}
		ServletActionContext.getResponse().getWriter().println("充值失败，请检查会员卡号！");
		closeStream();
	}
	public void  queryRestMoney() throws IOException
	{
		setEncoding();
		System.out.println("要查询的数据是："+queryvipnum);
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		if(queryvipnum.length()!=11)
			{
				queryBalanceVip.setVip_number(queryvipnum);
				queryoutmoney=casherServiceImple.queryRestMoney(queryBalanceVip);
			}
		else
		{
			queryoutmoney=casherServiceImple.queryRestMoneyByPhone(queryvipnum);
		}
		System.out.println("查询出来的账户余额为："+queryoutmoney);
		if(queryoutmoney!=0)
		  {
			ServletActionContext.getResponse().getWriter().println(queryoutmoney);
		  }
		else
		{
			ServletActionContext.getResponse().getWriter().println("主人快来充值吧！");
		}
		closeStream();
	}
	public void consumer() throws IOException{
		setEncoding();
		String content=casherServiceImple.beforeConsumer(consumer_number);
		if (content.equals("unexist")) {
			JSONObject json=new JSONObject();
			json.put("info","会员不存在");
			ServletActionContext.getResponse().getWriter().println(json.toString());
		}
		else{
			ServletActionContext.getResponse().getWriter().println(content);
		}
		closeStream();
	}
	public void userCost() throws IOException{
		setEncoding();
		System.out.println("进入到userCost方法");
		System.out.println(content+consumername+costdnum+way+total);
		costobj=new ConsumerCost();
		costobj.setContent(content);
		costobj.setName(consumername);
		costobj.setNumber(costdnum);
		costobj.setWay(way);
		costobj.setCost(Double.valueOf(total));
		costobj.setTime(format.format(new Date()));
		String serviceinfo=casherServiceImple.consumerCost(costobj);
		if(serviceinfo.equals("success"))
		{
			ServletActionContext.getResponse().getWriter().println("消费成功！");
		}
		closeStream();
	}
	public void ordinaryCost() throws IOException{
		setEncoding();
		costobj=new ConsumerCost();
		costobj.setContent(content);
		costobj.setWay(way);
		costobj.setCost(Double.valueOf(total));
		costobj.setTime(new Date().toString());
		String serviceinfo=casherServiceImple.consumerCost(costobj);
		if(serviceinfo.equals("success"))
		{
			ServletActionContext.getResponse().getWriter().println("消费成功！");
		}
		else
		{
			ServletActionContext.getResponse().getWriter().println("消费失败！");
		}
		closeStream();
	}
	
	public void lostCard() throws IOException 
	{
		setEncoding();
		String newvipnum=supplycardnum.generateNumber();
		if (casherServiceImple.lostCard(lostname, lostphone, newvipnum).equals("success"))
			ServletActionContext.getResponse().getWriter().println("挂失并重新生成成功！请牢记："+newvipnum);
		else
			ServletActionContext.getResponse().getWriter().println("挂失失败！");
		closeStream();
	}
	
	public void creditExchange() throws IOException{
		setEncoding();
		exchange.setExchangenumber(creditnum);
		exchange.setCostcredit(realcredit);
		exchange.setExchangegoods(exchangegoods);
		
		if(casherServiceImple.creditExchange(exchange).equals("success"))
			ServletActionContext.getResponse().getWriter().println("兑换成功！");
		else 
			ServletActionContext.getResponse().getWriter().println("兑换失败！");
		closeStream();
	}
	
	public void setEncoding(){
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
	}
	public void closeStream() throws IOException{
		ServletActionContext.getResponse().getWriter().flush();
		ServletActionContext.getResponse().getWriter().close();
	}
	
	public void queryVipInfo() throws IOException
	{
		setEncoding();
		String content=casherServiceImple.beforeConsumer(queryvipinfonum);
		System.out.println(content);
		if(content.equals("unexist"))
		{
			ServletActionContext.getResponse().getWriter().println("会员不存在");
			return ;
		}
		else
		{
			ServletActionContext.getResponse().getWriter().println(content);
			closeStream();
		}
		
	}
	public void modifyVipInfo() throws IOException {
		setEncoding();
		modifyvip=new Vip();
		modifyvip.setVip_number(modifyvipnum);
		modifyvip.setVip_name(modifyvipname);
		modifyvip.setVip_phone(modifyvipphone);
		modifyvip.setCard_balance(Double.valueOf(modifyvipbalance));
		modifyvip.setCard_level(modifyviplevel);
		modifyvip.setCard_score((Integer.valueOf(modifyvipcredit)));
		casherServiceImple.modifyVipInfo(modifyvip);
		ServletActionContext.getResponse().getWriter().println("修改成功！");
		
	}
	
	public String getQueryvipinfonum() {
		return queryvipinfonum;
	}

	public void setQueryvipinfonum(String queryvipinfonum) {
		this.queryvipinfonum = queryvipinfonum;
	}

	public Vip getNewVip() {
		return newVip;
	}
	public void setNewVip(Vip newVip) {
		this.newVip = newVip;
	}
	public Vip getQueryBalanceVip() {
		return queryBalanceVip;
	}
	public void setQueryBalanceVip(Vip queryBalanceVip) {
		this.queryBalanceVip = queryBalanceVip;
	}
	public Double getAmout() {
		return amout;
	}
	public void setAmout(Double amout) {
		this.amout = amout;
	}
	public String getVipRechargeNum() {
		return vipRechargeNum;
	}
	public void setVipRechargeNum(String vipRechargeNum) {
		this.vipRechargeNum = vipRechargeNum;
	}

	public CasherServiceImple getCasherServiceImple() {
		return casherServiceImple;
	}

	public void setCasherServiceImple(CasherServiceImple casherServiceImple) {
		this.casherServiceImple = casherServiceImple;
	}

	public String getQueryvipnum() {
		return queryvipnum;
	}

	public void setQueryvipnum(String queryvipnum) {
		this.queryvipnum = queryvipnum;
	}

	public double getQueryoutmoney() {
		return queryoutmoney;
	}

	public void setQueryoutmoney(double queryoutmoney) {
		this.queryoutmoney = queryoutmoney;
	}

	public String getNewVipvip_number() {
		return newVipvip_number;
	}

	public void setNewVipvip_number(String newVipvip_number) {
		this.newVipvip_number = newVipvip_number;
	}

	public String getNewVipvip_name() {
		return newVipvip_name;
	}

	public void setNewVipvip_name(String newVipvip_name) {
		this.newVipvip_name = newVipvip_name;
	}

	public String getNewVipvip_phone() {
		return newVipvip_phone;
	}

	public void setNewVipvip_phone(String newVipvip_phone) {
		this.newVipvip_phone = newVipvip_phone;
	}

	public String getNewVipcard_balance() {
		return newVipcard_balance;
	}

	public void setNewVipcard_balance(String newVipcard_balance) {
		this.newVipcard_balance = newVipcard_balance;
	}

	public String getNewVipcard_level() {
		return newVipcard_level;
	}

	public void setNewVipcard_level(String newVipcard_level) {
		this.newVipcard_level = newVipcard_level;
	}

	public CasherAction() {
		
	}

	public String getConsumer_number() {
		return consumer_number;
	}

	public void setConsumer_number(String consumer_number) {
		this.consumer_number = consumer_number;
	}

	public String getCostdnum() {
		return costdnum;
	}

	public void setCostdnum(String costdnum) {
		this.costdnum = costdnum;
	}

	public String getConsumername() {
		return consumername;
	}

	public void setConsumername(String consumername) {
		this.consumername = consumername;
	}

	public String getWay() {
		return way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ConsumerCost getCostobj() {
		return costobj;
	}

	public void setCostobj(ConsumerCost costobj) {
		this.costobj = costobj;
	}
	

	public String getLostname() {
		return lostname;
	}

	public void setLostname(String lostname) {
		this.lostname = lostname;
	}

	public String getLostphone() {
		return lostphone;
	}

	public void setLostphone(String lostphone) {
		this.lostphone = lostphone;
	}

	public GetCardNum getSupplycardnum() {
		return supplycardnum;
	}

	public void setSupplycardnum(GetCardNum supplycardnum) {
		this.supplycardnum = supplycardnum;
	}

	public String getCreditnum() {
		return creditnum;
	}

	public void setCreditnum(String creditnum) {
		this.creditnum = creditnum;
	}

	public String getExchangegoods() {
		return exchangegoods;
	}

	public void setExchangegoods(String exchangegoods) {
		this.exchangegoods = exchangegoods;
	}

	public Integer getRealcredit() {
		return realcredit;
	}

	public void setRealcredit(Integer realcredit) {
		this.realcredit = realcredit;
	}

	public CreditExchange getExchange() {
		return exchange;
	}

	public void setExchange(CreditExchange exchange) {
		this.exchange = exchange;
	}

	public String getModifyvipphone() {
		return modifyvipphone;
	}

	public void setModifyvipphone(String modifyvipphone) {
		this.modifyvipphone = modifyvipphone;
	}

	public String getModifyvipname() {
		return modifyvipname;
	}

	public void setModifyvipname(String modifyvipname) {
		this.modifyvipname = modifyvipname;
	}

	public String getModifyvipnum() {
		return modifyvipnum;
	}

	public void setModifyvipnum(String modifyvipnum) {
		this.modifyvipnum = modifyvipnum;
	}

	public String getModifyviplevel() {
		return modifyviplevel;
	}

	public void setModifyviplevel(String modifyviplevel) {
		this.modifyviplevel = modifyviplevel;
	}

	public String getModifynum() {
		return modifynum;
	}

	public void setModifynum(String modifynum) {
		this.modifynum = modifynum;
	}

	public String getModifyvipbalance() {
		return modifyvipbalance;
	}

	public void setModifyvipbalance(String modifyvipbalance) {
		this.modifyvipbalance = modifyvipbalance;
	}

	public String getModifyvipcredit() {
		return modifyvipcredit;
	}

	public void setModifyvipcredit(String modifyvipcredit) {
		this.modifyvipcredit = modifyvipcredit;
	}
	
}
