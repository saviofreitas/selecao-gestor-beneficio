import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IBeneficio, Beneficio } from 'app/shared/model/beneficio.model';
import { BeneficioService } from './beneficio.service';
import { IServidor } from 'app/shared/model/servidor.model';
import { ServidorService } from 'app/entities/servidor/servidor.service';

@Component({
  selector: 'jhi-beneficio-update',
  templateUrl: './beneficio-update.component.html'
})
export class BeneficioUpdateComponent implements OnInit {
  isSaving: boolean;

  servidors: IServidor[];

  editForm = this.fb.group({
    id: [],
    dataCriacao: [null, [Validators.required]],
    dataUltimaMovimentacao: [null, [Validators.required]],
    situacao: [null, [Validators.required]],
    servidor: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected beneficioService: BeneficioService,
    protected servidorService: ServidorService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ beneficio }) => {
      this.updateForm(beneficio);
    });
    this.servidorService
      .query()
      .subscribe((res: HttpResponse<IServidor[]>) => (this.servidors = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(beneficio: IBeneficio) {
    this.editForm.patchValue({
      id: beneficio.id,
      dataCriacao: beneficio.dataCriacao != null ? beneficio.dataCriacao.format(DATE_TIME_FORMAT) : null,
      dataUltimaMovimentacao: beneficio.dataUltimaMovimentacao != null ? beneficio.dataUltimaMovimentacao.format(DATE_TIME_FORMAT) : null,
      situacao: beneficio.situacao,
      servidor: beneficio.servidor
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const beneficio = this.createFromForm();
    if (beneficio.id !== undefined) {
      this.subscribeToSaveResponse(this.beneficioService.update(beneficio));
    } else {
      this.subscribeToSaveResponse(this.beneficioService.create(beneficio));
    }
  }

  private createFromForm(): IBeneficio {
    return {
      ...new Beneficio(),
      id: this.editForm.get(['id']).value,
      dataCriacao:
        this.editForm.get(['dataCriacao']).value != null ? moment(this.editForm.get(['dataCriacao']).value, DATE_TIME_FORMAT) : undefined,
      dataUltimaMovimentacao:
        this.editForm.get(['dataUltimaMovimentacao']).value != null
          ? moment(this.editForm.get(['dataUltimaMovimentacao']).value, DATE_TIME_FORMAT)
          : undefined,
      situacao: this.editForm.get(['situacao']).value,
      servidor: this.editForm.get(['servidor']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBeneficio>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackServidorById(index: number, item: IServidor) {
    return item.id;
  }
}
