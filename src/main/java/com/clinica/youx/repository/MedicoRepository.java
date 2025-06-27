package com.clinica.youx.repository;

import com.clinica.youx.entity.Medico;
import com.clinica.youx.entity.Paciente;
import com.clinica.youx.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Optional<Medico> findByUsuario(Usuario usuario);
}
