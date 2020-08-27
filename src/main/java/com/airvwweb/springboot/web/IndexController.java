package com.airvwweb.springboot.web;

import com.airvwweb.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
// RestController -> JSON/XML 형태로 객체 데이터를 반환하는 것
// Controller -> View를 반환하는 것
@Controller
// 페이지에 관련된 컨트롤러 모두 IndexController
public class IndexController {
    private final PostsService postsService;

    @GetMapping("/")
//    Model : 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장 할 수 있다.
//    postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달. model 객체 이름은 posts로 저장.
    public String index(Model model){
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }
}
