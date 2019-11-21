import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMovimentacaoBeneficio } from 'app/shared/model/movimentacao-beneficio.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { MovimentacaoBeneficioService } from './movimentacao-beneficio.service';
import { MovimentacaoBeneficioDeleteDialogComponent } from './movimentacao-beneficio-delete-dialog.component';

@Component({
  selector: 'jhi-movimentacao-beneficio',
  templateUrl: './movimentacao-beneficio.component.html'
})
export class MovimentacaoBeneficioComponent implements OnInit, OnDestroy {
  movimentacaoBeneficios: IMovimentacaoBeneficio[];
  eventSubscriber: Subscription;
  itemsPerPage: number;
  links: any;
  page: any;
  predicate: any;
  reverse: any;
  totalItems: number;

  constructor(
    protected movimentacaoBeneficioService: MovimentacaoBeneficioService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.movimentacaoBeneficios = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.reverse = true;
  }

  loadAll() {
    this.movimentacaoBeneficioService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IMovimentacaoBeneficio[]>) => this.paginateMovimentacaoBeneficios(res.body, res.headers));
  }

  reset() {
    this.page = 0;
    this.movimentacaoBeneficios = [];
    this.loadAll();
  }

  loadPage(page) {
    this.page = page;
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInMovimentacaoBeneficios();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IMovimentacaoBeneficio) {
    return item.id;
  }

  registerChangeInMovimentacaoBeneficios() {
    this.eventSubscriber = this.eventManager.subscribe('movimentacaoBeneficioListModification', () => this.reset());
  }

  delete(movimentacaoBeneficio: IMovimentacaoBeneficio) {
    const modalRef = this.modalService.open(MovimentacaoBeneficioDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.movimentacaoBeneficio = movimentacaoBeneficio;
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateMovimentacaoBeneficios(data: IMovimentacaoBeneficio[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    for (let i = 0; i < data.length; i++) {
      this.movimentacaoBeneficios.push(data[i]);
    }
  }
}
