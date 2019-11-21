import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAnexo } from 'app/shared/model/anexo.model';
import { AnexoService } from './anexo.service';
import { AnexoDeleteDialogComponent } from './anexo-delete-dialog.component';

@Component({
  selector: 'jhi-anexo',
  templateUrl: './anexo.component.html'
})
export class AnexoComponent implements OnInit, OnDestroy {
  anexos: IAnexo[];
  eventSubscriber: Subscription;

  constructor(protected anexoService: AnexoService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll() {
    this.anexoService.query().subscribe((res: HttpResponse<IAnexo[]>) => {
      this.anexos = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInAnexos();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IAnexo) {
    return item.id;
  }

  registerChangeInAnexos() {
    this.eventSubscriber = this.eventManager.subscribe('anexoListModification', () => this.loadAll());
  }

  delete(anexo: IAnexo) {
    const modalRef = this.modalService.open(AnexoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.anexo = anexo;
  }
}
