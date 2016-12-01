package barbershop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_consumercost")
public class ConsumerCost {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String number;
	private String way;
	private Double cost;
	private String content;
	private String time;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getWay() {
		return way;
	}
	public void setWay(String way) {
		this.way = way;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public ConsumerCost(String name, String number, String way, Double cost, String content, String time) {
		super();
		this.name = name;
		this.number = number;
		this.way = way;
		this.cost = cost;
		this.content = content;
		this.time = time;
	}
	public ConsumerCost() {
	}
	
}
