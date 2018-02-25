package cn.tedu.cloudnote.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.cloudnote.dao.BookDao;
import cn.tedu.cloudnote.dao.NoteDao;
import cn.tedu.cloudnote.entity.Book;
import cn.tedu.cloudnote.entity.Note;
@Service("noteService")
public class NoteServiceImpl implements NoteService{
	@Resource
	private NoteDao noteDao;
	@Resource
	private BookDao bookDao;
	
	public List<Map<String, Object>> loadNotes(String bookId) throws NoteBookNotFoundException {
		if(bookId==null||bookId.trim().isEmpty()){
			throw new NoteBookNotFoundException("BOOK ID不能为空");
		}
		Book book=bookDao.findByBookId(bookId);
		if(book==null){
			throw new NoteBookNotFoundException("笔记本不存在");
		}
		List<Map<String,Object>> list = noteDao.findByBookId(bookId);
		return list;
	}
	public Note loadNote(String noteId) throws NoteNotFoundException {
		//判断ID是否为空
		if(noteId == null || noteId.trim().isEmpty()){
			throw new NoteNotFoundException("笔记ID不能为空");
		}
		
		//调用noteDao.findByNoteId()
		Note note = noteDao.findByNoteId(noteId);
		//判断ID数据是否有效
		if(note == null){
			throw new NoteNotFoundException("笔记ID错误");
		}
		return note;
	}
	public boolean updateNote(String noteId, String noteTitle, String noteBody) throws NoteNotFoundException {
		if(noteId==null||noteId.trim().isEmpty()){
			throw new NoteNotFoundException("笔记ID为空");
		}
		if(noteBody==null){
			noteBody="";
		}
		if(noteTitle==null||noteTitle.trim().isEmpty()){
			noteTitle="无标题";
		}
		Note note=new Note();
		note.setId(noteId);
		note.setTitle(noteTitle);
		note.setBody(noteBody);
		note.setLastModifyTime(System.currentTimeMillis());
		int n=noteDao.update(note);
		return n==1;
	}
	public int deleteNote(String noteId) {
		int rows = noteDao.updateStatus(noteId);
		return rows;
	}
	
	@Transactional
	public int deleteNotes(String... ary) {
		int n=0;
		for(String id:ary){
			Note note=noteDao.findByNoteId(id);
			if(note==null){
				throw new NoteNotFoundException("id"+id);
			}
			n+=noteDao.deleteNoteById(id);
		}
		return n;
	}
	public int deletNotess(String... ids) {
		int n=noteDao.deleteNotes(ids);
		if(n!=ids.length){
			throw new NoteNotFoundException("未删除数据");
		}
		return n;
	}
}










