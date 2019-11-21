package br.gov.seplag.app.gestor.web.rest;

import br.gov.seplag.app.gestor.GestorBeneficioApp;
import br.gov.seplag.app.gestor.domain.CategoriaAnexo;
import br.gov.seplag.app.gestor.repository.CategoriaAnexoRepository;
import br.gov.seplag.app.gestor.service.CategoriaAnexoService;
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
 * Integration tests for the {@link CategoriaAnexoResource} REST controller.
 */
@SpringBootTest(classes = GestorBeneficioApp.class)
public class CategoriaAnexoResourceIT {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    @Autowired
    private CategoriaAnexoRepository categoriaAnexoRepository;

    @Autowired
    private CategoriaAnexoService categoriaAnexoService;

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

    private MockMvc restCategoriaAnexoMockMvc;

    private CategoriaAnexo categoriaAnexo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CategoriaAnexoResource categoriaAnexoResource = new CategoriaAnexoResource(categoriaAnexoService);
        this.restCategoriaAnexoMockMvc = MockMvcBuilders.standaloneSetup(categoriaAnexoResource)
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
    public static CategoriaAnexo createEntity(EntityManager em) {
        CategoriaAnexo categoriaAnexo = new CategoriaAnexo()
            .descricao(DEFAULT_DESCRICAO);
        return categoriaAnexo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategoriaAnexo createUpdatedEntity(EntityManager em) {
        CategoriaAnexo categoriaAnexo = new CategoriaAnexo()
            .descricao(UPDATED_DESCRICAO);
        return categoriaAnexo;
    }

    @BeforeEach
    public void initTest() {
        categoriaAnexo = createEntity(em);
    }

    @Test
    @Transactional
    public void createCategoriaAnexo() throws Exception {
        int databaseSizeBeforeCreate = categoriaAnexoRepository.findAll().size();

        // Create the CategoriaAnexo
        restCategoriaAnexoMockMvc.perform(post("/api/categoria-anexos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categoriaAnexo)))
            .andExpect(status().isCreated());

        // Validate the CategoriaAnexo in the database
        List<CategoriaAnexo> categoriaAnexoList = categoriaAnexoRepository.findAll();
        assertThat(categoriaAnexoList).hasSize(databaseSizeBeforeCreate + 1);
        CategoriaAnexo testCategoriaAnexo = categoriaAnexoList.get(categoriaAnexoList.size() - 1);
        assertThat(testCategoriaAnexo.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
    }

    @Test
    @Transactional
    public void createCategoriaAnexoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = categoriaAnexoRepository.findAll().size();

        // Create the CategoriaAnexo with an existing ID
        categoriaAnexo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCategoriaAnexoMockMvc.perform(post("/api/categoria-anexos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categoriaAnexo)))
            .andExpect(status().isBadRequest());

        // Validate the CategoriaAnexo in the database
        List<CategoriaAnexo> categoriaAnexoList = categoriaAnexoRepository.findAll();
        assertThat(categoriaAnexoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDescricaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = categoriaAnexoRepository.findAll().size();
        // set the field null
        categoriaAnexo.setDescricao(null);

        // Create the CategoriaAnexo, which fails.

        restCategoriaAnexoMockMvc.perform(post("/api/categoria-anexos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categoriaAnexo)))
            .andExpect(status().isBadRequest());

        List<CategoriaAnexo> categoriaAnexoList = categoriaAnexoRepository.findAll();
        assertThat(categoriaAnexoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCategoriaAnexos() throws Exception {
        // Initialize the database
        categoriaAnexoRepository.saveAndFlush(categoriaAnexo);

        // Get all the categoriaAnexoList
        restCategoriaAnexoMockMvc.perform(get("/api/categoria-anexos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(categoriaAnexo.getId().intValue())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO)));
    }
    
    @Test
    @Transactional
    public void getCategoriaAnexo() throws Exception {
        // Initialize the database
        categoriaAnexoRepository.saveAndFlush(categoriaAnexo);

        // Get the categoriaAnexo
        restCategoriaAnexoMockMvc.perform(get("/api/categoria-anexos/{id}", categoriaAnexo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(categoriaAnexo.getId().intValue()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO));
    }

    @Test
    @Transactional
    public void getNonExistingCategoriaAnexo() throws Exception {
        // Get the categoriaAnexo
        restCategoriaAnexoMockMvc.perform(get("/api/categoria-anexos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCategoriaAnexo() throws Exception {
        // Initialize the database
        categoriaAnexoService.save(categoriaAnexo);

        int databaseSizeBeforeUpdate = categoriaAnexoRepository.findAll().size();

        // Update the categoriaAnexo
        CategoriaAnexo updatedCategoriaAnexo = categoriaAnexoRepository.findById(categoriaAnexo.getId()).get();
        // Disconnect from session so that the updates on updatedCategoriaAnexo are not directly saved in db
        em.detach(updatedCategoriaAnexo);
        updatedCategoriaAnexo
            .descricao(UPDATED_DESCRICAO);

        restCategoriaAnexoMockMvc.perform(put("/api/categoria-anexos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCategoriaAnexo)))
            .andExpect(status().isOk());

        // Validate the CategoriaAnexo in the database
        List<CategoriaAnexo> categoriaAnexoList = categoriaAnexoRepository.findAll();
        assertThat(categoriaAnexoList).hasSize(databaseSizeBeforeUpdate);
        CategoriaAnexo testCategoriaAnexo = categoriaAnexoList.get(categoriaAnexoList.size() - 1);
        assertThat(testCategoriaAnexo.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
    }

    @Test
    @Transactional
    public void updateNonExistingCategoriaAnexo() throws Exception {
        int databaseSizeBeforeUpdate = categoriaAnexoRepository.findAll().size();

        // Create the CategoriaAnexo

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCategoriaAnexoMockMvc.perform(put("/api/categoria-anexos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(categoriaAnexo)))
            .andExpect(status().isBadRequest());

        // Validate the CategoriaAnexo in the database
        List<CategoriaAnexo> categoriaAnexoList = categoriaAnexoRepository.findAll();
        assertThat(categoriaAnexoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCategoriaAnexo() throws Exception {
        // Initialize the database
        categoriaAnexoService.save(categoriaAnexo);

        int databaseSizeBeforeDelete = categoriaAnexoRepository.findAll().size();

        // Delete the categoriaAnexo
        restCategoriaAnexoMockMvc.perform(delete("/api/categoria-anexos/{id}", categoriaAnexo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CategoriaAnexo> categoriaAnexoList = categoriaAnexoRepository.findAll();
        assertThat(categoriaAnexoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
