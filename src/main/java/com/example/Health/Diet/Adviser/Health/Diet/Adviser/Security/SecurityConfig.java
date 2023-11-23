package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Security;


import com.example.Health.Diet.Adviser.Health.Diet.Adviser.filters.CustomAuthenticationFilter;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.filters.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();
        http.cors();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers("/api/login/**").permitAll();
        http.authorizeRequests().antMatchers("/api/signup").permitAll();

        http.authorizeRequests().antMatchers("/api/chronicdiseases/add").permitAll(); // edit permissions
        http.authorizeRequests().antMatchers("/api/chronicdiseases/all").permitAll();
        http.authorizeRequests().antMatchers("/api/chronicdiseases/getByName/**").permitAll();
        http.authorizeRequests().antMatchers("/api/chronicdiseases/delete/**").permitAll();

        http.authorizeRequests().antMatchers("/api/meals/add-to-chronic-disease/**").permitAll();
        ///api/users
        http.authorizeRequests().antMatchers("/api/users/{userId}/update-chronic-diseases").permitAll();
        http.authorizeRequests().antMatchers("/api/meals").permitAll();
        http.authorizeRequests().antMatchers("/api/meals/by-disease/{diseaseId}").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
