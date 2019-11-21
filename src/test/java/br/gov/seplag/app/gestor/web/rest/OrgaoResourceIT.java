package br.gov.seplag.app.gestor.web.rest;

import br.gov.seplag.app.gestor.GestorBeneficioApp;
import br.gov.seplag.app.gestor.domain.Orgao;
import br.gov.seplag.app.gestor.repository.OrgaoRepository;
import br.gov.seplag.app.gestor.service.OrgaoService;
import br.gov.seplag.app.gestor.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static br.gov.seplag.app.gestor.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link OrgaoResource} REST controller.
 */
@SpringBootTest(classes = GestorBeneficioApp.class)
public class OrgaoResourceIT {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    @Autowired
    private OrgaoRepository orgaoRepository;

    @Autowired
    private OrgaoService orgaoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restOrgaoMockMvc;

    private Orgao orgao;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrgaoResource orgaoResource = new OrgaoResource(orgaoService);
        this.restOrgaoMockMvc = MockMvcBuilders.standaloneSetup(orgaoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Orgao createEntity(EntityManager em) {
        Orgao orgao = new Orgao()
            .descricao(DEFAULT_DESCRICAO);
        return orgao;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Orgao createUpdatedEntity(EntityManager em) {
        Orgao orgao = new Orgao()
            .descricao(UPDATED_DESCRICAO);
        return orgao;
    }

    @BeforeEach
    public void initTest() {
        orgao = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrgao() throws Exception {
        int databaseSizeBeforeCreate = orgaoRepository.findAll().size();

        // Create the Orgao
        restOrgaoMockMvc.perform(post("/api/orgaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orgao)))
            .andExpect(status().isCreated());

        // Validate the Orgao in the database
        List<Orgao> orgaoList = orgaoRepository.findAll();
        assertThat(orgaoList).hasSize(databaseSizeBeforeCreate + 1);
        Orgao testOrgao = orgaoList.get(orgaoList.size() - 1);
        assertThat(testOrgao.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
    }

    @Test
    @Transactional
    public void createOrgaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orgaoRepository.findAll().size();

        // Create the Orgao with an existing ID
        orgao.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrgaoMockMvc.perform(post("/api/orgaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orgao)))
            .andExpect(status().isBadRequest());

        // Validate the Orgao in the database
        List<Orgao> orgaoList = orgaoRepository.findAll();
        assertThat(orgaoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDescricaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = orgaoRepository.findAll().size();
        // set the field null
        orgao.setDescricao(null);

        // Create the Orgao, which fails.

        restOrgaoMockMvc.perform(post("/api/orgaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orgao)))
            .andExpect(status().isBadRequest());

        List<Orgao> orgaoList = orgaoRepository.findAll();
        assertThat(orgaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOrgaos() throws Exception {
        // Initialize the database
        orgaoRepository.saveAndFlush(orgao);

        // Get all the orgaoList
        restOrgaoMockMvc.perform(get("/api/orgaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orgao.getId().intValue())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO)));
    }
    
    @Test
    @Transactional
    public void getOrgao() throws Exception {
        // Initialize the database
        orgaoRepository.saveAndFlush(orgao);

        // Get the orgao
        restOrgaoMockMvc.perform(get("/api/orgaos/{id}", orgao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(orgao.getId().intValue()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO));
    }

    @Test
    @Transactional
    public void getNonExistingOrgao() throws Exception {
        // Get the orgao
        restOrgaoMockMvc.perform(get("/api/orgaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrgao() throws Exception {
        // Initialize the database
        orgaoService.save(orgao);

        int databaseSizeBeforeUpdate = orgaoRepository.findAll().size();

        // Update the orgao
        Orgao updatedOrgao = orgaoRepository.findById(orgao.getId()).get();
        // Disconnect from session so that the updates on updatedOrgao are not directly saved in db
        em.detach(updatedOrgao);
        updatedOrgao
            .descricao(UPDATED_DESCRICAO);

        restOrgaoMockMvc.perform(put("/api/orgaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedOrgao)))
            .andExpect(status().isOk());

        // Validate the Orgao in the database
        List<Orgao> orgaoList = orgaoRepository.findAll();
        assertThat(orgaoList).hasSize(databaseSizeBeforeUpdate);
        Orgao testOrgao = orgaoList.get(orgaoList.size() - 1);
        assertThat(testOrgao.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
    }

    @Test
    @Transactional
    public void updateNonExistingOrgao() throws Exception {
        int databaseSizeBeforeUpdate = orgaoRepository.findAll().size();

        // Create the Orgao

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrgaoMockMvc.perform(put("/api/orgaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orgao)))
            .andExpect(status().isBadRequest());

        // Validate the Orgao in the database
        List<Orgao> orgaoList = orgaoRepository.findAll();
        assertThat(orgaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrgao() throws Exception {
        // Initialize the database
        orgaoService.save(orgao);

        int databaseSizeBeforeDelete = orgaoRepository.findAll().size();

        // Delete the orgao
        restOrgaoMockMvc.perform(delete("/api/orgaos/{id}", orgao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Orgao> orgaoList = orgaoRepository.findAll();
        assertThat(orgaoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
