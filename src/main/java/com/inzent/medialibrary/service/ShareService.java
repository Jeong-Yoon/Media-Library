package com.inzent.medialibrary.service;

import java.util.List;

public interface ShareService {

	int unshareItems(List<Long> ids);

	int shareItems(List<Long> ids);

	public List<String> getShareItems();
}
