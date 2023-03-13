package com.example.jn_english.entity.details;

import com.example.jn_english.entity.admin.Admin;
import com.example.jn_english.entity.admin.AdminRepository;
import com.example.jn_english.exception.custom.AccountIdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByAccountId(username).orElseThrow(()-> AccountIdNotFoundException.EXCEPTION);
        return new Details(admin);
    }
}
