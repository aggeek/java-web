package seckillService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillClosedException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
						"classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SeckillService seckillService;
	@Test
	public void testGetSeckillList() throws Exception{
		List<Seckill> list=seckillService.getSeckillList();
		logger.info("list={}",list);
	}
	@Test
	public void testgetById()throws Exception {
		long id=1000;
		Seckill seckill=seckillService.getById(id);
		logger.info("seckill={}",seckill);
	}
	@Test
	public void testSeckillLogic()throws Exception{
		long id=1001;
		Exposer exposer=seckillService.exportSeckillUrl(id);
		if(exposer.isExposed()){
			logger.info("exposer={}", exposer);
			long userPhone=18088108999L;
			String md5=exposer.getMd5();
			try {
				SeckillExecution seckillExecution=seckillService.executeSeckill(id, userPhone, md5);
				logger.info("seckillExecution={}",seckillExecution);
			} catch (RepeatKillException e) {
				logger.error(e.getMessage());
			}catch (SeckillClosedException e) {
				logger.error(e.getMessage());
			}
			
		}else{
			logger.warn("exposer={}", exposer);
		}
		
	}
	
}
