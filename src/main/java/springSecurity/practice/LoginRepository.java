package springSecurity.practice;

import org.springframework.data.jpa.repository.JpaRepository;


public interface LoginRepository extends JpaRepository<LoginVO, Long> {
    LoginVO findByLoginId(String loginId);

}
