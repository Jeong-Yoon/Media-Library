package com.inzent.medialibrary.service.serviceImpl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.inzent.medialibrary.dto.ContentDTO;
import com.inzent.medialibrary.dto.ContentDetailDTO;
import com.inzent.medialibrary.dto.ContentVO;
import com.inzent.medialibrary.dto.UploadContentDTO;
import com.inzent.medialibrary.repository.ContentDAO;
import com.inzent.medialibrary.service.ContentService;
import com.inzent.medialibrary.utils.MakeDir;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private ContentDAO contentDAO;

	@Override
	public List<ContentVO> getContentList(Long folderId) {
		return contentDAO.getContentList(folderId);
	}

	@Override
	public int uploadContent(ContentDTO contentDTO) {
		try {
			MakeDir dir = new MakeDir();
			String baseDir = dir.makeDir();
			MultipartFile uploadContent = contentDTO.getContent();
			String ext = FilenameUtils.getExtension(uploadContent.getOriginalFilename()); 
	        baseDir = baseDir + "/" + UUID.randomUUID().toString() + "." + ext;
			uploadContent.transferTo(new File(baseDir));

			UploadContentDTO ucDTO = new UploadContentDTO();
			ucDTO.setContent_reg_user(contentDTO.getRegUser());
			ucDTO.setFolder_id(contentDTO.getFolder());
			ucDTO.setContent_origin_name(uploadContent.getOriginalFilename());
			ucDTO.setContent_size(uploadContent.getSize());
			ucDTO.setContent_storage(baseDir);
			ucDTO.setContent_save_name(baseDir);
			if (uploadContent.getContentType().startsWith("video")) {
				ucDTO.setContent_type("V");
			} else if (uploadContent.getContentType().startsWith("image")) {
				ucDTO.setContent_type("I");
			} else {
				return 0;
			}
			System.out.println(ucDTO.toString());
			BufferedImage image = ImageIO.read(uploadContent.getInputStream());
			Map<String, Object> map = new HashMap<String, Object>();
			int height = image.getHeight();
			int width = image.getWidth();
			map.put("해상도", height + "*" + width);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
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
}
