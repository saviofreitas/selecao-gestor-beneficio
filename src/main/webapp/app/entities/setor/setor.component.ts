import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISetor } from 'app/shared/model/setor.model';
import { SetorService } from './setor.service';
import { SetorDeleteDialogComponent } from './setor-delete-dialog.component';

@Component({
  selector: 'jhi-setor',
  templateUrl: './setor.component.html'
})
export class SetorComponent implements OnInit, OnDestroy {
  setors: ISetor[];
  eventSubscriber: Subscription;

  constructor(protected setorService: SetorService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll() {
    this.setorService.query().subscribe((res: HttpResponse<ISetor[]>) => {
      this.setors = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInSetors();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ISetor) {
    return item.id;
  }

  registerChangeInSetors() {
    this.eventSubscriber = this.eventManager.subscribe('setorListModification', () => this.loadAll());
  }

  delete(setor: ISetor) {
    const modalRef = this.modalService.open(SetorDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.setor = setor;
  }
}
