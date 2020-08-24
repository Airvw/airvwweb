package com.airvwweb.springboot.web;

import com.airvwweb.springboot.service.posts.PostsService;
import com.airvwweb.springboot.web.dto.PostsResponseDto;
import com.airvwweb.springboot.web.dto.PostsSaveRequestDto;
import com.airvwweb.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//생성자로 Bean 객체를 받도록 하면 @Autowired 와 동일한 효과
// 생성자를 직접 안쓰고 롬복 어노테이션을 사용하는 이유는 의존성 관계가 변경될 때 마다 생성자 코드를 계속 수정하는 번거로움 해결
@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){

        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }
}
