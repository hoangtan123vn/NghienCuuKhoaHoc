package com.onion.config;

import com.onion.config.jwt.CustomJwtAuthenticationFilter;
import com.onion.config.jwt.UserDetailsServiceImpl;
import com.onion.config.jwt.JwtAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Collections;

@EnableWebSecurity
@Configuration
@CrossOrigin
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;


    @Autowired
    UserDetailsServiceImpl userDetailsService;


    @Autowired
    CustomJwtAuthenticationFilter customJwtAuthenticationFilter;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
        //configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT"));
        configuration.setAllowCredentials(true);
        //configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
        //the below three lines will add the relevant CORS response headers
      //  configuration.addAllowedOrigin("*");
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    /*    http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/auth/login","/api/auth/users").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
               // .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();*/

      http.cors().configurationSource(corsConfigurationSource()).and()
              .csrf().disable()
		.authorizeRequests().antMatchers("/api/auth/admin","/api/auth/userinfo","/api/localsearch/add/location","/api/auth/refresh").hasRole("ADMIN")
		.antMatchers("/api/auth/user","/api/auth/userinfo","/api/auth/refresh").hasAnyRole("USER","ADMIN")
		.antMatchers("/api/auth/login","/api/auth/register").permitAll().anyRequest().authenticated()

		//if any exception occurs call this
		.and().exceptionHandling()
        .authenticationEntryPoint(unauthorizedHandler).and().
		// make sure we use stateless session; session won't be used to
		// store user's state.
		sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

// 		Add a filter to validate the tokens with every request
		http.addFilterBefore(customJwtAuthenticationFilter,
		UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}

