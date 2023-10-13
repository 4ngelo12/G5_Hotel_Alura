package com.alura.hotel.model.usuario.validaciones;

import com.alura.hotel.model.repository.UsuarioRepository;
import com.alura.hotel.model.usuario.DatosRegistroUsuario;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioCreado implements ValidacionUsuario{
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public void validar(DatosRegistroUsuario datos) {
        var emailExiste = usuarioRepository.getUserData(datos.email());

        if (emailExiste != null) {
            throw new ValidationException("El correo ingresado ya esta registrado");
        }
    }
}
