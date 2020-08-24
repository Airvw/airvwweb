package com.airvwweb.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// Junit에 내장된 실행자 외에 다른 실행자 실행 -> 여기서는 SpringRunner 실행자를 사용
// 즉, 스프링 부트 테스트와 Junit 연결자 역할
@RunWith(SpringRunner.class)
// 별다른 설정 없이 H2 데이터베이스를 자동으로 실행
@SpringBootTest
public class PostsRepositoryTest {
//    스프링이 관리하는 빈(bean)을 주입 받음
    @Autowired
    PostsRepository postsRepository;

//    Junit에서 단위 테스트가 끝날 때마다 수행되는 메소드를 지정. 보통 배포 전 전체 테스트 수행할 때 데이터간 침범을 막기 위해 사용
//    여러 테스트가 동시에 실행되면 테스트용 데이터베이스인 H2에 데이터가 그대로 남아 있어 실패 할 수 있음
    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("airvw14@gmail.com")
                .build());
        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
