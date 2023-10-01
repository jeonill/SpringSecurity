package springSecurity.practice.config;
import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.web.SecurityFilterChain;
import springSecurity.practice.sercurity.LoginAuthenticatorProvider;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

    @Autowired
    LoginAuthenticatorProvider loginAuthenticatorProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().cors().disable()
                .authorizeHttpRequests(request -> request
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers("/join").permitAll()
                        .anyRequest().authenticated()
                ).
                formLogin(login -> login
                        .loginPage("/loginPage")
                        .loginProcessingUrl("/login")
                        .usernameParameter("loginId")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/succesLogin",true)
                        .permitAll()

                )
                .logout(Customizer.withDefaults());

                return httpSecurity.build();
    }
    //구현한 인증절차를 등록
//    @Bean
//    public void configure(AuthenticationManagerBuilder auth)throws Exception{
//      auth.authenticationProvider(loginAuthenticatorProvider);
//    }

}
