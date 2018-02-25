package cn.tedu.cloudnote.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloudnote.service.NoteService;
import cn.tedu.cloudnote.util.JsonResult;

@Controller
@RequestMapping("/note")
public class NoteController extends ExceptionController{
	@Resource
	private NoteService noteService;
	
	@RequestMapping("/list.do")
	@ResponseBody
	public JsonResult list(String bookId){
		List<Map<String,Object>> list = noteService.loadNotes(bookId);
		return new JsonResult(list);
	}
	
	@RequestMapping("/loadnote.do")
	@ResponseBody
	public JsonResult loadNote(String noteId){
		return new JsonResult(noteService.loadNote(noteId));
	}
	
	@RequestMapping("/update.do")
	@ResponseBody
	public JsonResult updateNote(String noteId,String noteTitle,String noteBody){
		boolean b=
		noteService.updateNote(noteId, noteTitle, noteBody);
		return new JsonResult(b);
	}
	
	@RequestMapping("/delete.do")
	@ResponseBody
	public JsonResult deleteNote(String noteId){
		int rows=noteService.deleteNote(noteId);
		return new JsonResult(rows);
	}
}








