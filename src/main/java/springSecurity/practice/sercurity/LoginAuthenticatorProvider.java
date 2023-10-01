package springSecurity.practice.sercurity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import springSecurity.practice.LoginServiceImpl;
import springSecurity.practice.LoginVO;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoginAuthenticatorProvider implements AuthenticationProvider {
    @Autowired
    private LoginServiceImpl loginService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = (String) authentication.getCredentials();

        LoginVO userDetails =(LoginVO) loginService.loadUserByUsername(name);

        if(userDetails ==null){
            throw new BadCredentialsException("사용할 수 없는 계정입니다.");
        }
        String dbPassword = userDetails.getPassword();
        //log.info("db={}, web={}, same?={}",dbPassword,password,passwordEncoder.matches(password, dbPassword));

        if(!passwordEncoder.matches(password, dbPassword)){
            throw new BadCredentialsException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
