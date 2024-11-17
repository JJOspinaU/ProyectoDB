package com.example.sistemacitasmedicas.service;

import com.example.sistemacitasmedicas.model.Cita;
import com.example.sistemacitasmedicas.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    public List<Cita> findAll() {
        return citaRepository.findAll();
    }

    public Optional<Cita> findById(Integer id) {
        return citaRepository.findById(id);
    }

    public Cita save(Cita cita) {
        return citaRepository.save(cita);
    }

    public Cita update(Integer id, Cita cita) {
        cita.setIdCita(id);
        return citaRepository.save(cita);
    }

    public void deleteById(Integer id) {
        citaRepository.deleteById(id);
    }
}
