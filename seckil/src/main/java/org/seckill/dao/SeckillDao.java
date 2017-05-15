package org.seckill.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

public interface SeckillDao {
	/**
	 * 
	 * @param seckillId 被秒杀产品的id
	 * @param killTime  秒杀的时间
	 * @return 
	 */
	int reduceNumber(@Param("seckillId")long seckillId,@Param("killTime")Date killTime);
	/**
	 * 根据id查询秒杀对象
	 * @param seckillId
	 * @return 如果影响函数>=1表示更新记录行数
	 */
	Seckill queryById(long seckillId);
	/**
	 * 根据偏移量查询列表
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<Seckill> queryAll(@Param ("offset")int offset ,@Param("limit")int limit);
	
}
