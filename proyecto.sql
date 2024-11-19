CREATE DATABASE SistemaCitasMedicas;
USE SistemaCitasMedicas;

CREATE TABLE Pacientes (
idPaciente INT PRIMARY KEY AUTO_INCREMENT,
nombre VARCHAR(100),
fecha_nacimiento DATE,
alergias VARCHAR(255),
condiciones_clinicas VARCHAR(255),
prioridad_atencion VARCHAR(50) DEFAULT 'Normal',
eps VARCHAR(100)
);

CREATE TABLE Medicos (
idMedico INT PRIMARY KEY AUTO_INCREMENT,
nombre VARCHAR(100),
especialidad VARCHAR(100),
horario VARCHAR(100)
);

CREATE TABLE Citas (
idCita INT PRIMARY KEY AUTO_INCREMENT,
fecha DATE,
lugar VARCHAR(100),
idPaciente INT,
idMedico INT,
FOREIGN KEY (idPaciente) REFERENCES Pacientes(idPaciente),
FOREIGN KEY (idMedico) REFERENCES Medicos(idMedico)
);

INSERT INTO Pacientes (nombre, alergias, condiciones_clinicas, eps, fecha_nacimiento)
VALUES 
('Maria Lopez', 'Penicilina', 'Diabetes', 'EPS Salud', '1950-06-15'),
('Pedro González', 'Polen', 'Hipertensión', 'EPS Vida', '1955-11-10'),
('Luisa Martinez', 'Ninguna', 'Cáncer', 'EPS Familia', '1948-02-20');

INSERT INTO Medicos (nombre, especialidad, horario)
VALUES 
('Dr. Laura Suarez', 'Oncología', 'Lunes a Viernes 9:00-15:00'),
('Dr. Andres Rojas', 'Medicina General', 'Martes y Jueves 10:00-18:00'),
('Dra. Sofia Muñoz', 'Geriatría', 'Lunes a Miércoles 8:00-12:00');

INSERT INTO Citas (fecha, lugar, idPaciente, idMedico)
VALUES 
('2024-11-01', 'Consultorio 4', 1, 2),
('2024-11-05', 'Consultorio 5', 2, 3),
('2024-11-10', 'Consultorio 6', 3, 1);

DELIMITER //

CREATE PROCEDURE CambiarPrioridadAtencion()
BEGIN
    DECLARE finCursor INT DEFAULT 0;
    DECLARE id INT;
    DECLARE condicion VARCHAR(255);

    DECLARE pacientesCursor CURSOR FOR
    SELECT idPaciente, condiciones_clinicas
    FROM Pacientes
    WHERE TIMESTAMPDIFF(YEAR, fecha_nacimiento, CURDATE()) > 65
      AND (condiciones_clinicas LIKE '%Hipertensión%' OR
           condiciones_clinicas LIKE '%Diabetes%' OR
           condiciones_clinicas LIKE '%Cáncer%');

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET finCursor = 1;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        CLOSE pacientesCursor;
    END;

    START TRANSACTION;

    OPEN pacientesCursor;

    pacientesLoop: LOOP
        FETCH pacientesCursor INTO id, condicion;
        
        IF finCursor = 1 THEN
            LEAVE pacientesLoop;
        END IF;

        UPDATE Pacientes
        SET prioridad_atencion = 'Alta'
        WHERE idPaciente = id;
    END LOOP pacientesLoop;

    CLOSE pacientesCursor;

    COMMIT;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE ConsultarPacientesPorEdadYEPS(IN edad_min INT, IN edad_max INT, IN eps_nombre VARCHAR(100))
BEGIN
    SELECT idPaciente, nombre, fecha_nacimiento, alergias, condiciones_clinicas, prioridad_atencion, eps
    FROM Pacientes
    WHERE TIMESTAMPDIFF(YEAR, fecha_nacimiento, CURDATE()) BETWEEN edad_min AND edad_max
      AND eps = eps_nombre;
END //

DELIMITER ;


CREATE VIEW PacientesMayores65 AS
SELECT idPaciente, nombre, alergias, condiciones_clinicas, eps, prioridad_atencion
FROM Pacientes
WHERE TIMESTAMPDIFF(YEAR, fecha_nacimiento, CURDATE()) > 65
  AND eps = 'EPS Salud';

CALL CambiarPrioridadAtencion();
SELECT * FROM PacientesMayores65;

CALL ConsultarPacientesPorEdadYEPS(60, 80, 'EPS Salud');