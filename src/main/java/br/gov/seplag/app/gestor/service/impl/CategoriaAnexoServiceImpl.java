package br.gov.seplag.app.gestor.service.impl;

import br.gov.seplag.app.gestor.service.CategoriaAnexoService;
import br.gov.seplag.app.gestor.domain.CategoriaAnexo;
import br.gov.seplag.app.gestor.repository.CategoriaAnexoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link CategoriaAnexo}.
 */
@Service
@Transactional
public class CategoriaAnexoServiceImpl implements CategoriaAnexoService {

    private final Logger log = LoggerFactory.getLogger(CategoriaAnexoServiceImpl.class);

    private final CategoriaAnexoRepository categoriaAnexoRepository;

    public CategoriaAnexoServiceImpl(CategoriaAnexoRepository categoriaAnexoRepository) {
        this.categoriaAnexoRepository = categoriaAnexoRepository;
    }

    /**
     * Save a categoriaAnexo.
     *
     * @param categoriaAnexo the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CategoriaAnexo save(CategoriaAnexo categoriaAnexo) {
        log.debug("Request to save CategoriaAnexo : {}", categoriaAnexo);
        return categoriaAnexoRepository.save(categoriaAnexo);
    }

    /**
     * Get all the categoriaAnexos.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<CategoriaAnexo> findAll() {
        log.debug("Request to get all CategoriaAnexos");
        return categoriaAnexoRepository.findAll();
    }


    /**
     * Get one categoriaAnexo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CategoriaAnexo> findOne(Long id) {
        log.debug("Request to get CategoriaAnexo : {}", id);
        return categoriaAnexoRepository.findById(id);
    }

    /**
     * Delete the categoriaAnexo by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CategoriaAnexo : {}", id);
        categoriaAnexoRepository.deleteById(id);
    }
}
