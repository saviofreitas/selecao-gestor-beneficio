import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ICategoriaAnexo, CategoriaAnexo } from 'app/shared/model/categoria-anexo.model';
import { CategoriaAnexoService } from './categoria-anexo.service';

@Component({
  selector: 'jhi-categoria-anexo-update',
  templateUrl: './categoria-anexo-update.component.html'
})
export class CategoriaAnexoUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    descricao: [null, [Validators.required]]
  });

  constructor(protected categoriaAnexoService: CategoriaAnexoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ categoriaAnexo }) => {
      this.updateForm(categoriaAnexo);
    });
  }

  updateForm(categoriaAnexo: ICategoriaAnexo) {
    this.editForm.patchValue({
      id: categoriaAnexo.id,
      descricao: categoriaAnexo.descricao
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const categoriaAnexo = this.createFromForm();
    if (categoriaAnexo.id !== undefined) {
      this.subscribeToSaveResponse(this.categoriaAnexoService.update(categoriaAnexo));
    } else {
      this.subscribeToSaveResponse(this.categoriaAnexoService.create(categoriaAnexo));
    }
  }

  private createFromForm(): ICategoriaAnexo {
    return {
      ...new CategoriaAnexo(),
      id: this.editForm.get(['id']).value,
      descricao: this.editForm.get(['descricao']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICategoriaAnexo>>) {
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
