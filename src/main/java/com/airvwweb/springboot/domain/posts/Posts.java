package com.airvwweb.springboot.domain.posts;

//import com.airvwweb.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
// 기본 생성자 자동 추가. public Posts(){}와 같은 효과
@NoArgsConstructor
// @Enitity: 테이블과 링크될 클래스임을 나타냄. 언더스코어 네이밍(_)으로 매칭 ex) SalesManger.java -> sales_manager table
@Entity
// 실제 DB의 테이블과 매칭될 클래스
public class Posts {

//    해당 테이블의 PK필드를 나타냄
    @Id
//     PK의 생성 규칙을 나타냄. 스프링부트 2.0에서는 GenerationType.IDENTITY를 추가해야만 auto_increment가 됨
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    테이블의 칼럼. 굳이 선언하지 않아도 모두 컬럼이 됨. 사용하는 이유는 기본값 외에 추가로 변경이 필요한 옵션이 있어서 사용
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

//    해당 클래스의 빌더 패턴 클래스를 생성. 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
//    빌더를 사용하면 어느 필드에 어떤 값을 채워야할지 명확하게 인지할 수 있음
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}