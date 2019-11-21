import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBeneficio } from 'app/shared/model/beneficio.model';
import { BeneficioService } from './beneficio.service';
import { BeneficioDeleteDialogComponent } from './beneficio-delete-dialog.component';

@Component({
  selector: 'jhi-beneficio',
  templateUrl: './beneficio.component.html'
})
export class BeneficioComponent implements OnInit, OnDestroy {
  beneficios: IBeneficio[];
  eventSubscriber: Subscription;

  constructor(protected beneficioService: BeneficioService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll() {
    this.beneficioService.query().subscribe((res: HttpResponse<IBeneficio[]>) => {
      this.beneficios = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInBeneficios();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IBeneficio) {
    return item.id;
  }

  registerChangeInBeneficios() {
    this.eventSubscriber = this.eventManager.subscribe('beneficioListModification', () => this.loadAll());
  }

  delete(beneficio: IBeneficio) {
    const modalRef = this.modalService.open(BeneficioDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.beneficio = beneficio;
  }
}
