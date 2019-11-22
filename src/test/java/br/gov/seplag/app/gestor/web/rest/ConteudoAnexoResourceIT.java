package br.gov.seplag.app.gestor.web.rest;

import br.gov.seplag.app.gestor.GestorBeneficioApp;
import br.gov.seplag.app.gestor.domain.ConteudoAnexo;
import br.gov.seplag.app.gestor.domain.Anexo;
import br.gov.seplag.app.gestor.repository.ConteudoAnexoRepository;
import br.gov.seplag.app.gestor.service.ConteudoAnexoService;
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
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static br.gov.seplag.app.gestor.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ConteudoAnexoResource} REST controller.
 */
@SpringBootTest(classes = GestorBeneficioApp.class)
public class ConteudoAnexoResourceIT {

    private static final byte[] DEFAULT_DATA = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_DATA = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_DATA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_DATA_CONTENT_TYPE = "image/png";

    @Autowired
    private ConteudoAnexoRepository conteudoAnexoRepository;

    @Autowired
    private ConteudoAnexoService conteudoAnexoService;

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

    private MockMvc restConteudoAnexoMockMvc;

    private ConteudoAnexo conteudoAnexo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ConteudoAnexoResource conteudoAnexoResource = new ConteudoAnexoResource(conteudoAnexoService);
        this.restConteudoAnexoMockMvc = MockMvcBuilders.standaloneSetup(conteudoAnexoResource)
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
    public static ConteudoAnexo createEntity(EntityManager em) {
        ConteudoAnexo conteudoAnexo = new ConteudoAnexo()
            .data(DEFAULT_DATA)
            .dataContentType(DEFAULT_DATA_CONTENT_TYPE);
        // Add required entity
        Anexo anexo;
        if (TestUtil.findAll(em, Anexo.class).isEmpty()) {
            anexo = AnexoResourceIT.createEntity(em);
            em.persist(anexo);
            em.flush();
        } else {
            anexo = TestUtil.findAll(em, Anexo.class).get(0);
        }
        conteudoAnexo.setAnexo(anexo);
        return conteudoAnexo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ConteudoAnexo createUpdatedEntity(EntityManager em) {
        ConteudoAnexo conteudoAnexo = new ConteudoAnexo()
            .data(UPDATED_DATA)
            .dataContentType(UPDATED_DATA_CONTENT_TYPE);
        // Add required entity
        Anexo anexo;
        if (TestUtil.findAll(em, Anexo.class).isEmpty()) {
            anexo = AnexoResourceIT.createUpdatedEntity(em);
            em.persist(anexo);
            em.flush();
        } else {
            anexo = TestUtil.findAll(em, Anexo.class).get(0);
        }
        conteudoAnexo.setAnexo(anexo);
        return conteudoAnexo;
    }

    @BeforeEach
    public void initTest() {
        conteudoAnexo = createEntity(em);
    }

    @Test
    @Transactional
    public void createConteudoAnexo() throws Exception {
        int databaseSizeBeforeCreate = conteudoAnexoRepository.findAll().size();

        // Create the ConteudoAnexo
        restConteudoAnexoMockMvc.perform(post("/api/conteudo-anexos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conteudoAnexo)))
            .andExpect(status().isCreated());

        // Validate the ConteudoAnexo in the database
        List<ConteudoAnexo> conteudoAnexoList = conteudoAnexoRepository.findAll();
        assertThat(conteudoAnexoList).hasSize(databaseSizeBeforeCreate + 1);
        ConteudoAnexo testConteudoAnexo = conteudoAnexoList.get(conteudoAnexoList.size() - 1);
        assertThat(testConteudoAnexo.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testConteudoAnexo.getDataContentType()).isEqualTo(DEFAULT_DATA_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createConteudoAnexoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = conteudoAnexoRepository.findAll().size();

        // Create the ConteudoAnexo with an existing ID
        conteudoAnexo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConteudoAnexoMockMvc.perform(post("/api/conteudo-anexos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conteudoAnexo)))
            .andExpect(status().isBadRequest());

        // Validate the ConteudoAnexo in the database
        List<ConteudoAnexo> conteudoAnexoList = conteudoAnexoRepository.findAll();
        assertThat(conteudoAnexoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllConteudoAnexos() throws Exception {
        // Initialize the database
        conteudoAnexoRepository.saveAndFlush(conteudoAnexo);

        // Get all the conteudoAnexoList
        restConteudoAnexoMockMvc.perform(get("/api/conteudo-anexos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(conteudoAnexo.getId().intValue())))
            .andExpect(jsonPath("$.[*].dataContentType").value(hasItem(DEFAULT_DATA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].data").value(hasItem(Base64Utils.encodeToString(DEFAULT_DATA))));
    }
    
    @Test
    @Transactional
    public void getConteudoAnexo() throws Exception {
        // Initialize the database
        conteudoAnexoRepository.saveAndFlush(conteudoAnexo);

        // Get the conteudoAnexo
        restConteudoAnexoMockMvc.perform(get("/api/conteudo-anexos/{id}", conteudoAnexo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(conteudoAnexo.getId().intValue()))
            .andExpect(jsonPath("$.dataContentType").value(DEFAULT_DATA_CONTENT_TYPE))
            .andExpect(jsonPath("$.data").value(Base64Utils.encodeToString(DEFAULT_DATA)));
    }

    @Test
    @Transactional
    public void getNonExistingConteudoAnexo() throws Exception {
        // Get the conteudoAnexo
        restConteudoAnexoMockMvc.perform(get("/api/conteudo-anexos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConteudoAnexo() throws Exception {
        // Initialize the database
        conteudoAnexoService.save(conteudoAnexo);

        int databaseSizeBeforeUpdate = conteudoAnexoRepository.findAll().size();

        // Update the conteudoAnexo
        ConteudoAnexo updatedConteudoAnexo = conteudoAnexoRepository.findById(conteudoAnexo.getId()).get();
        // Disconnect from session so that the updates on updatedConteudoAnexo are not directly saved in db
        em.detach(updatedConteudoAnexo);
        updatedConteudoAnexo
            .data(UPDATED_DATA)
            .dataContentType(UPDATED_DATA_CONTENT_TYPE);

        restConteudoAnexoMockMvc.perform(put("/api/conteudo-anexos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedConteudoAnexo)))
            .andExpect(status().isOk());

        // Validate the ConteudoAnexo in the database
        List<ConteudoAnexo> conteudoAnexoList = conteudoAnexoRepository.findAll();
        assertThat(conteudoAnexoList).hasSize(databaseSizeBeforeUpdate);
        ConteudoAnexo testConteudoAnexo = conteudoAnexoList.get(conteudoAnexoList.size() - 1);
        assertThat(testConteudoAnexo.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testConteudoAnexo.getDataContentType()).isEqualTo(UPDATED_DATA_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingConteudoAnexo() throws Exception {
        int databaseSizeBeforeUpdate = conteudoAnexoRepository.findAll().size();

        // Create the ConteudoAnexo

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restConteudoAnexoMockMvc.perform(put("/api/conteudo-anexos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conteudoAnexo)))
            .andExpect(status().isBadRequest());

        // Validate the ConteudoAnexo in the database
        List<ConteudoAnexo> conteudoAnexoList = conteudoAnexoRepository.findAll();
        assertThat(conteudoAnexoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteConteudoAnexo() throws Exception {
        // Initialize the database
        conteudoAnexoService.save(conteudoAnexo);

        int databaseSizeBeforeDelete = conteudoAnexoRepository.findAll().size();

        // Delete the conteudoAnexo
        restConteudoAnexoMockMvc.perform(delete("/api/conteudo-anexos/{id}", conteudoAnexo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ConteudoAnexo> conteudoAnexoList = conteudoAnexoRepository.findAll();
        assertThat(conteudoAnexoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
