package cn.tedu.cloudnote.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.cloudnote.entity.Share;

public interface ShareDao {
	public void save(Share share);
	public List<Share> findLikeTitle(Map params);
}









