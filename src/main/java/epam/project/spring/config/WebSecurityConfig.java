package epam.project.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author Aleksandr Ovcharenko
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//        auth.
//                inMemoryAuthentication()
//                .withUser("testuser").password("password").roles("USER")
//                .and()
//                .withUser("admin").password("4c8bef50528ba16268fb268490fef63f2acb1bda137e32777a7d7652b9862459a39fbb6593a18c18").roles("ADMIN");
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/", "/menu", "/user/*")
                        .permitAll()
                    .antMatchers("/admin/*")
                        .hasRole("ADMIN")
                    .antMatchers("/user/*")
                        .hasAnyRole("ADMIN", "USER")
                    .and()
                .exceptionHandling()
                    .accessDeniedPage("/")
                    .and()
//                .formLogin()
//                    .loginPage("/sign/in")
//                    .loginProcessingUrl("/sign/in")
//                    .failureUrl("/error")
//                    .usernameParameter("username")
//                    .passwordParameter("password")
//                        .permitAll()
//                    .successForwardUrl("/menu")
//                    .and()
                .formLogin()
                    .loginPage("/sign/up")
                    .loginProcessingUrl("/sign/in")
                    .failureUrl("/error")
                    .usernameParameter("username")
                    .passwordParameter("password")
                        .permitAll()
                    .successForwardUrl("/menu")
                    .and()
                .logout()
                    .permitAll()
                    .logoutUrl("/sign/out")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                .and()
                    .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("testuser").password("password").roles("USER")
//                .and()
//                .withUser("admin").password("password").roles("ADMIN");
//    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
