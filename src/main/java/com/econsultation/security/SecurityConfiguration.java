package com.econsultation.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.econsultation.handler.CustomLogoutSuccessHandler;
import com.econsultation.jpa.UserRepository;
import com.econsultation.model.User;

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

    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> {
            User user = userRepository.findByUserName(username);
            if (user != null) {
                return new org.springframework.security.core.userdetails.User(user.getUserName(), new BCryptPasswordEncoder().encode(user.getPassword()),
                        true, true, true, true, AuthorityUtils.createAuthorityList(user.getUserRole().getRoleName()));
            } else {
                throw new UsernameNotFoundException("Could not find the user '" + username + "'");
            }
        });
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().fullyAuthenticated().and().httpBasic().and().csrf().disable();
    }

    
//    @Override
//	protected void configureAPI(HttpSecurity http) throws Exception {
//		http.httpBasic().and().authorizeRequests()
//		.antMatchers("/webjars/**").permitAll()
//        .antMatchers("/js/**").permitAll()
//        .antMatchers("/webjars/**").permitAll()
//        .antMatchers(HttpMethod.POST, "/users").permitAll()
//        .antMatchers("/*todo**").hasAnyRole("Patient","Doctor")
//        .antMatchers("/*users/**").hasAnyRole("Patient","Doctor","Admin")
//        .antMatchers("/**").authenticated()
////        .and().formLogin().loginPage("/login").permitAll()
//        .and().logout().logoutUrl("/appLogout").logoutSuccessUrl("/welcome")
////        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
////        .invalidateHttpSession(true)
////        .deleteCookies("JSESSIONID")
//        .and().csrf().disable()
////        .formLogin().disable()
////		.headers().frameOptions().disable()
//		;
//	}

	
    //@Override
    protected void configureWeb(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers("/welcome").permitAll()
//                .antMatchers("/login**").permitAll()
                
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/add-user**").permitAll()
                .antMatchers("/*todo**").hasRole("Admin")
                .antMatchers("/*user**").hasAnyRole("Patient","Doctor")
                .antMatchers("/**").authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/signin") //this is any name. Same should be defined in form: action in the JSP
                .loginPage("/login").permitAll()
                .usernameParameter("txtUsername") //this is any name of username field in custom login JSP.
                .passwordParameter("txtPassword") //this is any name of password field in custom login JSP.
                //.defaultSuccessUrl("/welcome") //upon successful login authentication, the page to redirect to
                .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler()) //define custom logout handler. It is called if logout is successful
                .logoutRequestMatcher(new AntPathRequestMatcher("/appLogout")).logoutSuccessUrl("/login")
                .and()
                .headers().frameOptions().disable();
                //.and()
                //.rememberMe().tokenValiditySeconds(2592000).key("mySecret!").rememberMeParameter("checkRememberMe")
                ;
    }
    
    /**
     *This custom logout handler will log on console (or add audit entry in db) if logout was successful.
     * @return
     */
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());//NoOpPasswordEncoder.getInstance());//
        daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);

        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}