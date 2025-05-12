package lbw.practice.service;

import lbw.practice.dto.user.UserDto;
import lbw.practice.service.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @DisplayName("회원가입 테스트")
    @Test
    void registerUser() {
        UserDto userDto = new UserDto();
        userDto.setId("asd123");
        userDto.setPw("test123");
        userService.registerUser(userDto);

        UserDto savedUser = userService.findById("asd123");
        assertNotNull(savedUser);
        assertEquals("asd123", savedUser.getId());
    }

    @DisplayName("회원가입 예외 처리, pw 없을 시")
        @Test
        void registerUser_validPw() {
            UserDto userDto = new UserDto();
            userDto.setId("asd1234");
            userDto.setPw(null);

            assertThrows(IllegalArgumentException.class,
                    () -> userService.registerUser(userDto),
                    "비밀번호 누락 시 예외가 발생해야 합니다");

            userDto.setPw("");
            assertThrows(IllegalArgumentException.class,
                    () -> userService.registerUser(userDto),
                    "빈 문자열 비밀번호 시에도 예외가 발생해야 합니다");
    }

    @DisplayName("회원가입시 예외 처리, pw 4자 이상 입력")
    @Test
    void registerUser_shortPw() {
        UserDto userDto = new UserDto();
        userDto.setId("asd1234");
        userDto.setPw("123");

        assertThrows(IllegalArgumentException.class,
                () -> userService.registerUser(userDto),
                "비밀번호는 4자 이상 입력해야 합니다.");
    }
}
