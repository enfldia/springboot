package com.example.secondproject.api;

import com.example.secondproject.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController //@RestController는 @Controller에 @ResponseBody가 추가된 것입니다. 당연하게도 RestController의 주용도는 Json 형태로 객체 데이터를 반환하는 것입니다.
@Slf4j
@RequiredArgsConstructor
public class AtricleApiController {
    private final ArticleService articleService;

}
