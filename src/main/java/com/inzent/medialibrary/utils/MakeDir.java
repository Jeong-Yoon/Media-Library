package com.inzent.medialibrary.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MakeDir {
	public String makeDir() {
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMdd");
		String folderPath = "C:/Users/bit-user/Desktop/media_storage/";
//		Date date = new Date();
//        File makeFolder = new File(folderPath);
        
//        ServletContext context = HttpRequest.getServletContext();
//        String uploadPath = context.getRealPath("/upload");
        
        // upload 하위에 모듈별 날짜 형태의 디렉토리 생성후 저장
        SimpleDateFormat sdf = new SimpleDateFormat(
           "yyyy/MM/dd"   
        );
        String datePath = sdf.format(new Date());
        
        folderPath += datePath;
        
        File f = new File(folderPath);
        if (!f.exists()) {
           f.mkdirs();
        }
        
//		if (!makeFolder.exists()) {
//			// 폴더를 생성합니다.
//			makeFolder.mkdir();
//			System.out.println("폴더를 생성합니다.");
//			// 정성적으로 폴더 생성시 true를 반환합니다.
//			System.out.println("폴더가 존재하는지 체크 true/false : " + makeFolder.exists());
//		} else {
//			System.out.println("이미 해당 폴더가 존재합니다.");
//		}
		return folderPath;
	}
}
