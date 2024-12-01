package az.edu.turing.msuser.controller;

import az.edu.turing.msuser.dto.request.UserRegisterRequest;
import az.edu.turing.msuser.dto.response.UserRegisterResponse;
import az.edu.turing.msuser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserRegisterResponse> createUser(@RequestBody UserRegisterRequest userRequestDto) {
        UserRegisterResponse userResponse = userService.create(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping("/{fin}")
    public ResponseEntity<Optional<UserRegisterResponse>> getUserByFin(@PathVariable String fin) {
        Optional<UserRegisterResponse> userResponse = userService.getByFin(fin);
        return ResponseEntity.ok(userResponse);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<Optional<UserRegisterResponse>> getUserByEmail(@PathVariable String email) {
        Optional<UserRegisterResponse> userResponse = userService.getByEmail(email);
        return ResponseEntity.ok(userResponse);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserRegisterResponse> updateUser(@PathVariable Long id, @RequestBody UserRegisterRequest userRequestDto) {
        UserRegisterResponse updatedUser = userService.update(id, userRequestDto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok("User with ID " + id + " has been deleted successfully.");
    }


    @DeleteMapping
    public ResponseEntity<Void> deleteAllUsers() {
        userService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
