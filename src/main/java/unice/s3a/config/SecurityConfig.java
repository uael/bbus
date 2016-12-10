package unice.s3a.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import unice.s3a.domain.AccountService;

/**
 * The type Security config.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired private AccountService accountService;

    @Bean(name = "authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(
            "/",
            "/favicon.ico",
            "/resources/**",
            "/signup",
            "/bus/*",
            "/box/*",
            "/boxes",
            "/message/*"
        ).permitAll().anyRequest().authenticated().and().formLogin().loginPage("/signin").permitAll().failureUrl(
            "/signin?error=1").loginProcessingUrl("/authenticate").and().logout().logoutUrl("/logout").permitAll()
            .logoutSuccessUrl(
            "/signin?logout").and().rememberMe().rememberMeServices(rememberMeServices()).key("remember-me-key");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.eraseCredentials(true).userDetailsService(accountService).passwordEncoder(passwordEncoder());
    }

    /**
     * Password encoder password encoder.
     * @return the password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }

    /**
     * Remember me services token based remember me services.
     * @return the token based remember me services
     */
    @Bean
    public TokenBasedRememberMeServices rememberMeServices() {
        return new TokenBasedRememberMeServices("remember-me-key", accountService);
    }
}