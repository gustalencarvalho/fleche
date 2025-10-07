package com.api.fleche.service;

import com.api.fleche.model.User;
import com.api.fleche.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String telefone) throws UsernameNotFoundException {
        User user = userRepository.findUserNotProfile(telefone)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return user;
    }


}
