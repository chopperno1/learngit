package cn.tedu.cloudnote.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.cloudnote.entity.Share;
import cn.tedu.cloudnote.service.ShareService;

public class TestShareService extends TestBase{
	private ShareService shareService;
	@Before
	public void init(){
		shareService=super.getContext().getBean("shareService", ShareService.class);
	}
	@Test
	public void testSearchNote(){
		String keyword="笔记";
		List<Share> list = shareService.searchNote(keyword,1);
		for(Share share:list){
			System.out.println(share);
		}
	}
}









