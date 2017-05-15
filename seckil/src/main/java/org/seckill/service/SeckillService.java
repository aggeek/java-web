package org.seckill.service;

import java.util.List;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillClosedException;
import org.seckill.exception.SeckillException;

public interface SeckillService {
/**
 * 业务逻辑接口
 * @param seckillId
 * @return
 */
	Seckill getById(long seckillId);
	/**
	 * 根据id获取秒杀商品
	 * @return
	 */
	List<Seckill> getSeckillList();
	
	Exposer exportSeckillUrl(long seckillId);
	
	SeckillExecution executeSeckill(long seckillId,long userPhone ,String md5) throws
	SeckillException,SeckillClosedException,RepeatKillException;
}
