package cn.tedu.cloudnote.test;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.cloudnote.entity.Note;
import cn.tedu.cloudnote.service.NoteService;

public class TestNoteService extends TestBase{
	private NoteService noteService;
	@Before
	public void init(){
		noteService=super.getContext().getBean(
				"noteService", NoteService.class);		
	}
	//@Test
	public void test(){
		String id=
		"fa8d3d9d-2de5-4cfe-845f-951041bcc461";
		List<Map<String,Object>> list=
				noteService.loadNotes(id);
		for(Map<String,Object> map:list){
			System.out.println(map);
		}
	}
	//@Test
	public void testLoadNote(){
		String id
		="5565bda4-ddee-4f87-844e-2ba83aa4925f";
		Note note
				=noteService.loadNote(id);
		System.out.println(note);
	}
	//@Test
	public void testUpdateNote(){
		String noteId=
			"5565bda4-ddee-4f87-844e-2ba83aa4925f";
		String noteTitle="JAVA";
		String noteBody="AOP Program";
		boolean ok=
		noteService.updateNote(noteId, noteTitle, noteBody);
		System.out.println(ok);
	}
	@Test
	public void testDeleteNotes(){
		String id1="07305c91-d9fa-420d-af09-c3ff209608ff";
		String id2="5565bda4-ddee-4f87-844e-2ba83aa4925f";
		String id3="1ec185d6-554a-481b-b322-b562485bb8e8";
		int n=noteService.deletNotess(id1,id2,id3);
		System.out.println(n);
	}
}


























