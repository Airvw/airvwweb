package com.airvwweb.springboot.config.auth;

import com.airvwweb.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
// Spring Security 설정들을 활성화
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    // 선택적 보안을 적용하기 위한 오버라이딩
    @Override
    protected void configure(HttpSecurity http) throws Exception{
//        Spring Security에서 h2-console 접근 차단 -> h2-console 화면을 사용하기 위해 해당 옵션들을 disable
        http.csrf().disable().headers().frameOptions().disable()
                .and()
//                URL별 권한 관리를 설정하는 옵션의 시작점
                    .authorizeRequests()
//                권한 관리 대상을 지정하는 옵션. authorizeRequests가 선언되어야 사용 가능
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
//               Role.USER.name() name() 메소드가 어디서 나온건지 모르겠다.
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
//                설정된 값들 이외 나머지 URL들은 인증된 사용자들(로그인한 사용자들)에게 허용
                    .anyRequest().authenticated()
                .and()
//                로그아웃 기능에 대한 설정의 진입점.
                    .logout()
//                로그아웃 성공시 / 주소로 이동
                        .logoutSuccessUrl("/")
                .and()
//                OAuth2 로그인 기능에 대한 설정 진입점
                    .oauth2Login()
//                OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들
                        .userInfoEndpoint()
//                소셜 로그인 성공시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
//                리소스 서버(소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시
                            .userService(customOAuth2UserService);
    }
}
