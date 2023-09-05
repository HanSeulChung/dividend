package com.dayone.service;


import com.dayone.exception.impl.AlreadyExistUserException;
import com.dayone.exception.impl.NoUserIdException;
import com.dayone.exception.impl.UnmatchPasswordException;
import com.dayone.exception.impl.UserNotFoundException;
import com.dayone.model.Auth;
import com.dayone.persist.entity.MemberEntity;
import com.dayone.persist.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    public MemberEntity register(Auth.SignUp member) {
        boolean exits = this.memberRepository.existsByUsername(member.getUsername()) ;
        if (exits) {
            throw new AlreadyExistUserException();
        }

        member.setPassword(this.passwordEncoder.encode(member.getPassword()));
        var result = this.memberRepository.save(member.toEntity());
        return result;
    }

    public MemberEntity authenticate(Auth.SignIn member) {

        var user = this.memberRepository.findByUsername(member.getUsername())
                .orElseThrow(() -> new NoUserIdException());

        if (!this.passwordEncoder.matches(member.getPassword(), user.getPassword())) {
            throw new UnmatchPasswordException();
        }

        return user;
    }
}
