package dev.collegues;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${jwt.cookie}")
    private String TOKEN_COOKIE;

    @Autowired
    JwtAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
        http.headers().frameOptions().disable();

        http.authorizeRequests().antMatchers(HttpMethod.POST,"/auth").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/**").hasAnyRole("USER","ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/**").hasRole("ADMIN");

        http.authorizeRequests().anyRequest().authenticated();

        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        http.logout()
                .logoutSuccessHandler((req,resp,auth)-> resp.setStatus(HttpStatus.OK.value()))
                .deleteCookies(TOKEN_COOKIE);
    }
}
