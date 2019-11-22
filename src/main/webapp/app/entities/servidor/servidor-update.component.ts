import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IServidor, Servidor } from 'app/shared/model/servidor.model';
import { ServidorService } from './servidor.service';
import { IOrgao } from 'app/shared/model/orgao.model';
import { OrgaoService } from 'app/entities/orgao/orgao.service';

@Component({
  selector: 'jhi-servidor-update',
  templateUrl: './servidor-update.component.html'
})
export class ServidorUpdateComponent implements OnInit {
  isSaving: boolean;

  orgaos: IOrgao[];

  editForm = this.fb.group({
    id: [],
    nome: [null, [Validators.required]],
    cpf: [null, [Validators.required]],
    matricula: [null, [Validators.required]],
    orgao: [null, [Validators.required]]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected servidorService: ServidorService,
    protected orgaoService: OrgaoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ servidor }) => {
      this.updateForm(servidor);
    });
    this.orgaoService
      .query()
      .subscribe((res: HttpResponse<IOrgao[]>) => (this.orgaos = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(servidor: IServidor) {
    this.editForm.patchValue({
      id: servidor.id,
      nome: servidor.nome,
      cpf: servidor.cpf,
      matricula: servidor.matricula,
      orgao: servidor.orgao
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const servidor = this.createFromForm();
    if (servidor.id !== undefined) {
      this.subscribeToSaveResponse(this.servidorService.update(servidor));
    } else {
      this.subscribeToSaveResponse(this.servidorService.create(servidor));
    }
  }

  private createFromForm(): IServidor {
    return {
      ...new Servidor(),
      id: this.editForm.get(['id']).value,
      nome: this.editForm.get(['nome']).value,
      cpf: this.editForm.get(['cpf']).value,
      matricula: this.editForm.get(['matricula']).value,
      orgao: this.editForm.get(['orgao']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IServidor>>) {
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

  trackOrgaoById(index: number, item: IOrgao) {
    return item.id;
  }
}
