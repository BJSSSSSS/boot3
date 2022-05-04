package com.byeon.boot3.schedule;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.byeon.boot3.board.BoardMapper;
import com.byeon.boot3.board.BoardVO;
import com.byeon.boot3.util.Pager;

@Component
public class TestSchedule {

	@Autowired
	private BoardMapper boardMapper;
	
	//@Scheduled(fixedRate = 1000, initialDelayString = "2000")
	public void fixRateScehdule() throws Exception{
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.getTime());
		Thread.sleep(2000);
	}
	
	//@Scheduled(fixedDelay = 1000, initialDelayString = "2000")
	public void fixDelayScehdule() throws Exception{
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.getTime());
		Thread.sleep(2000);
	}
	
	//@Scheduled(cron = "50 * * * * *")
	public void cronSchedule() throws Exception{
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.getTime());
		Pager pager = new Pager();
		pager.makeRow();
		List<BoardVO> ar = boardMapper.getList(pager);
		System.out.println(ar);
	}
	
	
	
	
}
