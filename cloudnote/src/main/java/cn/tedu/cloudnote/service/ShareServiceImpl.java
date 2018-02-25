package cn.tedu.cloudnote.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.cloudnote.dao.NoteDao;
import cn.tedu.cloudnote.dao.ShareDao;
import cn.tedu.cloudnote.entity.Note;
import cn.tedu.cloudnote.entity.Share;
import cn.tedu.cloudnote.util.NoteUtil;

@Service("shareService")
@Transactional
public class ShareServiceImpl implements ShareService{
	@Resource
	private ShareDao shareDao;
	@Resource
	private NoteDao noteDao;
	
	public void shareNote(String noteId) {
		Share share = new Share();
		String shareId = NoteUtil.createId();
		Note note = noteDao.findByNoteId(noteId);
		share.setCn_share_id(shareId);
		share.setCn_share_title(note.getTitle());
		share.setCn_share_body(note.getBody());
		share.setCn_note_id(note.getId());
		shareDao.save(share);
		//TODO noteDao.updateType();
		//模拟异常
		String str=null;
		str.length();
	}
	public List<Share> searchNote(String keyword,int page) {
		String title="%";
		if(keyword!=null && !"".equals(keyword)){
			title="%"+keyword.trim()+"%";
		}
		int begin=(page-1)*3;
		Map<String,Object> params = new HashMap();
		params.put("title", title);//对应#{title}
		params.put("begin", begin);//对应#{begin}
		List<Share> list = shareDao.findLikeTitle(params);
		return list;
	}
}











