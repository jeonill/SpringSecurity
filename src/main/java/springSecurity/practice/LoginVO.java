package springSecurity.practice;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "userinfo")
@Entity
public class LoginVO implements UserDetails {
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private String loginId;
    private String loginPassword;
    @Builder
    public LoginVO(Long id, String loginId, String loginPassword) {
        this.id = id;
        this.loginId = loginId;
        this.loginPassword = loginPassword;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.loginPassword;
    }

    @Override
    public String getUsername() {
        return this.loginId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
