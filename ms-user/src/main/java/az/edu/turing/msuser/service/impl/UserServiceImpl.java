package az.edu.turing.msuser.service.impl;

import az.edu.turing.msuser.domain.entity.UserEntity;
import az.edu.turing.msuser.domain.repository.UserRepository;
import az.edu.turing.msuser.dto.request.UserRegisterRequest;
import az.edu.turing.msuser.dto.response.UserRegisterResponse;
import az.edu.turing.msuser.exceptions.UserNotFoundException;
import az.edu.turing.msuser.mapper.UserMapper;
import az.edu.turing.msuser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserRegisterResponse create(UserRegisterRequest userDto) {
        UserEntity userEntity = userMapper.toUserEntity(userDto);
        userRepository.save(userEntity);
        return userMapper.toUserResponse(userEntity);
    }

    @Override
    public Optional<UserRegisterResponse> getByFin(String fin) {
        UserEntity userEntity = findUserByFin(fin);
        return Optional.of(userMapper.toUserResponse(userEntity));
    }

    @Override
    public Optional<UserRegisterResponse> getByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
        return Optional.of(userMapper.toUserResponse(userEntity));
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public void deleteById(Long id) {
        UserEntity userEntity = findUserById(id);
        userRepository.deleteById(userEntity.getId());
    }

    @Override
    public UserRegisterResponse update(Long id, UserRegisterRequest userDto) {
        UserEntity userEntity = findUserById(id);
        userEntity.setFullName(userDto.getFullName());
        userEntity.setEmail(userDto.getEmail());
        userRepository.save(userEntity);
        return userMapper.toUserResponse(userEntity);
    }

    // Köməkçi metod: İstifadəçini ID əsasında tap
    private UserEntity findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    // Köməkçi metod: İstifadəçini FIN əsasında tap
    private UserEntity findUserByFin(String fin) {
        return userRepository.findByFin(fin)
                .orElseThrow(() -> new UserNotFoundException("User not found with fin: " + fin));
    }
}
