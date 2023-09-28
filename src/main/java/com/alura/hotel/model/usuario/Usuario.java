package com.alura.hotel.model.usuario;

import com.alura.hotel.model.role.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
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
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    @Enumerated(EnumType.STRING)
    @Column(length = 15, nullable = false)
    private TipoDocumento tipoDocumento;
    @Column(length = 15, nullable = false, unique = true)
    private String documento;
    @Column(length = 15, nullable = false)
    private String telefono;
    @Column(length = 5, nullable = false, columnDefinition = "tinyint")
    private Boolean activo;
    @Column(length = 60, nullable = false)
    private String password;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    public Usuario(DatosRegistroUsuario datosRegistroUsuario, Role rol, BCryptPasswordEncoder passwordEncoder) {
        this.nombre = datosRegistroUsuario.nombre();
        this.apellido = datosRegistroUsuario.apellido();
        this.email = datosRegistroUsuario.email();
        this.fechaNacimiento = datosRegistroUsuario.fechaNacimiento();
        this.tipoDocumento = datosRegistroUsuario.tipoDocumento();
        this.telefono = datosRegistroUsuario.telefono();
        this.documento = datosRegistroUsuario.documento();
        this.role = rol;
        this.activo = true;
        this.password = passwordEncoder.encode(datosRegistroUsuario.password());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(getRole().getNombre()));
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
