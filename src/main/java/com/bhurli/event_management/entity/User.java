package com.bhurli.event_management.entity;

import com.bhurli.event_management.enums.AccountStatus;
import com.bhurli.event_management.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus accountStatus;

    @Column(length = 500)
    private String profileImage;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    //user shoud know the "how many events its create
    @OneToMany(mappedBy = "organizer")
    private List <Event> organizedEvents;
    //booking relation
    @OneToMany(mappedBy = "user")
    private List<Booking> bookings;

    //feedback relation
    @OneToMany(mappedBy = "user")
    private List<Feedback> feedbacks;


    //preparing userdetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //return role of the user

//        return List.of(new SimpleGrantedAuthority(role.name()));
        return List.of(
                new SimpleGrantedAuthority("ROLE_" + role.name())
        );
    }

    @Override //login from email
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

    @Override //inactive udser could not login
    public boolean isEnabled() {
        return accountStatus == AccountStatus.ACTIVE;
    }


}
