package lbw.practice.repository.user;

import lbw.practice.dto.user.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRepository {
    //회원가입
    void registerUser(UserDto userDto);

    //id 이용해서 유저정보 찾기
    UserDto findById(String id);
}
