package sellcars.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@ComponentScan("sellcars")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private AuthenticationProvider authProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login", "/show_register").anonymous()
                .antMatchers(HttpMethod.POST, "/register").anonymous()
                .antMatchers("/picture", "/css/**").permitAll()
                .antMatchers(HttpMethod.POST, "/json").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login/process")
                .usernameParameter("login")
                .failureUrl("/login?error=true")
                //.defaultSuccessUrl("/")//авторизованный пользователь редиректится сюда
                //.and().exceptionHandling().accessDeniedPage("/") //авторизованный пользователь редиректится сюда при попытке попасть на страницу, которая служит только для неавторизованных
                .and().logout();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Autowired
    public void setAuthProvider(AuthenticationProvider authProvider) {
        this.authProvider = authProvider;
    }


}

