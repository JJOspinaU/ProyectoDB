package com.example.sistemacitasmedicas.repository;

import com.example.sistemacitasmedicas.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    // Invoca el procedimiento almacenado CambiarPrioridadAtencion
    @Procedure(procedureName = "CambiarPrioridadAtencion")
    void cambiarPrioridadAtencion();

    // Invoca el procedimiento almacenado ConsultarPacientesPorEdadYEPS con parámetros
    @Procedure(procedureName = "ConsultarPacientesPorEdadYEPS")
    List<Paciente> consultarPacientesPorEdadYEPS(@Param("edad_min") int edadMin,
                                                  @Param("edad_max") int edadMax,
                                                  @Param("eps_nombre") String epsNombre);
}
