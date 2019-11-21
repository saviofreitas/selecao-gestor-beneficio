package br.gov.seplag.app.gestor.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.gov.seplag.app.gestor.web.rest.TestUtil;

public class MovimentacaoBeneficioTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MovimentacaoBeneficio.class);
        MovimentacaoBeneficio movimentacaoBeneficio1 = new MovimentacaoBeneficio();
        movimentacaoBeneficio1.setId(1L);
        MovimentacaoBeneficio movimentacaoBeneficio2 = new MovimentacaoBeneficio();
        movimentacaoBeneficio2.setId(movimentacaoBeneficio1.getId());
        assertThat(movimentacaoBeneficio1).isEqualTo(movimentacaoBeneficio2);
        movimentacaoBeneficio2.setId(2L);
        assertThat(movimentacaoBeneficio1).isNotEqualTo(movimentacaoBeneficio2);
        movimentacaoBeneficio1.setId(null);
        assertThat(movimentacaoBeneficio1).isNotEqualTo(movimentacaoBeneficio2);
    }
}
