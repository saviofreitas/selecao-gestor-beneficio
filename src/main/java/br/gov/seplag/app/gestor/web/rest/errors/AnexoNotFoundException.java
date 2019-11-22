package br.gov.seplag.app.gestor.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class AnexoNotFoundException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public AnexoNotFoundException() {
        super(ErrorConstants.DEFAULT_TYPE, "Anexo não encontrado", Status.BAD_REQUEST);
    }
}
