import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IOrgao } from 'app/shared/model/orgao.model';
import { OrgaoService } from './orgao.service';
import { OrgaoDeleteDialogComponent } from './orgao-delete-dialog.component';

@Component({
  selector: 'jhi-orgao',
  templateUrl: './orgao.component.html'
})
export class OrgaoComponent implements OnInit, OnDestroy {
  orgaos: IOrgao[];
  eventSubscriber: Subscription;

  constructor(protected orgaoService: OrgaoService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll() {
    this.orgaoService.query().subscribe((res: HttpResponse<IOrgao[]>) => {
      this.orgaos = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInOrgaos();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IOrgao) {
    return item.id;
  }

  registerChangeInOrgaos() {
    this.eventSubscriber = this.eventManager.subscribe('orgaoListModification', () => this.loadAll());
  }

  delete(orgao: IOrgao) {
    const modalRef = this.modalService.open(OrgaoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.orgao = orgao;
  }
}
