package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

public interface SuccessKilledDao {
/**
 * 插入购买明细，可过滤重复
 * @param seckillId
 * @param userPhone
 * @return
 */
	int insertSuccessKilled(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);
	/**
	 * 根据秒杀id+userPhone查询记录，并将seckill携带
	 * @param seckillId
	 * @return 插入的行数
	 */
	SuccessKilled queryByIdWithSeckill(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);
}
