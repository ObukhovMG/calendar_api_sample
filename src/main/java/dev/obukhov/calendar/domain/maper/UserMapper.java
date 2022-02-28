package dev.obukhov.calendar.domain.maper;

import dev.obukhov.calendar.domain.dto.UserDtoResp;
import dev.obukhov.calendar.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDtoResp userToUserDtoResp(User user);
}
