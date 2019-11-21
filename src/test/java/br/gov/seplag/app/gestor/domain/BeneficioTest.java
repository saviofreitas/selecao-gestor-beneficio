package br.gov.seplag.app.gestor.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.gov.seplag.app.gestor.web.rest.TestUtil;

public class BeneficioTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Beneficio.class);
        Beneficio beneficio1 = new Beneficio();
        beneficio1.setId(1L);
        Beneficio beneficio2 = new Beneficio();
        beneficio2.setId(beneficio1.getId());
        assertThat(beneficio1).isEqualTo(beneficio2);
        beneficio2.setId(2L);
        assertThat(beneficio1).isNotEqualTo(beneficio2);
        beneficio1.setId(null);
        assertThat(beneficio1).isNotEqualTo(beneficio2);
    }
}
