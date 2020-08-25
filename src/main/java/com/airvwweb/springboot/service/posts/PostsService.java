package com.airvwweb.springboot.service.posts;

import com.airvwweb.springboot.domain.posts.Posts;
import com.airvwweb.springboot.domain.posts.PostsRepository;
import com.airvwweb.springboot.web.dto.PostsResponseDto;
import com.airvwweb.springboot.web.dto.PostsSaveRequestDto;
import com.airvwweb.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

//    update 기능에서는 쿼리를 날리는 부분이 없다.(내 생각에는 PostsRepository의 CRUD 메소드가 활용이 안됬다는 뜻 인것 같다.)
//    JPA의 영속성 컨텍스트 때문-> 엔티티를 영구 저장하는 환경. jPA의 핵심내용은 엔티티가 영속성 컨텍스트에 포함되어 있냐 아니냐로 갈림
//    JPA의 엔티티 매니저가 활성화된 상태로 트랜잭션 안에서 데이터베이스에서 데이터를 가져오면 이 데이터는 영속성 컨텍스트가 유지된 상태
//    -> 이 상태에서 해당 데이터 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영함. 그러므로 update 쿼리가 필요없음.
//    이 개념은 더티 체킹
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해답 게시글이 없습니다. id=" + id ));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습닌다. id=" + id));

        return new PostsResponseDto(entity);
    }
}

