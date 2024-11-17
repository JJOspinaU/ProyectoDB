package com.example.sistemacitasmedicas.service;

import com.example.sistemacitasmedicas.model.Paciente;
import com.example.sistemacitasmedicas.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import java.util.List;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    public Paciente save(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public void deleteById(Integer id) {
        pacienteRepository.deleteById(id);
    }

    // Llama al procedimiento CambiarPrioridadAtencion
    public void cambiarPrioridadAtencion() {
        pacienteRepository.cambiarPrioridadAtencion();
    }

    // Llama al procedimiento ConsultarPacientesPorEdadYEPS con parámetros
    public List<Paciente> consultarPacientesPorEdadYEPS(int edadMin, int edadMax, String epsNombre) {
        return pacienteRepository.consultarPacientesPorEdadYEPS(edadMin, edadMax, epsNombre);
    }
    
    public Optional<Paciente> findById(Integer id) {
        return pacienteRepository.findById(id);
    }
}
