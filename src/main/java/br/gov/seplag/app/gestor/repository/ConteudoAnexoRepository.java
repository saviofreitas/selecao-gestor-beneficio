package br.gov.seplag.app.gestor.repository;
import br.gov.seplag.app.gestor.domain.ConteudoAnexo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ConteudoAnexo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConteudoAnexoRepository extends JpaRepository<ConteudoAnexo, Long> {

}
