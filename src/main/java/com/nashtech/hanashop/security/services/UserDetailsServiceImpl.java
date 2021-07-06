package com.nashtech.hanashop.security.services;

import com.nashtech.hanashop.data.dto.UserDTO;
import com.nashtech.hanashop.data.entity.UserEntity;
import com.nashtech.hanashop.data.mapper.UserMapper;
import com.nashtech.hanashop.repository.UserRepository;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        UserEntity entity = userRepository.findByUserName(username);
        if(entity==null){
            throw new UsernameNotFoundException("User Not Found with -> username or email : " + username);
        }
//                .orElseThrow(() ->
//                        new UsernameNotFoundException("User Not Found with -> username or email : " + username)
//                );
        UserDTO user = UserMapper.parseEntityToDTO(entity);

        return UserDetailsImpl.build(user);
    }
}
