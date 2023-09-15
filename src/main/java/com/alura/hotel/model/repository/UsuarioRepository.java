package com.alura.hotel.model.repository;

import com.alura.hotel.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public UserDetails findByEmail(String email);
    @Query("""
            select u from Usuario u
            where u.email=:email
            """)
    public Usuario getUserData(String email);
}
