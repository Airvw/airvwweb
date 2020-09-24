package com.airvwweb.springboot.web;

import com.airvwweb.springboot.config.auth.LoginUser;
import com.airvwweb.springboot.config.auth.dto.SessionUser;
import com.airvwweb.springboot.service.posts.PostsService;
import com.airvwweb.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



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
//    어느 컨트롤러든지 @LoginUser만 사용하면 세션 정보를 가져올 수 있게 되었습니다.
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());

//   index.mustache에서 userName을 사용할 수 있게 indexController에서 userName을 model에 저장하는 코드를 추가

//      CustomOAuth2UserService에서 로그인 성공 시 세션에 SessionUser를 저장하도록 구성
//        -> 로그인 성공시 httpSession.getAttribute("user")에서 값을 가져올 수 있음
//      index 메소드 외에 다른 컨트롤러와 메소드에서 세션값이 필요하면 그때마다 직접 세션을 가져와야하는 반복 코드
//        SessionUser user = (SessionUser) httpSession.getAttribute("user");
//      -> 메소드 인자로 세션값을 바로 받을 수 있도록 변경

//       세션에 저장된 값이 있을 때만 model에 userName으로 등록
//       세션에 저장된 값이 없으면 model에 아무 값이 없으므로 로그인 버튼이 보이게 됨
        if(user != null){
            model.addAttribute("userName", user.getName());
        }

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
