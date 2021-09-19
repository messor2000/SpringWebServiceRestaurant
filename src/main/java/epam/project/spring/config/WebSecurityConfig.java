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

/**
 * @author Aleksandr Ovcharenko
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/", "/locale/*", "/info/*", "/user/*", "/client/*")
                        .permitAll()
                    .antMatchers("/admin/*")
                        .hasRole("ADMIN")
                    .antMatchers("/user/*", "/client/*")
                    .hasAnyRole("ADMIN", "USER")
                    .and()
                .exceptionHandling()
                    .accessDeniedPage("/")
                    .and()
                .formLogin()
                    .loginPage("/sign/up")
                    .loginProcessingUrl("/sign/in")
                    .failureUrl("/sign/up?error")
                    .usernameParameter("login")
                    .passwordParameter("password")
                        .permitAll()
                    .successForwardUrl("/")
                    .and()
                .logout()
                    .permitAll()
                    .logoutUrl("/sign/out")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true);

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
