import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IOrgao, Orgao } from 'app/shared/model/orgao.model';
import { OrgaoService } from './orgao.service';

@Component({
  selector: 'jhi-orgao-update',
  templateUrl: './orgao-update.component.html'
})
export class OrgaoUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    descricao: [null, [Validators.required]]
  });

  constructor(protected orgaoService: OrgaoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ orgao }) => {
      this.updateForm(orgao);
    });
  }

  updateForm(orgao: IOrgao) {
    this.editForm.patchValue({
      id: orgao.id,
      descricao: orgao.descricao
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const orgao = this.createFromForm();
    if (orgao.id !== undefined) {
      this.subscribeToSaveResponse(this.orgaoService.update(orgao));
    } else {
      this.subscribeToSaveResponse(this.orgaoService.create(orgao));
    }
  }

  private createFromForm(): IOrgao {
    return {
      ...new Orgao(),
      id: this.editForm.get(['id']).value,
      descricao: this.editForm.get(['descricao']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrgao>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
