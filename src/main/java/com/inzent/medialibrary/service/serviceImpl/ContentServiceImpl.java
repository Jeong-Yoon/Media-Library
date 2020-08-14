package com.inzent.medialibrary.service.serviceImpl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.jcodec.api.JCodecException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.inzent.medialibrary.dto.ContentDTO;
import com.inzent.medialibrary.dto.ContentDetailDTO;
import com.inzent.medialibrary.dto.ContentVO;
import com.inzent.medialibrary.dto.FolderIdDTO;
import com.inzent.medialibrary.dto.ImageDTO;
import com.inzent.medialibrary.dto.ImageListDTO;
import com.inzent.medialibrary.dto.MoveFolderDTO;
import com.inzent.medialibrary.dto.ParentIdDTO;
import com.inzent.medialibrary.dto.SelectTargetDTO;
import com.inzent.medialibrary.dto.UploadContentDTO;
import com.inzent.medialibrary.dto.VideoListDTO;
import com.inzent.medialibrary.repository.ContentDAO;
import com.inzent.medialibrary.repository.UserDAO;
import com.inzent.medialibrary.service.ContentService;
import com.inzent.medialibrary.utils.GetThumbnail;
import com.inzent.medialibrary.utils.MakeDir;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private ContentDAO contentDAO;
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public List<ContentVO> getImageList(Long folderId) {
		return contentDAO.getImageList(folderId);
	}

	@Override
	public int uploadContent(ContentDTO contentDTO) {
		UploadContentDTO ucDTO = new UploadContentDTO();
		try {
			MakeDir dir = new MakeDir();
			String baseDir = dir.makeDir();
			MultipartFile uploadContent = contentDTO.getAttachFiles()[0];
			String ext = FilenameUtils.getExtension(uploadContent.getOriginalFilename()); 
			String saveName = UUID.randomUUID().toString() + "." + ext;
	        baseDir = baseDir + "/" + saveName;
			uploadContent.transferTo(new File(baseDir));
			ucDTO.setUser_id(userDAO.getUserIdByEmail(contentDTO.getRegUser()));
			ucDTO.setFolder_id(contentDTO.getFolder());
			ucDTO.setContent_origin_name(uploadContent.getOriginalFilename());
			ucDTO.setContent_size(uploadContent.getSize());
			ucDTO.setContent_storage(baseDir);
			ucDTO.setContent_save_name(saveName);
			if (uploadContent.getContentType().startsWith("video")) {
				ucDTO.setContent_type("V");
			} else if (uploadContent.getContentType().startsWith("image")) {
				ucDTO.setContent_type("I");
			} else {
				return 0;
			}
			System.out.println(ucDTO.toString());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("extension", ext);
			ucDTO.setContent_attribute(map);
			BufferedImage image = ImageIO.read(uploadContent.getInputStream());
//			Map<String, Object> map = new HashMap<String, Object>();
			int height = image.getHeight();
			int width = image.getWidth();
			map.put("resolution", height + "*" + width);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contentDAO.uploadContent(ucDTO);
	}

	@Override
	public void uploadContentDetail(ContentDetailDTO detailDTO, Map<String, Object> json) {
		System.out.println(detailDTO.getContent_attribute() + "in service");
		System.out.println(contentDAO.uploadContentDetail(detailDTO, json));
	}
	
	@Override
	public void getContentDetail(Long contentId) {
		ContentDetailDTO cdDTO = contentDAO.getContentDetail(contentId);
		System.out.println(cdDTO.getContent_id()+"11111111111111111111");
		System.out.println(cdDTO.getContent_attribute());
		System.out.println(contentDAO.getContentDetail(contentId).getContent_attribute());
	}

	@Override
	public ImageDTO getContentById(Long image_id) {
		ImageDTO image = contentDAO.getContentById(image_id);
		InputStream in;
		try {
			in = new FileInputStream(image.getContent_storage());
			image.setContent(IOUtils.toByteArray(in));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}

	@Override
	public int deleteContent(List<Long> ids) {
		System.out.println(ids.size() + " content delete service");
		return contentDAO.deleteContent(ids);
	}

	@Override
	public List<ImageDTO> selectTarget(SelectTargetDTO selectTargetDTO) {
		if(selectTargetDTO.getTarget().equals("image")) {
			selectTargetDTO.setTarget("I");
		} else if(selectTargetDTO.getTarget().equals("video")) {
			selectTargetDTO.setTarget("V");
		}
		List<ImageDTO> list = contentDAO.selectTarget(selectTargetDTO);
		for(ImageDTO i : list) {
			if (i.getContent_type().equals("V")) {
				break;
			} else {
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
	public List<ImageDTO> getImageList(FolderIdDTO folderIdDTO) throws IOException, JCodecException {
		List<ImageDTO> list = contentDAO.getImageList(folderIdDTO);
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
	public List<ImageDTO> getVideoList(VideoListDTO videoListDTO) throws IOException, JCodecException {
		List<ImageDTO> list = contentDAO.getVideoList(videoListDTO);
		for(ImageDTO i : list) {
			File file = new File(i.getContent_storage());
			i.setContent(GetThumbnail.getThumbnail(file));
		}
		return list;
	}

	@Override
	public int moveFolder(MoveFolderDTO moveFolderDTO) {
		return contentDAO.moveFolder(moveFolderDTO);
	}
}
