package com.telegram.messaging.configuration;

import com.telegram.messaging.entity.User;
import com.telegram.messaging.pojo.UserPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Configuration
public class IntegrationTestConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        User user = User.builder()
                .username("login")
                .password("password")
                .build();

        UserPrincipal userPrincipal = new UserPrincipal(user);

        UserDetailsService userDetailsService = mock(UserDetailsService.class);
        when(userDetailsService.loadUserByUsername(anyString())).thenReturn(userPrincipal);

        return userDetailsService;
    }

}
