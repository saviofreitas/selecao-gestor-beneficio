import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IConteudoAnexo } from 'app/shared/model/conteudo-anexo.model';
import { ConteudoAnexoService } from './conteudo-anexo.service';

@Component({
  templateUrl: './conteudo-anexo-delete-dialog.component.html'
})
export class ConteudoAnexoDeleteDialogComponent {
  conteudoAnexo: IConteudoAnexo;

  constructor(
    protected conteudoAnexoService: ConteudoAnexoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.conteudoAnexoService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'conteudoAnexoListModification',
        content: 'Deleted an conteudoAnexo'
      });
      this.activeModal.dismiss(true);
    });
  }
}
