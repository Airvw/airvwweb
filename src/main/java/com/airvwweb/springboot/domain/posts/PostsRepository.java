package com.airvwweb.springboot.domain.posts;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// Dao라고 불리는 DB Layer 접근자. Jpa에서는 Repository라고 부름
// JpaRepository<Entitiy 클래스, PK타입>를 상속하면 기본적인 CRUD 메소드가 자동 생성
// Entity 클래스와 함께 위치 해야됨.
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
