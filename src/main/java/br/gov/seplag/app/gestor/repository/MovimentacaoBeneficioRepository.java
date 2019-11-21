package br.gov.seplag.app.gestor.repository;
import br.gov.seplag.app.gestor.domain.MovimentacaoBeneficio;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MovimentacaoBeneficio entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MovimentacaoBeneficioRepository extends JpaRepository<MovimentacaoBeneficio, Long> {

}
