package com.telegram.messaging.configuration;

import com.telegram.messaging.config.SecurityConfiguration;
import com.telegram.messaging.entity.User;
import com.telegram.messaging.pojo.UserPrincipal;
import com.telegram.messaging.service.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ContextConfiguration
public class WebSecurityTestConfiguration {

    @Bean
    public SecurityConfiguration securityConfiguration(UserDetailsService userDetailsService) {
        return new SecurityConfiguration(userDetailsService);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetailServiceImpl service = mock(UserDetailServiceImpl.class);

        UserPrincipal stubUserPrincipal = stubUserPrincipal();
        when(service.loadUserByUsername("user"))
                .thenReturn(stubUserPrincipal);
        return service;
    }

    private UserPrincipal stubUserPrincipal() {
        User user = new User();
        user.setUsername("login");
        user.setPassword("password");

        return new UserPrincipal(user);
    }

}
