package lbw.practice.service.user;

import lbw.practice.dto.user.UserDto;
import lbw.practice.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //회원가입 시, 비밀번호 입력확인(null or 빈문자열 아닌지)
    private void validatePw(String pw) {
        if (pw == null || pw.isBlank()) {
            throw new IllegalArgumentException("비밀번호를 입력해주세요.");
        }
    }

    //비밀번호 입력 시, 4자 이상 입력했는지 확인
    private void validatePwLength(String pw) {
        if (pw.length() < 4) {
            throw new IllegalArgumentException("비밀번호는 4자 이상이여야 합니다.");
        }
    }

    //회원가입
    public void registerUser(UserDto userDto) {
       validatePw(userDto.getPw());
       validatePwLength(userDto.getPw());

        userRepository.registerUser(userDto);
    }

    //id 이용해서 유저정보 찾기
    public UserDto findById(String id) {
        return userRepository.findById(id);
    }

}
