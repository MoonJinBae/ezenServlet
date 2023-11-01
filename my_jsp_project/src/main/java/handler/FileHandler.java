package handler;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileHandler {
	private static final Logger log = LoggerFactory.getLogger(FileHandler.class);
	
	public int deleteFile(String imgFileName, String savePath) {
		boolean isDel = true; // 삭제가 잘 이루어졌는지 체크하는 변수
		log.info("imgFileName >> " + imgFileName);
		
		File fileDir = new File(savePath);
		File removeFile = new File(fileDir + File.separator+imgFileName);
		File removeThFile = new File(fileDir + File.separator + "_th_" + imgFileName);
		
		// 현 시점에서 삭제하고자 하는 파일이 있어야(존재해야) 삭제
		if(removeFile.exists() || removeThFile.exists()) {
			isDel = removeFile.delete(); // boolean 형태로 리턴
			log.info("del >> " + (isDel ? "Ok" : "Fail"));
			if(isDel) {
				isDel = removeThFile.delete();
				log.info("thFile del >> " + (isDel ? "Ok" : "Fail"));
			}
		}
		return isDel ? 1 : 0;
	}
}
