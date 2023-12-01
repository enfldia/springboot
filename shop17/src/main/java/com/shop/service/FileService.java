package com.shop.service;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
@Log
public class FileService {

    public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws Exception {
        // 파일에 고유 식별자(UUID)를 생성, 이를 사용해서 저장된 파일 이름을 만듭니다.
        UUID uuid = UUID.randomUUID();
        // 원본 파일에서 확장자를 추출
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        // 고유한 식별자와 추출한 파일을 조합하여 저장될 파일명 생성
        String savedFileName = uuid.toString() + extension;
        // 파일을 저장할 전체 경로 생성
        String fileUploadFullUrl = uploadPath + "/" + savedFileName;
        // FileOutputStream 바이트 단위로 출력을 내보내는 클래스
        // 파일을 저장하기 위한 FileOutputStream 생성
        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
        fos.write(fileData); // filedata 배열의 내용을 파일에 기록
        fos.close(); // 파일 출력 스트림을 닫습니다.
        return savedFileName; // 저장된 파일의 이름을 반환합니다.
    }

    public void deleteFile(String filePath) throws Exception {
        File deleteFile = new File(filePath);

        if(deleteFile.exists()) {
            deleteFile.delete();
            log.info("파일을 삭제하였습니다.");
        } else {
            log.info("파일이 존재하지 않습니다.");
        }
    }
}
