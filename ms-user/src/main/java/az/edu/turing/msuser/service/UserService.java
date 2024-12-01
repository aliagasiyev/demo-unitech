package az.edu.turing.msuser.service;

import az.edu.turing.msuser.dto.request.UserRegisterRequest;
import az.edu.turing.msuser.dto.response.UserRegisterResponse;

import java.util.Optional;

public interface UserService {

    UserRegisterResponse create(UserRegisterRequest userDto);

    void deleteAll();

    void deleteById(Long id);

    UserRegisterResponse update(Long id, UserRegisterRequest userDto);

    Optional<UserRegisterResponse> getByFin(String fin);

    Optional<UserRegisterResponse> getByEmail(String email); // Yeni metod
}
