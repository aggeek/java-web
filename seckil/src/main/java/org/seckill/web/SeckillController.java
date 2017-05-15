package org.seckill.web;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.dto.SeckillResult;
import org.seckill.entity.Seckill;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillClosedException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@Controller
@RequestMapping("/seckill")
public class SeckillController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SeckillService seckillService;
	//获取列表
	@RequestMapping(value="/list" ,method=RequestMethod.GET)
	public String list(Model model){
		List<Seckill> list= seckillService.getSeckillList();
		model.addAttribute("list", list);
		return "list";
		
	}
	//商品细节
	@RequestMapping(value="/{seckillId}/detail" ,method=RequestMethod.GET)
	public String detail(@PathVariable("seckillId") Long seckillId, Model model){

		if(seckillId==null){
			return "redirect:/seckill/list";
		}
		Seckill seckill=seckillService.getById(seckillId);
		if(seckill==null){
			return "forward:/seckill/list";
		}
		model.addAttribute("seckill", seckill);
		return "detail";
		
	}
	//ajax json
	//秒杀接口
	@RequestMapping(value="/{seckillId}/exposer",method=RequestMethod.POST
							,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public SeckillResult<Exposer> exposer(@PathVariable("seckillId")Long seckillId){
		
		SeckillResult<Exposer> result;
		try {
			Exposer exposer=seckillService.exportSeckillUrl(seckillId);
			result=new SeckillResult<Exposer>(true, exposer);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			result=new SeckillResult<Exposer>(false,e.getMessage());
		}
		return result;
		
	}
	@RequestMapping(value="/{seckillId}/{md5}/execution",method=RequestMethod.POST
			,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	 public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId")Long seckillId
			 ,@CookieValue(value="killPhone",required=false) Long userPhone,@PathVariable("md5")String md5){
		SeckillResult<SeckillExecution> result;
		if(userPhone==null){

			return new SeckillResult<SeckillExecution>(true, "未注册");

		}
		try {
			SeckillExecution execution = seckillService.executeSeckill(seckillId,userPhone,md5);
			return new SeckillResult<SeckillExecution>(true, execution);

		}catch (SeckillClosedException e1) {
			//秒杀结束或者未开始
			SeckillExecution execution=new SeckillExecution(seckillId,SeckillStateEnum.END);
			return new SeckillResult<SeckillExecution>(true, execution);
		}
		catch (RepeatKillException e2) {
			//重复秒杀
			SeckillExecution execution=new SeckillExecution(seckillId,SeckillStateEnum.REPEAT);
			return new SeckillResult<SeckillExecution>(true, execution);

		}catch (Exception e) {
			logger.error(e.getMessage(),e);	
			SeckillExecution execution=new SeckillExecution(seckillId,SeckillStateEnum.INNER_ERROR);
			return new SeckillResult<SeckillExecution>(true, execution);

			
		}

	 }
	@RequestMapping(value="/time/now",method=RequestMethod.GET
			,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public SeckillResult<Long> time(){
		SeckillResult<Long> result;
		Date now=new Date();
		result=new SeckillResult<Long>(true, now.getTime());
		return result;
		
	}
}
