package com.shop.service;

import com.shop.entity.ItemImg;
import com.shop.repository.ItemImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemImgService {
    @Value("itemImgLocation")
    private String itemImgLocation;

    private final ItemImgRepository itemImgRepository;

    private final FileService fileService;

    public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws Exception{
        String oriImgName = itemImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        //파일 업로드
        if(!StringUtils.isEmpty(oriImgName)){
            imgName = fileService.uploadFile(itemImgLocation,oriImgName,itemImgFile.getBytes());
            //uploadFile을 호출하여 파일을 업로드하고
            imgUrl = "/images/item" + imgName;
            //업로드된 파일의 이름과 url을 얻어옵니다.
            //WebMvcConfig - "/images/**"
            //application.properties uploadpath D:/springboot/shop
            //아래 item 폴더에 이미지를 저장
            //이미지를 불러오는 경로는 "/images/item"
        }
    itemImg.updateItemImg(oriImgName, imgName, imgUrl);
    itemImgRepository.save(itemImg);
    }

}
