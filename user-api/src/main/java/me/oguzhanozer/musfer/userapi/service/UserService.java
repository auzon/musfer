package me.oguzhanozer.musfer.userapi.service;

import me.oguzhanozer.musfer.userapi.entity.User;
import me.oguzhanozer.musfer.userapi.exception.EmailAlreadyExistsException;
import me.oguzhanozer.musfer.userapi.exception.UserNotFoundException;
import me.oguzhanozer.musfer.userapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException(user.getEmail());
        }
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

}
