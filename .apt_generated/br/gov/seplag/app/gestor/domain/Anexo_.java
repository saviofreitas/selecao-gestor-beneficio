package br.gov.seplag.app.gestor.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Anexo.class)
public abstract class Anexo_ {

	public static volatile SingularAttribute<Anexo, Long> tamanho;
	public static volatile SingularAttribute<Anexo, Beneficio> beneficio;
	public static volatile SingularAttribute<Anexo, CategoriaAnexo> categoria;
	public static volatile SingularAttribute<Anexo, ConteudoAnexo> conteudoAnexo;
	public static volatile SingularAttribute<Anexo, Long> id;
	public static volatile SingularAttribute<Anexo, String> mimeType;
	public static volatile SingularAttribute<Anexo, String> descricao;

	public static final String TAMANHO = "tamanho";
	public static final String BENEFICIO = "beneficio";
	public static final String CATEGORIA = "categoria";
	public static final String CONTEUDO_ANEXO = "conteudoAnexo";
	public static final String ID = "id";
	public static final String MIME_TYPE = "mimeType";
	public static final String DESCRICAO = "descricao";

}

