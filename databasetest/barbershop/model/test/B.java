package barbershop.model.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import net.sf.json.JSONObject;

public class B {
	@Test
	public void main() throws ParseException{
		String time="2016-01-01";
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		
		System.out.println(new SimpleDateFormat("yyyyƒÍMM").format(format.parse(time)));
	
	}
}
