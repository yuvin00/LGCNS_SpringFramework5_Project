package mylab.user.di.annot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:mylab-user-di.xml")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testUserService() {
        // 서비스와 의존 객체가 주입되었는지 확인
        assertNotNull(userService);
        assertNotNull(userService.getUserRepository());
        assertNotNull(userService.getSecurityService());

        // @Value로 주입한 값 확인
        assertEquals(
                "MySQL",
                userService.getUserRepository().getDbType());
    }

    @Test
    public void testRegisterUserWithPassword() {
        // 비밀번호가 있으면 인증 후 저장 성공
        assertTrue(
                userService.registerUser(
                        "user01", "홍길동", "password123"));
    }

    @Test
    public void testRegisterUserWithoutPassword() {
        // 비밀번호가 null이면 실패
        assertFalse(
                userService.registerUser(
                        "user02", "김철수", null));

        // 비밀번호가 빈 문자열이면 실패
        assertFalse(
                userService.registerUser(
                        "user03", "이영희", ""));
    }
}