import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IConteudoAnexo } from 'app/shared/model/conteudo-anexo.model';
import { ConteudoAnexoService } from './conteudo-anexo.service';
import { ConteudoAnexoDeleteDialogComponent } from './conteudo-anexo-delete-dialog.component';

@Component({
  selector: 'jhi-conteudo-anexo',
  templateUrl: './conteudo-anexo.component.html'
})
export class ConteudoAnexoComponent implements OnInit, OnDestroy {
  conteudoAnexos: IConteudoAnexo[];
  eventSubscriber: Subscription;

  constructor(
    protected conteudoAnexoService: ConteudoAnexoService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll() {
    this.conteudoAnexoService.query().subscribe((res: HttpResponse<IConteudoAnexo[]>) => {
      this.conteudoAnexos = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInConteudoAnexos();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IConteudoAnexo) {
    return item.id;
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  registerChangeInConteudoAnexos() {
    this.eventSubscriber = this.eventManager.subscribe('conteudoAnexoListModification', () => this.loadAll());
  }

  delete(conteudoAnexo: IConteudoAnexo) {
    const modalRef = this.modalService.open(ConteudoAnexoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.conteudoAnexo = conteudoAnexo;
  }
}
