package com.inzent.medialibrary.service;

import java.io.IOException;
import java.util.List;

import org.jcodec.api.JCodecException;

import com.inzent.medialibrary.dto.AddAlbumDTO;
import com.inzent.medialibrary.dto.AddConAlbumDTO;
import com.inzent.medialibrary.dto.AlbumVO;
import com.inzent.medialibrary.dto.ImageDTO;

public interface AlbumService {

	List<String> getAlbumNameList(String useremail);

	int addAlbum(AddAlbumDTO addAlbumDTO);

	int deleteAlbums(List<Long> albumIds);

	List<AlbumVO> getAlbumList(String useremail);

	List<ImageDTO> getContentInAlbum(Long album_id) throws IOException, JCodecException;

	int addContent(AddConAlbumDTO addConAlbumDTO);

	int deleteContents(AddConAlbumDTO addConAlbumDTO);

}
