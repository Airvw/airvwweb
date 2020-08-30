package com.airvwweb.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
//각 사용자의 권한을 관리할 Enum 클래스
//한정된 값만을 가지는 데이터 타입이 열거 타입(Enumeration type)이다
public enum Role {
//    스프링 서큐리티에서 권한 코드에 항상 ROLE_이 앞에 있어야만 한다.
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;
}
