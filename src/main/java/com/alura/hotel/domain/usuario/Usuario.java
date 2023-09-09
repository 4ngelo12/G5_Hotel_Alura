package com.alura.hotel.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 45, nullable = false)
    private String nombre;
    @Column(length = 45, nullable = false)
    private String apellido;
    @Column(length = 70, nullable = false)
    private String email;
    @Column(length = 15, nullable = false)
    private String documento;
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Role role;
    @Column(length = 60, nullable = false, unique = true)
    private String username;
    @Column(length = 60, nullable = false)
    private String password;

    public Usuario(DatosUsuario datosRegistroUsuario) {
        this.nombre = datosRegistroUsuario.nombre();
        this.apellido = datosRegistroUsuario.apellido();
        this.email = datosRegistroUsuario.email();
        this.documento = datosRegistroUsuario.documento();
        this.role = datosRegistroUsuario.role();
        this.username = datosRegistroUsuario.username();
        this.password = datosRegistroUsuario.password();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
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
