package cn.tedu.cloudnote.service;

import java.util.List;

import cn.tedu.cloudnote.entity.Share;

public interface ShareService {
	public void shareNote(String noteId);
	public List<Share> searchNote(String keyword,int page);
}









