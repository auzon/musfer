package me.oguzhanozer.musfer.userservice.service;

import lombok.RequiredArgsConstructor;
import me.oguzhanozer.musfer.userservice.entity.User;
import me.oguzhanozer.musfer.userservice.exception.EmailAlreadyExistsException;
import me.oguzhanozer.musfer.userservice.exception.UserNotFoundException;
import me.oguzhanozer.musfer.userservice.repository.UserRepository;

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
