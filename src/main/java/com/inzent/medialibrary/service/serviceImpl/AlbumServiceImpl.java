package com.inzent.medialibrary.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inzent.medialibrary.dto.AddAlbumDTO;
import com.inzent.medialibrary.dto.AddConAlbumDTO;
import com.inzent.medialibrary.dto.AlbumVO;
import com.inzent.medialibrary.dto.ImageDTO;
import com.inzent.medialibrary.repository.AlbumDAO;
import com.inzent.medialibrary.service.AlbumService;

@Service
public class AlbumServiceImpl implements AlbumService{
	@Autowired
	private AlbumDAO albumDAO;

	@Override
	public List<String> getAlbumNameList(String useremail) {
		return albumDAO.getAlbumNameList(useremail);
	}

	@Override
	public int addAlbum(AddAlbumDTO addAlbumDTO) {
		return albumDAO.addAlubum(addAlbumDTO);
	}

	@Override
	public int deleteAlbums(List<Long> albumIds) {
		return albumDAO.deleteAlbums(albumIds);
	}

	@Override
	public List<AlbumVO> getAlbumList(String useremail) {
		return albumDAO.getAlbumList(useremail);
	}

	@Override
	public List<ImageDTO> getContentInAlbum(Long album_id) {
		return albumDAO.getContentInAlbum(album_id);
	}

	@Override
	public int addContent(AddConAlbumDTO addConAlbumDTO) {
		return albumDAO.addContent(addConAlbumDTO);
	}

	@Override
	public int deleteContents(AddConAlbumDTO addConAlbumDTO) {
		return albumDAO.deleteContents(addConAlbumDTO);
	}
}
