import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICategoriaAnexo } from 'app/shared/model/categoria-anexo.model';
import { CategoriaAnexoService } from './categoria-anexo.service';
import { CategoriaAnexoDeleteDialogComponent } from './categoria-anexo-delete-dialog.component';

@Component({
  selector: 'jhi-categoria-anexo',
  templateUrl: './categoria-anexo.component.html'
})
export class CategoriaAnexoComponent implements OnInit, OnDestroy {
  categoriaAnexos: ICategoriaAnexo[];
  eventSubscriber: Subscription;

  constructor(
    protected categoriaAnexoService: CategoriaAnexoService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll() {
    this.categoriaAnexoService.query().subscribe((res: HttpResponse<ICategoriaAnexo[]>) => {
      this.categoriaAnexos = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInCategoriaAnexos();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ICategoriaAnexo) {
    return item.id;
  }

  registerChangeInCategoriaAnexos() {
    this.eventSubscriber = this.eventManager.subscribe('categoriaAnexoListModification', () => this.loadAll());
  }

  delete(categoriaAnexo: ICategoriaAnexo) {
    const modalRef = this.modalService.open(CategoriaAnexoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.categoriaAnexo = categoriaAnexo;
  }
}
