package com.alura.hotel.domain.usuario;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 45, nullable = false)
    private String nombre;
    @Column(length = 45, nullable = false)
    private String apellido;
    @Column(length = 70, nullable = false)
    private String email;
    @Column(length = 15, nullable = false, unique = true)
    private String documento;
    @Column(length = 15, nullable = false)
    private String telefono;
    @Column(length = 12, nullable = false)
    private String role;
    @Column(length = 5, nullable = false, columnDefinition = "tinyint")
    private Boolean activo;
    @Column(length = 60, nullable = false)
    private String password;


    public Usuario(DatosUsuario datosRegistroUsuario, BCryptPasswordEncoder passwordEncoder) {
        this.nombre = datosRegistroUsuario.nombre();
        this.apellido = datosRegistroUsuario.apellido();
        this.email = datosRegistroUsuario.email();
        this.telefono = datosRegistroUsuario.telefono();
        this.documento = datosRegistroUsuario.documento();
        this.role = datosRegistroUsuario.role();
        this.activo = true;
        this.password = passwordEncoder.encode(datosRegistroUsuario.password());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(getRole()));
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
