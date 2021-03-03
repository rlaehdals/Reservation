package Rservation.vacation.project.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Setter
public class UserInfo implements UserDetails {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userinfo_id")
    private Long id;


    private String name;

    @Column(name="email", unique = true)
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="auth")
    private String auth;

    @Column(name="phoneNumber")
    private String phoneNumber;

    @OneToMany(mappedBy = "userInfo")
    private List<Reservation> reservations = new ArrayList<>();

    @Embedded
    private Address address;

    @Builder
    public UserInfo(String email, String password, String auth,String name, String phoneNumber, Address address){
        this.email=email;
        this.password=password;
        this.auth=auth;
        this.address =address;
        this.phoneNumber=phoneNumber;
        this.name=name;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for(String role : auth.split(",")){
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
    }

    public UserInfo(String name) {
        this.name = name;
    }

    public void changeAddress(Address address){
        this.address=address;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
