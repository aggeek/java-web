package org.seckill.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

public interface SeckillDao {
	/**
	 * 
	 * @param seckillId ����ɱ��Ʒ��id
	 * @param killTime  ��ɱ��ʱ��
	 * @return 
	 */
	int reduceNumber(@Param("seckillId")long seckillId,@Param("killTime")Date killTime);
	/**
	 * ����id��ѯ��ɱ����
	 * @param seckillId
	 * @return ���Ӱ�캯��>=1��ʾ���¼�¼����
	 */
	Seckill queryById(long seckillId);
	/**
	 * ����ƫ������ѯ�б�
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<Seckill> queryAll(@Param ("offset")int offset ,@Param("limit")int limit);
	
}
