package seckillDao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.SeckillDao;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration({"classpath:spring/spring-dao.xml"})
/**
 * spring junit
 */
public class SeckillDaoTest {
	@Resource
	private SeckillDao seckillDao;
	@Test
	public void testReduceNumber() throws Exception {
		long id=1001;
		Date date=new Date();
	int nowCount=	seckillDao.reduceNumber(id, date);
	System.out.println("nowCount:"+nowCount);
	}
	@Test
	public void testQueryById() throws Exception{
		long id=1000;
		Seckill seckill=seckillDao.queryById(id);
		System.out.println(seckill);
	}
	@Test
	public void testQueryAll()throws Exception {
		List<Seckill> seckills=seckillDao.queryAll(0, 10);

		for (Seckill seckill : seckills) {
			   System.out.println(seckill);
		}
	}
	
}
