package com.inzent.medialibrary.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ShareDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<String> getShareItems() {
		System.out.println("share item dao");
		return sqlSession.selectList("getshareitems");
	}
	
	public int unshareItem(List<Long> ids) {
		return sqlSession.update("unshareItems", ids);
	}

	public int shareItem(List<Long> ids) {
		return sqlSession.update("shareItems", ids);
	}
}
