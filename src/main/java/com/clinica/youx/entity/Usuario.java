package com.clinica.youx.entity;

import com.clinica.youx.dto.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String senha;
    private Integer cpf;
    private Role role;

    public Usuario(String nome, String senha, Integer cpf, Role role){
        this.nome = nome;
        this.senha = senha;
        this.cpf = cpf;
        this.role = role;
    }

    @OneToOne
    private Medico medico;

    @OneToOne
    private Enfermeiro enfermeiro;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == Role.MEDICO) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_MEDICO"),
                    new SimpleGrantedAuthority("ROLE_ENFERMEIRO")
            );
        } else if (this.role == Role.ENFERMEIRO) {
            return List.of(new SimpleGrantedAuthority("ROLE_ENFERMEIRO"));
        } else if (this.role == Role.PACIENTE) {
            return List.of(new SimpleGrantedAuthority("ROLE_PACIENTE"));
        }

        return List.of();
    }



    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return nome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
