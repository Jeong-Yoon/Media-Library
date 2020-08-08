package com.inzent.medialibrary.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inzent.medialibrary.repository.ShareDAO;
import com.inzent.medialibrary.service.ShareService;

@Service
public class ShareServiceImpl implements ShareService{
	
	@Autowired
	private ShareDAO shareDAO;
	
	@Override
	public int unshareItems(List<Long> ids) {
		return shareDAO.unshareItem(ids);
	}

	@Override
	public int shareItems(List<Long> ids) {
		return shareDAO.shareItem(ids);
	}
	
	@Override
	public List<String> getShareItems() {
		System.out.println("get service item");
		return shareDAO.getShareItems();
	}
}
