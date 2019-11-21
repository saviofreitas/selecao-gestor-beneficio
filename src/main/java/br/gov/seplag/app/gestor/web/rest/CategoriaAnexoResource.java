package br.gov.seplag.app.gestor.web.rest;

import br.gov.seplag.app.gestor.domain.CategoriaAnexo;
import br.gov.seplag.app.gestor.service.CategoriaAnexoService;
import br.gov.seplag.app.gestor.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link br.gov.seplag.app.gestor.domain.CategoriaAnexo}.
 */
@RestController
@RequestMapping("/api")
public class CategoriaAnexoResource {

    private final Logger log = LoggerFactory.getLogger(CategoriaAnexoResource.class);

    private static final String ENTITY_NAME = "categoriaAnexo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CategoriaAnexoService categoriaAnexoService;

    public CategoriaAnexoResource(CategoriaAnexoService categoriaAnexoService) {
        this.categoriaAnexoService = categoriaAnexoService;
    }

    /**
     * {@code POST  /categoria-anexos} : Create a new categoriaAnexo.
     *
     * @param categoriaAnexo the categoriaAnexo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new categoriaAnexo, or with status {@code 400 (Bad Request)} if the categoriaAnexo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/categoria-anexos")
    public ResponseEntity<CategoriaAnexo> createCategoriaAnexo(@Valid @RequestBody CategoriaAnexo categoriaAnexo) throws URISyntaxException {
        log.debug("REST request to save CategoriaAnexo : {}", categoriaAnexo);
        if (categoriaAnexo.getId() != null) {
            throw new BadRequestAlertException("A new categoriaAnexo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CategoriaAnexo result = categoriaAnexoService.save(categoriaAnexo);
        return ResponseEntity.created(new URI("/api/categoria-anexos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /categoria-anexos} : Updates an existing categoriaAnexo.
     *
     * @param categoriaAnexo the categoriaAnexo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categoriaAnexo,
     * or with status {@code 400 (Bad Request)} if the categoriaAnexo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the categoriaAnexo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/categoria-anexos")
    public ResponseEntity<CategoriaAnexo> updateCategoriaAnexo(@Valid @RequestBody CategoriaAnexo categoriaAnexo) throws URISyntaxException {
        log.debug("REST request to update CategoriaAnexo : {}", categoriaAnexo);
        if (categoriaAnexo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CategoriaAnexo result = categoriaAnexoService.save(categoriaAnexo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, categoriaAnexo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /categoria-anexos} : get all the categoriaAnexos.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of categoriaAnexos in body.
     */
    @GetMapping("/categoria-anexos")
    public List<CategoriaAnexo> getAllCategoriaAnexos() {
        log.debug("REST request to get all CategoriaAnexos");
        return categoriaAnexoService.findAll();
    }

    /**
     * {@code GET  /categoria-anexos/:id} : get the "id" categoriaAnexo.
     *
     * @param id the id of the categoriaAnexo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the categoriaAnexo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/categoria-anexos/{id}")
    public ResponseEntity<CategoriaAnexo> getCategoriaAnexo(@PathVariable Long id) {
        log.debug("REST request to get CategoriaAnexo : {}", id);
        Optional<CategoriaAnexo> categoriaAnexo = categoriaAnexoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(categoriaAnexo);
    }

    /**
     * {@code DELETE  /categoria-anexos/:id} : delete the "id" categoriaAnexo.
     *
     * @param id the id of the categoriaAnexo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/categoria-anexos/{id}")
    public ResponseEntity<Void> deleteCategoriaAnexo(@PathVariable Long id) {
        log.debug("REST request to delete CategoriaAnexo : {}", id);
        categoriaAnexoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
