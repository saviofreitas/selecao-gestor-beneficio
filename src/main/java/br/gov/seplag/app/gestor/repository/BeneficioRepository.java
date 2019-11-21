package br.gov.seplag.app.gestor.repository;
import br.gov.seplag.app.gestor.domain.Beneficio;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Beneficio entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BeneficioRepository extends JpaRepository<Beneficio, Long> {

}
