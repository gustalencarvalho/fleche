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

import static com.api.fleche.infra.security.SecurityFilter.getAuthenticationUserId;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDao userDao;
    private static final Integer AGE = 18;

    @Transactional
    public UserDto createAccount(UserDto userDto) {
        var user = new User();
        String encryptedPassword = new BCryptPasswordEncoder().encode(userDto.getPassword());
        userDto.setPassword(encryptedPassword);
        BeanUtils.copyProperties(userDto, user);
        validateRegister(user);
        var userSave = userRepository.save(user);
        return new UserDto(
                userSave.getName(),
                userSave.getEmail(),
                userSave.getDdd(),
                userSave.getPhone(),
                userSave.getRole(),
                userSave.getStatus().name());
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

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFounException("User not found by id: " + userId));
    }

    public UserDataDto findDataUser(Long id) {
        var userData = userDao.findDataUser(id);
        if (userData == null) {
            throw new UserNotFounException("User not found by id: " + id);
        }
        return userData;
    }

    public LoginDto login(String emailOuNumero) {
        return userDao.findDataLogin(emailOuNumero);
    }

    @Transactional
    @Modifying
    public void updateDataUser(UserUpdateDto updateDto) {
        var userData = findDataUser(getAuthenticationUserId());
        if (updateDto.getPhone() != null) {
            if (updateDto.getPhone().equals(userData.getPhone()) && userData.getId() != getAuthenticationUserId()) {
                throw new PhoneAlreadyExistsException("Phone already exisits");
            }
        }

        if (updateDto.getEmail() != null) {
            if (updateDto.getEmail().equals(userData.getEmail()) && userData.getId() != getAuthenticationUserId()) {
                throw new EmailAlreadyExistsException("E-mail already exists");
            }
        }

        User user = findById(getAuthenticationUserId());
        user.setName(updateDto.getName() != null ? updateDto.getName() : user.getName());
        user.setPhone(updateDto.getPhone() != null ? updateDto.getPhone() : user.getPhone());
        user.setEmail(updateDto.getEmail() != null ? updateDto.getEmail() : user.getEmail());
        user.setDateOfBirth(user.getDateOfBirth());
        userRepository.save(user);
    }

    public User findUserNotProfile(String phone) {
        return userRepository.findUserNotProfile(phone).get();
    }

    public ProfileUserDto profileUser(Long userId) {
        var user = findById(userId);
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
