package br.gov.seplag.app.gestor.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.gov.seplag.app.gestor.web.rest.TestUtil;

public class ConteudoAnexoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConteudoAnexo.class);
        ConteudoAnexo conteudoAnexo1 = new ConteudoAnexo();
        conteudoAnexo1.setId(1L);
        ConteudoAnexo conteudoAnexo2 = new ConteudoAnexo();
        conteudoAnexo2.setId(conteudoAnexo1.getId());
        assertThat(conteudoAnexo1).isEqualTo(conteudoAnexo2);
        conteudoAnexo2.setId(2L);
        assertThat(conteudoAnexo1).isNotEqualTo(conteudoAnexo2);
        conteudoAnexo1.setId(null);
        assertThat(conteudoAnexo1).isNotEqualTo(conteudoAnexo2);
    }
}
