package br.gov.seplag.app.gestor.web.rest;

import br.gov.seplag.app.gestor.domain.Setor;
import br.gov.seplag.app.gestor.service.SetorService;
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
 * REST controller for managing {@link br.gov.seplag.app.gestor.domain.Setor}.
 */
@RestController
@RequestMapping("/api")
public class SetorResource {

    private final Logger log = LoggerFactory.getLogger(SetorResource.class);

    private static final String ENTITY_NAME = "setor";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SetorService setorService;

    public SetorResource(SetorService setorService) {
        this.setorService = setorService;
    }

    /**
     * {@code POST  /setors} : Create a new setor.
     *
     * @param setor the setor to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new setor, or with status {@code 400 (Bad Request)} if the setor has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/setors")
    public ResponseEntity<Setor> createSetor(@Valid @RequestBody Setor setor) throws URISyntaxException {
        log.debug("REST request to save Setor : {}", setor);
        if (setor.getId() != null) {
            throw new BadRequestAlertException("A new setor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Setor result = setorService.save(setor);
        return ResponseEntity.created(new URI("/api/setors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /setors} : Updates an existing setor.
     *
     * @param setor the setor to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated setor,
     * or with status {@code 400 (Bad Request)} if the setor is not valid,
     * or with status {@code 500 (Internal Server Error)} if the setor couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/setors")
    public ResponseEntity<Setor> updateSetor(@Valid @RequestBody Setor setor) throws URISyntaxException {
        log.debug("REST request to update Setor : {}", setor);
        if (setor.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Setor result = setorService.save(setor);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, setor.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /setors} : get all the setors.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of setors in body.
     */
    @GetMapping("/setors")
    public List<Setor> getAllSetors() {
        log.debug("REST request to get all Setors");
        return setorService.findAll();
    }

    /**
     * {@code GET  /setors/:id} : get the "id" setor.
     *
     * @param id the id of the setor to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the setor, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/setors/{id}")
    public ResponseEntity<Setor> getSetor(@PathVariable Long id) {
        log.debug("REST request to get Setor : {}", id);
        Optional<Setor> setor = setorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(setor);
    }

    /**
     * {@code DELETE  /setors/:id} : delete the "id" setor.
     *
     * @param id the id of the setor to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/setors/{id}")
    public ResponseEntity<Void> deleteSetor(@PathVariable Long id) {
        log.debug("REST request to delete Setor : {}", id);
        setorService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
