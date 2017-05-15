package org.seckill.serviceImpl;

import java.util.Date;
import java.util.List;

import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dao.cache.RedisDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillClosedException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
@Service
public class SeckillServiceImpl implements SeckillService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SeckillDao seckillDao;
	@Autowired
	private SuccessKilledDao successKilledDao;
	@Autowired
	private RedisDao redisDao;
	// 盐值字符串用于混淆
	private final String slat = "da2sk1h654$%54645^$";

	// 根据id获取秒杀商品
	public Seckill getById(long seckillId) {

		return seckillDao.queryById(seckillId);
	}

	// 获取秒杀列表
	public List<Seckill> getSeckillList() {
	
		return seckillDao.queryAll(0, 5);
	}
	
	// 暴露接口，返回值决定是否秒杀
	public Exposer exportSeckillUrl(long seckillId) {
		//通过redis缓存优化
		Seckill seckill = redisDao.getSeckill(seckillId);
		if (seckill==null){
			//没找到就查询数据库
			seckill = seckillDao.queryById(seckillId);
			if (seckill == null) {
				return new Exposer(false, seckillId);
			}else {
				redisDao.putSeckill(seckill);
			}

		}


		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		Date nowTime = new Date();
		if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
			return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
		}
		// 生成MD5
		String md5 = getMd5(seckillId);
		return new Exposer(true, md5, seckillId);
	}

	private String getMd5(long seckillId) {
		String base = seckillId + "/" + slat;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
		

	}
	//执行秒杀ִ
	@Transactional
	public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, SeckillClosedException, RepeatKillException {
		try {
			//md5被更改说明
			if(md5==null||!md5.equals(getMd5(seckillId))) {
				throw new SeckillException("数据被重写");
			}
			//
			Date now=new Date();
			int insertCount=successKilledDao.insertSuccessKilled(seckillId, userPhone);

			if(insertCount<=0) {
				throw new RepeatKillException("重复秒杀");

			} else {
				int updateCount=seckillDao.reduceNumber(seckillId, now);
				if(updateCount<=0){
					throw new SeckillClosedException("秒杀关闭");
				} else {
					SuccessKilled successKilled=successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
					return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS,successKilled);
				}
			}
		}
		catch (SeckillClosedException e1) {
			throw e1;
		}
		catch (RepeatKillException e2) {
			throw e2;
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			throw new SeckillException("seckill inner error"+e.getMessage());
		}
	

	}

}
