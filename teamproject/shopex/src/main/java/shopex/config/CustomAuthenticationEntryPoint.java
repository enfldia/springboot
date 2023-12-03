package shopex.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//AuthenticationEntryPoint 추상 메소드를 상속한다.
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /*
    * 인증 처리 예외 (신원 검증)
    * RequsetCatch : 인증 성공 시 이전 요청 정보로 이동하기 위한 캐시 저장
    * SavedRequest : 사용자 요청 파라미터 및 헤더 정보
    * */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }

}