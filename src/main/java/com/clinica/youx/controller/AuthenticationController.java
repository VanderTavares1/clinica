package com.clinica.youx.controller;

import com.clinica.youx.dto.AuthenticationDTO;
import com.clinica.youx.dto.RegisterDTO;
import com.clinica.youx.dto.Role;
import com.clinica.youx.entity.Enfermeiro;
import com.clinica.youx.entity.Medico;
import com.clinica.youx.entity.Usuario;
import com.clinica.youx.repository.EnfermeiraRepository;
import com.clinica.youx.repository.MedicoRepository;
import com.clinica.youx.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Controller
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EnfermeiraRepository enfermeiraRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO data) {
        Optional<Usuario> usuarioExistente = this.usuarioRepository.findByNome(data.nome());

        if (usuarioExistente.isPresent()) {
            return ResponseEntity.badRequest().body("Usuário já existe");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        Usuario newUsuario = new Usuario(data.nome(), encryptedPassword, data.cpf(), data.role());

        if (data.role() == Role.MEDICO) {
            Medico medico = new Medico();
            medico.setNome(data.nome());
            medico.setCpf(data.cpf());
            medico.setRole(String.valueOf(data.role()));
            medico.setSenha(encryptedPassword);
            this.medicoRepository.save(medico);
            newUsuario.setMedico(medico);
        }

        if (data.role() == Role.ENFERMEIRO) {
            Enfermeiro enfermeiro = new Enfermeiro();
            enfermeiro.setNome(data.nome());
            enfermeiro.setCpf(data.cpf());
            enfermeiro.setRole(String.valueOf(data.role()));
            enfermeiro.setSenha(encryptedPassword);
            this.enfermeiraRepository.save(enfermeiro);
            newUsuario.setEnfermeiro(enfermeiro);
        }

        this.usuarioRepository.save(newUsuario);
        return ResponseEntity.ok("Usuário registrado com sucesso!");
    }

}
