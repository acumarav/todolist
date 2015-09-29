package org.alex.todoweb.security;


import com.google.common.collect.ImmutableMap;
import org.alex.todoweb.model.User;
import org.alex.todoweb.repository.UserRepository;
import org.alex.todoweb.service.DataInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserRepository userRepository;

    public static Map<String, String> USERS = ImmutableMap.of("alex", "doe", "tom", "smith", "dave", "brown");

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        final List<User> users = createUsers();

        for (User user : users) {
            auth.inMemoryAuthentication()
                    .withUser(user.getFirstName()).password("pwd").roles("USER");
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/rs/tasks").authenticated()
        .and().authorizeRequests().antMatchers(HttpMethod.PUT,"/rs/task").authenticated()
        .and().authorizeRequests().anyRequest().permitAll()
        .and().formLogin()
        .and().httpBasic();


        http.csrf().disable();
    }

    private List<User> createUsers() {
        List<User> users = new ArrayList<>();

        for (String userName : USERS.keySet()) {
            String lastName = USERS.get(userName);
            User user = buildUser(userName, lastName);
            users.add(user);
        }

        return userRepository.save(users);
    }

    private User buildUser(String firstName, String secondName) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(secondName);

        return user;
    }
}
