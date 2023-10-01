package springSecurity.practice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements UserDetailsService, LoginService {
    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void join(String joinId, String joinPassword) {

        LoginVO newMember = new LoginVO().builder()
                .loginId(joinId)
                .loginPassword(passwordEncoder.encode(joinPassword))
                .build();

        loginRepository.save(newMember);
        log.info("save");
    }

    @Override
    public int LoginCheck(String loginId, String loginPassword) {

        try {
            UserDetails userDetails = loadUserByUsername(loginId);
            String password = userDetails.getPassword();
            passwordEncoder.matches(loginPassword, password);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginVO result = loginRepository.findByLoginId(username);

        if (result == null) {
            throw new UsernameNotFoundException("User ID Not Find:" + username);
        } else {
            return result;
        }
    }
}
