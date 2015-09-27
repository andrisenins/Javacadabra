package lv.id.andrise.javacadabra;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JavacadabraApplication.class)
@WebAppConfiguration
@EnableAutoConfiguration
public class JavacadabraApplicationTests {

	@Test
	public void contextLoads() {
	}

}
