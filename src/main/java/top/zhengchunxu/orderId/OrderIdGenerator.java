package top.zhengchunxu.orderId;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class OrderIdGenerator {

	private static int count=0;
	
	public String getOrder12() {
		
		synchronized (String.class) {
			SimpleDateFormat format=new SimpleDateFormat("yyyyMMddhhmmss");
			return format.format(new Date())+"-"+ ++count;
		}
		
		
	}
	
}
