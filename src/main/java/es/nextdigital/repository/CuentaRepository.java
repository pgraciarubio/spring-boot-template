package es.nextdigital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.nextdigital.entity.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {


}
