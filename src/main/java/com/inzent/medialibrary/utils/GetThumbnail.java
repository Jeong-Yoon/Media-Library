package com.inzent.medialibrary.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;

public class GetThumbnail {
	
	private static final String IMAGE_PNG_FORMAT = "png";

	public static byte[] getThumbnail(File source) throws IOException, JCodecException {
		int frameNumber = 0;
//		File thumbnail = new File("frame"+0+".png");
		Picture picture = FrameGrab.getFrameFromFile(source, frameNumber);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedImage bufferedImage = AWTUtil.toBufferedImage(picture);
		ImageIO.write(bufferedImage, IMAGE_PNG_FORMAT, baos);
		byte[] byteimage = baos.toByteArray();
		return byteimage; 
	}
}
