package az.edu.turing.msuser.mapper;

import az.edu.turing.msuser.domain.entity.UserEntity;
import az.edu.turing.msuser.dto.request.UserRegisterRequest;
import az.edu.turing.msuser.dto.response.UserRegisterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", expression = "java(az.edu.turing.msuser.enums.Role.USER)")
    @Mapping(target = "status", constant = "ACTIVE")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    UserEntity toUserEntity(UserRegisterRequest userRequestDto);

    UserRegisterResponse toUserResponse(UserEntity userEntity);
}
