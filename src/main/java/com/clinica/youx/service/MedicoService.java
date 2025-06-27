package com.clinica.youx.service;

import com.clinica.youx.entity.Medico;
import com.clinica.youx.entity.Paciente;
import com.clinica.youx.entity.Usuario;
import com.clinica.youx.repository.MedicoRepository;
import com.clinica.youx.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Medico adicionandoMedico(Medico addMedico) {
         medicoRepository.save(addMedico);
         return null;
    }

    public Usuario adicionandoUsuario(Usuario addPaciente) {
        usuarioRepository.save(addPaciente);
        return null;
    }
}
