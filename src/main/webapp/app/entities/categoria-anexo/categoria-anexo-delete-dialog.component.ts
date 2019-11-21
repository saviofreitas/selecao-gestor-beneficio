import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICategoriaAnexo } from 'app/shared/model/categoria-anexo.model';
import { CategoriaAnexoService } from './categoria-anexo.service';

@Component({
  templateUrl: './categoria-anexo-delete-dialog.component.html'
})
export class CategoriaAnexoDeleteDialogComponent {
  categoriaAnexo: ICategoriaAnexo;

  constructor(
    protected categoriaAnexoService: CategoriaAnexoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.categoriaAnexoService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'categoriaAnexoListModification',
        content: 'Deleted an categoriaAnexo'
      });
      this.activeModal.dismiss(true);
    });
  }
}
