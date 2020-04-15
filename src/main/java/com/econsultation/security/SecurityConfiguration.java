package com.econsultation.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private UserPrincipalDetailsService userPrincipalDetailsService;
    
    public SecurityConfiguration(UserPrincipalDetailsService userPrincipalDetailsService) {
        this.userPrincipalDetailsService = userPrincipalDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    
//	@Autowired
//  public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
//          throws Exception {
//      auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
//		.withUser("user1").password("secret1").roles("USER")
//		.and().withUser("Saurabh").password("pass").roles("USER")
//		.and().withUser("admin1").password("secret1").roles("USER", "ADMIN");
//  }
    
	

	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers("/welcome").permitAll()
//                .antMatchers("/login**").permitAll()
                
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/*todo**").hasRole("Admin")
                .antMatchers("/*user**").hasAnyRole("Patient","Doctor")
                .antMatchers("/**").authenticated()
                
//                .antMatchers("/*login**").permitAll()
//                .antMatchers("/*welcome**").permitAll()
//                .antMatchers("/**").authenticated()
//                .antMatchers("/**").hasRole("Admin")
//                .antMatchers("/*user**").hasAnyRole("Patient","Admin")
//                .antMatchers("/*todo**").hasRole("Doctor")
                
                //.antMatchers("/api/public/test1").hasAuthority("ACCESS_TEST1")
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/management/**").hasAnyRole("ADMIN", "Doctor")
//                .antMatchers("/api/public/test1").hasAuthority("ACCESS_TEST1")
//                .antMatchers("/api/public/test2").hasAuthority("ACCESS_TEST2")
                .and()
                .formLogin()
                .loginProcessingUrl("/signin") //this is any name. Same should be defined in form: action in the JSP
                .loginPage("/login").permitAll()
                .usernameParameter("txtUsername") //this is any name of username field in custom login JSP.
                .passwordParameter("txtPassword") //this is any name of password field in custom login JSP.
                //.defaultSuccessUrl("/welcome") //upon successful login authentication, the page to redirect to
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
                //.and()
                //.rememberMe().tokenValiditySeconds(2592000).key("mySecret!").rememberMeParameter("checkRememberMe")
                ;
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());//(NoOpPasswordEncoder.getInstance());//
        daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);

        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}