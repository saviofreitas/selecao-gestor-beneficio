import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ISetor, Setor } from 'app/shared/model/setor.model';
import { SetorService } from './setor.service';

@Component({
  selector: 'jhi-setor-update',
  templateUrl: './setor-update.component.html'
})
export class SetorUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    descricao: [null, [Validators.required]]
  });

  constructor(protected setorService: SetorService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ setor }) => {
      this.updateForm(setor);
    });
  }

  updateForm(setor: ISetor) {
    this.editForm.patchValue({
      id: setor.id,
      descricao: setor.descricao
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const setor = this.createFromForm();
    if (setor.id !== undefined) {
      this.subscribeToSaveResponse(this.setorService.update(setor));
    } else {
      this.subscribeToSaveResponse(this.setorService.create(setor));
    }
  }

  private createFromForm(): ISetor {
    return {
      ...new Setor(),
      id: this.editForm.get(['id']).value,
      descricao: this.editForm.get(['descricao']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISetor>>) {
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
