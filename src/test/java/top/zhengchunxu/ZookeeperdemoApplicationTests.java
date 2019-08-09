package top.zhengchunxu;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.base.Joiner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZookeeperdemoApplicationTests {

	@Test
	public void contextLoads() {
		
		Joiner joiner = Joiner.on(";").useForNull("!");
		String t="a;!;c";
		
		System.out.println(joiner.join(new String[] {"a",null,"c"}));
		//assertEquals("a;!;c", joiner.join(new String[]{"a",null,"c"})); 
	}

}
