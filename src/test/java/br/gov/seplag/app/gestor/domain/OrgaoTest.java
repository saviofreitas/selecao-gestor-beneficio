package br.gov.seplag.app.gestor.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.gov.seplag.app.gestor.web.rest.TestUtil;

public class OrgaoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Orgao.class);
        Orgao orgao1 = new Orgao();
        orgao1.setId(1L);
        Orgao orgao2 = new Orgao();
        orgao2.setId(orgao1.getId());
        assertThat(orgao1).isEqualTo(orgao2);
        orgao2.setId(2L);
        assertThat(orgao1).isNotEqualTo(orgao2);
        orgao1.setId(null);
        assertThat(orgao1).isNotEqualTo(orgao2);
    }
}
