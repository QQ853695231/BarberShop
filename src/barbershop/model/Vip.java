package barbershop.model;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_vip")
public class Vip {
		@Id
		private String vip_number;
		private String vip_name;
		private String vip_phone;
		private String card_level;
		private Double  card_balance;
		private int card_score;
		private String card_time;
		public Vip() {

		}
		
		public Vip(String vip_number, String vip_name, String vip_phone, String card_level, Double card_balance,
				int card_score,String card_time) {
			super();
			this.vip_number = vip_number;
			this.vip_name = vip_name;
			this.vip_phone = vip_phone;
			this.card_level = card_level;
			this.card_balance = card_balance;
			this.card_time = card_time;
			this.card_score=card_score;
		}

		public String getVip_number() {
			return vip_number;
		}

		public void setVip_number(String vip_number) {
			this.vip_number = vip_number;
		}

		public String getVip_name() {
			return vip_name;
		}
		public void setVip_name(String vip_name) {
			this.vip_name = vip_name;
		}
		public String getVip_phone() {
			return vip_phone;
		}
		public void setVip_phone(String vip_phone) {
			this.vip_phone = vip_phone;
		}
		public String getCard_level() {
			return card_level;
		}
		public void setCard_level(String card_level) {
			this.card_level = card_level;
		}
		public Double getCard_balance() {
			return card_balance;
		}
		public void setCard_balance(Double card_balance) {
			this.card_balance = card_balance;
		}
		public String getCard_time() {
			return card_time;
		}
		public void setCard_time(String card_time) {
			this.card_time = card_time;
		}

		public int getCard_score() {
			return card_score;
		}

		public void setCard_score(int card_score) {
			this.card_score = card_score;
		}

		
		
		
}
