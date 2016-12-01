package barbershop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="t_creditexchange")
public class CreditExchange {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String exchangenumber;
	private String exchangename;
	private String exchangegoods;
	private Integer costcredit;
	public CreditExchange(Integer id, String exchangenumber, String exchangename, String exchangegoods,
			Integer costcredit) {
		super();
		this.id = id;
		this.exchangenumber = exchangenumber;
		this.exchangename = exchangename;
		this.exchangegoods = exchangegoods;
		this.costcredit = costcredit;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getExchangenumber() {
		return exchangenumber;
	}
	public void setExchangenumber(String exchangenumber) {
		this.exchangenumber = exchangenumber;
	}
	public String getExchangename() {
		return exchangename;
	}
	public void setExchangename(String exchangename) {
		this.exchangename = exchangename;
	}
	public String getExchangegoods() {
		return exchangegoods;
	}
	public void setExchangegoods(String exchangegoods) {
		this.exchangegoods = exchangegoods;
	}
	public Integer getCostcredit() {
		return costcredit;
	}
	public void setCostcredit(Integer costcredit) {
		this.costcredit = costcredit;
	}
	public CreditExchange() {
		super();
	}
	
}
