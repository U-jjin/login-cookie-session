package com.login.loginproject.web.session;

import com.login.loginproject.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SessionManagerTest {

    SessionManager sessionManager =new SessionManager();


    @Test
    void sessionTest() {
        //세션 생성
        //서블렛은 인터페이스이기 떄문에 MOck 클래스를 통해 테스트가 가능합니다.
        MockHttpServletResponse response = new MockHttpServletResponse();
        Member member = new Member();
        sessionManager.createSession(member,response);


        //웹브라우저가 쿠키를 전달해주는 상황
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        //세션 조회
        Object result = sessionManager.getSession(request);
        Assertions.assertThat(result).isEqualTo(member);

        //세션 만료
        sessionManager.expire(request);
        Object session = sessionManager.getSession(request);
        assertThat(session).isNull();

    }


}