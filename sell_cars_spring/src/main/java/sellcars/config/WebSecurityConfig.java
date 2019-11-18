package sellcars.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
        .usersByUsernameQuery("select login, password from users where login=?");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()//должна быть вначале, иначе не работают фильтры
                .authorizeRequests()
                .antMatchers("/register", "/login", "/json", "/picture").permitAll()//перечисление исключений, для которых не требуется авторизация
                .anyRequest().authenticated() //требование авторизации
                .and().formLogin()//.loginProcessingUrl("/login") //the URL on which the clients should post the login information
        .usernameParameter("login") //the username parameter in the queryString, default is 'username'
        .passwordParameter("password")
                //the password parameter in the queryString, default is 'password'
        ;
    }

}


