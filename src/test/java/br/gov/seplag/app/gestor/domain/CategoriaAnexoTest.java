package br.gov.seplag.app.gestor.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.gov.seplag.app.gestor.web.rest.TestUtil;

public class CategoriaAnexoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategoriaAnexo.class);
        CategoriaAnexo categoriaAnexo1 = new CategoriaAnexo();
        categoriaAnexo1.setId(1L);
        CategoriaAnexo categoriaAnexo2 = new CategoriaAnexo();
        categoriaAnexo2.setId(categoriaAnexo1.getId());
        assertThat(categoriaAnexo1).isEqualTo(categoriaAnexo2);
        categoriaAnexo2.setId(2L);
        assertThat(categoriaAnexo1).isNotEqualTo(categoriaAnexo2);
        categoriaAnexo1.setId(null);
        assertThat(categoriaAnexo1).isNotEqualTo(categoriaAnexo2);
    }
}
