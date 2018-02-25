package cn.tedu.cloudnote.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.cloudnote.dao.NoteDao;
import cn.tedu.cloudnote.dao.ShareDao;
import cn.tedu.cloudnote.entity.Note;
import cn.tedu.cloudnote.entity.Share;
import cn.tedu.cloudnote.util.NoteUtil;

public class TestShareDao extends TestBase{
	private ShareDao shareDao;
	private NoteDao noteDao;
	@Before
	public void init(){
		shareDao=super.getContext().getBean("shareDao", ShareDao.class);
		noteDao=super.getContext().getBean("noteDao", NoteDao.class);
	}
	//@Test
	public void testSave(){
		//分享笔记:向cn_share插入数据
		Share share=new Share();
		String shareId=NoteUtil.createId();
		Note note=noteDao.findByNoteId("5565bda4-ddee-4f87-844e-2ba83aa4925f");
		share.setCn_share_id(shareId);
		share.setCn_share_title(note.getTitle());
		share.setCn_share_body(note.getBody());
		share.setCn_note_id(note.getId());
		shareDao.save(share);
	}
	
	@Test
	public void testFindLikeTitle(){
		Map<Integer , String> title=new HashMap<Integer , String>();
		title.put(1, "spring");
		List<Share> list = shareDao.findLikeTitle(title);
		for(Share share:list){
			System.out.println(
					share.getCn_note_id()
					+","+share.getCn_share_title());
		}
	}
}












