package br.gov.seplag.app.gestor.repository;
import br.gov.seplag.app.gestor.domain.CategoriaAnexo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CategoriaAnexo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategoriaAnexoRepository extends JpaRepository<CategoriaAnexo, Long> {

}
