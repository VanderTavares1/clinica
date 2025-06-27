package com.clinica.youx.controller;

import com.clinica.youx.entity.Medico;
import com.clinica.youx.entity.Paciente;
import com.clinica.youx.entity.Usuario;
import com.clinica.youx.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping("/add")
    private Medico adicionandoMedico(@RequestBody Medico addMedico){
        return medicoService.adicionandoMedico(addMedico);
    }

    @PostMapping("/addUsuario")
    private Usuario adicionandoUsuario(@RequestBody Usuario addPaciente){
        return medicoService.adicionandoUsuario(addPaciente);
    }
}
