package com.alura.hotel.controller;

import com.alura.hotel.model.reserva.DatosRegistroReserva;
import com.alura.hotel.model.reserva.DatosRespuestaReserva;
import com.alura.hotel.model.role.Role;
import com.alura.hotel.model.service.ReservaService;
import com.alura.hotel.model.usuario.Usuario;
import org.apache.tomcat.util.http.parser.Authorization;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ReservaControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<Usuario> respuestaUsuarioJacksonTester;
    @Autowired
    private JacksonTester<DatosRegistroReserva> datosRegistroReservaJacksonTester;
    @Autowired
    private JacksonTester<DatosRespuestaReserva> detalleConsultaJacksonTester;
    @MockBean
    private ReservaService reservaService;

    @Test
    @DisplayName("Deberia retornar un estado 400 cuando los datos ingresados sean invalidos")
    @WithMockUser
    void reservarEscenario1() throws Exception{
        var response = mvc.perform(post("/reserva")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("deberia retornar estado http 200 cuando los datos ingresados son validos")
    @WithMockUser
    void reservarEscenario2() throws Exception{
        var token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJob3RlbCIsInN1YiI6ImRhbmllLm9ydEBtYWlsLmNvbSIsImlkIjoxLCJyb2xlIjoiQURNSU4iLCJleHAiOjE2OTUwNzI1OTF9.mmEe0IVSIvhgsy005m6orKmLuwnCp_-u8AQBlAmqhXo";
        var fechaReserva = LocalDateTime.now();
        var entrada = LocalDateTime.now().plusDays(1);
        var salida = LocalDateTime.now().plusDays(3);
        String codigo = String.valueOf(UUID.randomUUID());
        BigDecimal total = BigDecimal.valueOf(46);
        var datos = new DatosRespuestaReserva(null, codigo, fechaReserva, entrada, salida, total, 1l,
                1l, 2l);

        when(reservaService.registrarReserva(any(), any())).thenReturn(datos);

        var response = mvc.perform(post("/reserva")
                .contentType(APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .content(datosRegistroReservaJacksonTester.write(new DatosRegistroReserva(entrada, salida, 1l,
                        2l)).getJson())
        ).andReturn().getResponse();

        response.getHeader("Authorization");

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = detalleConsultaJacksonTester.write(datos).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}