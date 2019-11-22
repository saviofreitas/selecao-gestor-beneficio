import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IAnexo, Anexo } from 'app/shared/model/anexo.model';
import { AnexoService } from './anexo.service';
import { IConteudoAnexo } from 'app/shared/model/conteudo-anexo.model';
import { ConteudoAnexoService } from 'app/entities/conteudo-anexo/conteudo-anexo.service';
import { ICategoriaAnexo } from 'app/shared/model/categoria-anexo.model';
import { CategoriaAnexoService } from 'app/entities/categoria-anexo/categoria-anexo.service';
import { IBeneficio } from 'app/shared/model/beneficio.model';
import { BeneficioService } from 'app/entities/beneficio/beneficio.service';

@Component({
  selector: 'jhi-anexo-update',
  templateUrl: './anexo-update.component.html'
})
export class AnexoUpdateComponent implements OnInit {
  isSaving: boolean;

  conteudoanexos: IConteudoAnexo[];

  categoriaanexos: ICategoriaAnexo[];

  beneficios: IBeneficio[];

  editForm = this.fb.group({
    id: [],
    descricao: [null, [Validators.required]],
    tamanho: [null, [Validators.required]],
    mimeType: [],
    conteudoAnexo: [],
    categoria: [],
    beneficio: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected anexoService: AnexoService,
    protected conteudoAnexoService: ConteudoAnexoService,
    protected categoriaAnexoService: CategoriaAnexoService,
    protected beneficioService: BeneficioService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ anexo }) => {
      this.updateForm(anexo);
    });
    this.conteudoAnexoService.query({ filter: 'anexo-is-null' }).subscribe(
      (res: HttpResponse<IConteudoAnexo[]>) => {
        if (!this.editForm.get('conteudoAnexo').value || !this.editForm.get('conteudoAnexo').value.id) {
          this.conteudoanexos = res.body;
        } else {
          this.conteudoAnexoService
            .find(this.editForm.get('conteudoAnexo').value.id)
            .subscribe(
              (subRes: HttpResponse<IConteudoAnexo>) => (this.conteudoanexos = [subRes.body].concat(res.body)),
              (subRes: HttpErrorResponse) => this.onError(subRes.message)
            );
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.categoriaAnexoService
      .query()
      .subscribe(
        (res: HttpResponse<ICategoriaAnexo[]>) => (this.categoriaanexos = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.beneficioService
      .query()
      .subscribe((res: HttpResponse<IBeneficio[]>) => (this.beneficios = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(anexo: IAnexo) {
    this.editForm.patchValue({
      id: anexo.id,
      descricao: anexo.descricao,
      tamanho: anexo.tamanho,
      mimeType: anexo.mimeType,
      conteudoAnexo: anexo.conteudoAnexo,
      categoria: anexo.categoria,
      beneficio: anexo.beneficio
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const anexo = this.createFromForm();
    if (anexo.id !== undefined) {
      this.subscribeToSaveResponse(this.anexoService.update(anexo));
    } else {
      this.subscribeToSaveResponse(this.anexoService.create(anexo));
    }
  }

  private createFromForm(): IAnexo {
    return {
      ...new Anexo(),
      id: this.editForm.get(['id']).value,
      descricao: this.editForm.get(['descricao']).value,
      tamanho: this.editForm.get(['tamanho']).value,
      mimeType: this.editForm.get(['mimeType']).value,
      conteudoAnexo: this.editForm.get(['conteudoAnexo']).value,
      categoria: this.editForm.get(['categoria']).value,
      beneficio: this.editForm.get(['beneficio']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAnexo>>) {
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

  trackConteudoAnexoById(index: number, item: IConteudoAnexo) {
    return item.id;
  }

  trackCategoriaAnexoById(index: number, item: ICategoriaAnexo) {
    return item.id;
  }

  trackBeneficioById(index: number, item: IBeneficio) {
    return item.id;
  }
}
