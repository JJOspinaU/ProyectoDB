package com.example.sistemacitasmedicas.controller;

import com.example.sistemacitasmedicas.model.Paciente;
import com.example.sistemacitasmedicas.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public List<Paciente> getAllPacientes() {
        return pacienteService.findAll();
    }

    @PostMapping
    public Paciente createPaciente(@RequestBody Paciente paciente) {
        return pacienteService.save(paciente);
    }

    // Endpoint para Cambiar la Prioridad de Atención
    @PostMapping("/cambiar-prioridad")
    public ResponseEntity<Void> cambiarPrioridadAtencion() {
        pacienteService.cambiarPrioridadAtencion();
        return ResponseEntity.ok().build();
    }

    // Endpoint para Consultar Pacientes por Edad y EPS
    @GetMapping("/consultar-por-edad-eps")
    public ResponseEntity<List<Paciente>> consultarPacientesPorEdadYEPS(
            @RequestParam int edadMin,
            @RequestParam int edadMax,
            @RequestParam String epsNombre) {
        List<Paciente> pacientes = pacienteService.consultarPacientesPorEdadYEPS(edadMin, edadMax, epsNombre);
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable Integer id) {
    return pacienteService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}
}
