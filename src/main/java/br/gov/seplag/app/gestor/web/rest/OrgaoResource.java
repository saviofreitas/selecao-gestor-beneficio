package br.gov.seplag.app.gestor.web.rest;

import br.gov.seplag.app.gestor.domain.Orgao;
import br.gov.seplag.app.gestor.service.OrgaoService;
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
 * REST controller for managing {@link br.gov.seplag.app.gestor.domain.Orgao}.
 */
@RestController
@RequestMapping("/api")
public class OrgaoResource {

    private final Logger log = LoggerFactory.getLogger(OrgaoResource.class);

    private static final String ENTITY_NAME = "orgao";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrgaoService orgaoService;

    public OrgaoResource(OrgaoService orgaoService) {
        this.orgaoService = orgaoService;
    }

    /**
     * {@code POST  /orgaos} : Create a new orgao.
     *
     * @param orgao the orgao to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new orgao, or with status {@code 400 (Bad Request)} if the orgao has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/orgaos")
    public ResponseEntity<Orgao> createOrgao(@Valid @RequestBody Orgao orgao) throws URISyntaxException {
        log.debug("REST request to save Orgao : {}", orgao);
        if (orgao.getId() != null) {
            throw new BadRequestAlertException("A new orgao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Orgao result = orgaoService.save(orgao);
        return ResponseEntity.created(new URI("/api/orgaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /orgaos} : Updates an existing orgao.
     *
     * @param orgao the orgao to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orgao,
     * or with status {@code 400 (Bad Request)} if the orgao is not valid,
     * or with status {@code 500 (Internal Server Error)} if the orgao couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/orgaos")
    public ResponseEntity<Orgao> updateOrgao(@Valid @RequestBody Orgao orgao) throws URISyntaxException {
        log.debug("REST request to update Orgao : {}", orgao);
        if (orgao.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Orgao result = orgaoService.save(orgao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orgao.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /orgaos} : get all the orgaos.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orgaos in body.
     */
    @GetMapping("/orgaos")
    public List<Orgao> getAllOrgaos() {
        log.debug("REST request to get all Orgaos");
        return orgaoService.findAll();
    }

    /**
     * {@code GET  /orgaos/:id} : get the "id" orgao.
     *
     * @param id the id of the orgao to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the orgao, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/orgaos/{id}")
    public ResponseEntity<Orgao> getOrgao(@PathVariable Long id) {
        log.debug("REST request to get Orgao : {}", id);
        Optional<Orgao> orgao = orgaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(orgao);
    }

    /**
     * {@code DELETE  /orgaos/:id} : delete the "id" orgao.
     *
     * @param id the id of the orgao to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/orgaos/{id}")
    public ResponseEntity<Void> deleteOrgao(@PathVariable Long id) {
        log.debug("REST request to delete Orgao : {}", id);
        orgaoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
