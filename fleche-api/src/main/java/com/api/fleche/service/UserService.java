package com.api.fleche.service;

import com.api.fleche.dao.UserDao;
import com.api.fleche.model.User;
import com.api.fleche.model.dtos.*;
import com.api.fleche.model.exception.AgeMinNotDifinedException;
import com.api.fleche.model.exception.EmailAlreadyExistsException;
import com.api.fleche.model.exception.PhoneAlreadyExistsException;
import com.api.fleche.model.exception.UserNotFounException;
import com.api.fleche.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDao userDao;
    private static final Integer AGE = 18;

    @Transactional
    public User createAccount(UserDto userDto) {
        var user = new User();
        String encryptedPassword = new BCryptPasswordEncoder().encode(userDto.getPassword());
        userDto.setPassword(encryptedPassword);
        BeanUtils.copyProperties(userDto, user);
        validateRegister(user);
        return userRepository.save(user);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsByTelefone(String phone) {
        return userRepository.existsByPhone(phone);
    }

    public boolean verifyAge(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            return false;
        }
        LocalDate today = LocalDate.now();
        Period age = Period.between(dateOfBirth, today);
        return age.getYears() >= AGE;
    }

    public Optional<User> findById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user;
    }

    public UserDataDto findDataUser(Long id) {
        return userDao.findDataUser(id);
    }

    public LoginDto login(String emailOuNumero) {
        return userDao.findDataLogin(emailOuNumero);
    }

    @Transactional
    @Modifying
    public void updateDataUser(UserUpdateDto updateDto, Long id) {
        var userData = findDataUser(id);
        if (userData == null) {
            throw new UserNotFounException("User not found by id: " + id);
        }
        if (updateDto.getPhone() != null) {
            if (updateDto.getPhone().equals(userData.getPhone()) && userData.getId() != id) {
                throw new PhoneAlreadyExistsException("Phone already exisits");
            }
        }
        if (updateDto.getEmail() != null) {
            if (updateDto.getEmail().equals(userData.getEmail()) && userData.getId() != id) {
                throw new EmailAlreadyExistsException("E-mail already exists");
            }
        }
        Optional<User> user = findById(id);
        user.get().setName(updateDto.getName() != null ? updateDto.getName() : user.get().getName());
        user.get().setPhone(updateDto.getPhone() != null ? updateDto.getPhone() : user.get().getPhone());
        user.get().setEmail(updateDto.getEmail() != null ? updateDto.getEmail() : user.get().getEmail());
        user.get().setDateOfBirth(user.get().getDateOfBirth());
        userRepository.save(user.get());
    }

    public User findUserNotProfile(String phone) {
        return userRepository.findUserNotProfile(phone).get();
    }

    public ProfileUserDto profileUser(Long userId) {
        var user = findById(userId);
        if (user.isEmpty()){
            throw new UserNotFounException("User not found by id: " + userId);
        }
        return userDao.profileUser(userId);
    }

    public UserDetails findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    public void validateRegister(User user) {
        if (existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException("E-mail already exists");
        }
        LocalDate nascimento = LocalDate.parse(user.getDateOfBirth().toString());
        if (!verifyAge(nascimento)) {
            throw new AgeMinNotDifinedException("Must be over 18 years old");
        }
        if (findByPhone(user.getPhone()) != null) {
            throw new PhoneAlreadyExistsException("Phone already exists");
        }
    }

}
