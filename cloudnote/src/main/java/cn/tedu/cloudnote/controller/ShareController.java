package cn.tedu.cloudnote.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloudnote.entity.Share;
import cn.tedu.cloudnote.service.ShareService;
import cn.tedu.cloudnote.util.JsonResult;

@Controller
@RequestMapping("/share")
public class ShareController {
	@Resource
	private ShareService shareService;
	
	@RequestMapping("/add.do")
	@ResponseBody
	public JsonResult shareNote(String noteId){
		shareService.shareNote(noteId);
		JsonResult result=new JsonResult();
		result.setMessage("笔记分享成功");
		return result;
	}
	
	@RequestMapping("/search.do")
	@ResponseBody
	public JsonResult searchNote(String keyword,int page){
		List<Share> list=shareService.searchNote(keyword,page);
		return new JsonResult(list);
	}
}







