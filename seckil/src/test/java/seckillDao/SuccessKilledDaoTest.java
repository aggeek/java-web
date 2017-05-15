package seckillDao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {
@Resource	
private SuccessKilledDao skDao;
	@Test
	public void testinsertSuccessKilled() {
		skDao.insertSuccessKilled(1000L, 18020108379L);
		skDao.insertSuccessKilled(1001L, 18020108370L);
	}
	@Test
	public void testqueryByIdWithSeckill() {
		SuccessKilled	SK=skDao.queryByIdWithSeckill(1001L,18020108370L);
		System.out.println(SK);
		System.out.println(SK.getSeckill());
	}
	
	
}
