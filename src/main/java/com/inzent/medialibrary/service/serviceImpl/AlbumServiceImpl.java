package com.inzent.medialibrary.service.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.jcodec.api.JCodecException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inzent.medialibrary.dto.AddAlbumDTO;
import com.inzent.medialibrary.dto.AddConAlbumDTO;
import com.inzent.medialibrary.dto.AlbumVO;
import com.inzent.medialibrary.dto.ImageDTO;
import com.inzent.medialibrary.repository.AlbumDAO;
import com.inzent.medialibrary.service.AlbumService;
import com.inzent.medialibrary.utils.GetThumbnail;

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
	public List<ImageDTO> getContentInAlbum(Long album_id) throws IOException, JCodecException {
		List<ImageDTO> list = albumDAO.getContentInAlbum(album_id);
		for(ImageDTO i : list) {
			if (i.getContent_type().equals("V")) {
				File file = new File(i.getContent_storage());
				i.setContent(GetThumbnail.getThumbnail(file));
			} else if(i.getContent_type().equals("I")){
				InputStream in;
				try {
					in = new FileInputStream(i.getContent_storage());
					i.setContent(IOUtils.toByteArray(in));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
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
