package com.airvwweb.springboot.web;

import com.airvwweb.springboot.service.posts.PostsService;
import com.airvwweb.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

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

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
