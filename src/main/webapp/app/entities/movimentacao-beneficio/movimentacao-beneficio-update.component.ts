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
import { IMovimentacaoBeneficio, MovimentacaoBeneficio } from 'app/shared/model/movimentacao-beneficio.model';
import { MovimentacaoBeneficioService } from './movimentacao-beneficio.service';
import { IBeneficio } from 'app/shared/model/beneficio.model';
import { BeneficioService } from 'app/entities/beneficio/beneficio.service';
import { ISetor } from 'app/shared/model/setor.model';
import { SetorService } from 'app/entities/setor/setor.service';

@Component({
  selector: 'jhi-movimentacao-beneficio-update',
  templateUrl: './movimentacao-beneficio-update.component.html'
})
export class MovimentacaoBeneficioUpdateComponent implements OnInit {
  isSaving: boolean;

  beneficios: IBeneficio[];

  setors: ISetor[];

  editForm = this.fb.group({
    id: [],
    dataTramitacao: [null, [Validators.required]],
    responsavel: [],
    beneficio: [],
    setorOrigem: [],
    setorDestino: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected movimentacaoBeneficioService: MovimentacaoBeneficioService,
    protected beneficioService: BeneficioService,
    protected setorService: SetorService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ movimentacaoBeneficio }) => {
      this.updateForm(movimentacaoBeneficio);
    });
    this.beneficioService
      .query()
      .subscribe((res: HttpResponse<IBeneficio[]>) => (this.beneficios = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.setorService
      .query()
      .subscribe((res: HttpResponse<ISetor[]>) => (this.setors = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(movimentacaoBeneficio: IMovimentacaoBeneficio) {
    this.editForm.patchValue({
      id: movimentacaoBeneficio.id,
      dataTramitacao: movimentacaoBeneficio.dataTramitacao != null ? movimentacaoBeneficio.dataTramitacao.format(DATE_TIME_FORMAT) : null,
      responsavel: movimentacaoBeneficio.responsavel,
      beneficio: movimentacaoBeneficio.beneficio,
      setorOrigem: movimentacaoBeneficio.setorOrigem,
      setorDestino: movimentacaoBeneficio.setorDestino
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const movimentacaoBeneficio = this.createFromForm();
    if (movimentacaoBeneficio.id !== undefined) {
      this.subscribeToSaveResponse(this.movimentacaoBeneficioService.update(movimentacaoBeneficio));
    } else {
      this.subscribeToSaveResponse(this.movimentacaoBeneficioService.create(movimentacaoBeneficio));
    }
  }

  private createFromForm(): IMovimentacaoBeneficio {
    return {
      ...new MovimentacaoBeneficio(),
      id: this.editForm.get(['id']).value,
      dataTramitacao:
        this.editForm.get(['dataTramitacao']).value != null
          ? moment(this.editForm.get(['dataTramitacao']).value, DATE_TIME_FORMAT)
          : undefined,
      responsavel: this.editForm.get(['responsavel']).value,
      beneficio: this.editForm.get(['beneficio']).value,
      setorOrigem: this.editForm.get(['setorOrigem']).value,
      setorDestino: this.editForm.get(['setorDestino']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMovimentacaoBeneficio>>) {
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

  trackBeneficioById(index: number, item: IBeneficio) {
    return item.id;
  }

  trackSetorById(index: number, item: ISetor) {
    return item.id;
  }
}
