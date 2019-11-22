package br.gov.seplag.app.gestor.service.mapper;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.gov.seplag.app.gestor.domain.Anexo;

@Service
public class AnexoMapper {

	private final Logger log = LoggerFactory.getLogger(AnexoMapper.class);
	   public Set<Anexo> multiPartFilesToDocuments(List<MultipartFile> files){
	       return files.stream()
	           .map((this::multiPartFileToDocument))
	           .collect(Collectors.toSet());
	   }

	   public Anexo multiPartFileToDocument(MultipartFile file) {
		   Anexo document = new Anexo();
	       document.setDescricao(file.getOriginalFilename());
	       document.setTamanho(file.getSize());
	       document.setMimeType(file.getContentType());
	       try {
	           document.addContent(file.getBytes());
	       } catch (IOException e) {
	           log.error(e.getMessage());
	       }

	       return document;
	   }
}
