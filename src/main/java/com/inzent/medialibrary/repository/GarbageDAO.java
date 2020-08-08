package com.inzent.medialibrary.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inzent.medialibrary.dto.ImageDTO;

@Repository
public class GarbageDAO {

	@Autowired
	private SqlSession sqlSession;

	public List<String> getGarbageList(String userEmail) {
		return sqlSession.selectList("gettrash", userEmail);
	}

	public int restoreContent(List<Long> contentIdList) {
		return sqlSession.update("restoreitems", contentIdList);
	}

	
//	public List<ImageDTO> getDeleteList(List<Long> contentIdList) {
//		return sqlSession.selectList("getitemlist", contentIdList);
//	}

	public  List<ImageDTO> getDeleteList(List<Long> contentIdList) {
//		List<ImageDTO> list = new ArrayList<ImageDTO>();
		List<ImageDTO> slist = sqlSession.selectList("getharddeleteitem", contentIdList);

//		for (int i = 0; i < contentIdList.size(); i++) {
//			if (contentIdList.get(i).toString().startsWith("2")) {
//				List<ImageDTO> slist = sqlSession.selectList("getharddeleteitem", contentIdList.get(i));
//				for (ImageDTO idto : slist) {
//					list.add(idto);
//				}
//			} else {
//				list.add(sqlSession.selectOne("getcontentbyid", contentIdList.get(i)));
//			}
//		}

//		List<List<ImageDTO>> list = new ArrayList<List<ImageDTO>>();
//		for(int i = 0; i < contentIdList.size(); i++) {
//			list.add(sqlSession.selectList("getharddeleteitem", contentIdList.get(i)));
//		}
		System.out.println("slist size : " + slist.size());
		return slist;
//		return sqlSession.delete("harddeleteitems",contentIdList);
	}
	
	public int hardDelete(List<Long> contentIdList, List<ImageDTO> contentList, long sum, String email) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("===============");
//		System.out.println(sqlSession.selectList("getharddeletefolderid",contentIdList));
		map.put("sum", sum);
		map.put("userId", email);
		sqlSession.update("minususecapacity",map);
		List<Long> cList = new ArrayList<Long>();
		for(ImageDTO i : contentList) {
			cList.add(i.getContent_id());
		}
		if(contentList.size() != 0) {
			sqlSession.delete("harddeletecontent", cList);
		}
		System.out.println(contentIdList.size());
//		sqlSession.delete("harddeletefolder", contentIdList);
		return sqlSession.delete("harddeletefolder", contentIdList);
	}
}
