package com.example.sistemacitasmedicas.service;

import com.example.sistemacitasmedicas.model.Medico;
import com.example.sistemacitasmedicas.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public List<Medico> findAll() {
        return medicoRepository.findAll();
    }

    public Optional<Medico> findById(Integer id) {
        return medicoRepository.findById(id);
    }

    public Medico save(Medico medico) {
        return medicoRepository.save(medico);
    }

    public Medico update(Integer id, Medico medico) {
        medico.setIdMedico(id);
        return medicoRepository.save(medico);
    }

    public void deleteById(Integer id) {
        medicoRepository.deleteById(id);
    }
}
