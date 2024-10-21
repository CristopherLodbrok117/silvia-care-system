package com.silvia_care.caregivers;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CaregiverDetailService implements UserDetailsService {



    CaregiverRepository caregiverRepository;

    public CaregiverDetailService(CaregiverRepository caregiverRepository) {
         this.caregiverRepository = caregiverRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Caregiver caregiver = caregiverRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + "no existe"));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        caregiver.getRoles()
                .forEach(role ->
                    authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

        caregiver.getRoles()
                .stream()
                .flatMap(role -> role.getPermissions().stream())
                .forEach(permission ->
                    authorityList.add(new SimpleGrantedAuthority(permission.getName())));


        return new User(caregiver.getUsername(),
                caregiver.getPassword(),
                caregiver.isEnabled(),
                caregiver.isAccountNoExpired(),
                caregiver.isCredentialNoExpired(),
                caregiver.isAccountNoLocked(),
                authorityList
                );
    }



}
