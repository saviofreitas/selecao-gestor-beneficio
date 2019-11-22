import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IConteudoAnexo, ConteudoAnexo } from 'app/shared/model/conteudo-anexo.model';
import { ConteudoAnexoService } from './conteudo-anexo.service';
import { IAnexo } from 'app/shared/model/anexo.model';
import { AnexoService } from 'app/entities/anexo/anexo.service';

@Component({
  selector: 'jhi-conteudo-anexo-update',
  templateUrl: './conteudo-anexo-update.component.html'
})
export class ConteudoAnexoUpdateComponent implements OnInit {
  isSaving: boolean;

  anexos: IAnexo[];

  editForm = this.fb.group({
    id: [],
    data: [null, [Validators.required]],
    dataContentType: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected conteudoAnexoService: ConteudoAnexoService,
    protected anexoService: AnexoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ conteudoAnexo }) => {
      this.updateForm(conteudoAnexo);
    });
    this.anexoService
      .query()
      .subscribe((res: HttpResponse<IAnexo[]>) => (this.anexos = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(conteudoAnexo: IConteudoAnexo) {
    this.editForm.patchValue({
      id: conteudoAnexo.id,
      data: conteudoAnexo.data,
      dataContentType: conteudoAnexo.dataContentType
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  setFileData(event, field: string, isImage) {
    return new Promise((resolve, reject) => {
      if (event && event.target && event.target.files && event.target.files[0]) {
        const file: File = event.target.files[0];
        if (isImage && !file.type.startsWith('image/')) {
          reject(`File was expected to be an image but was found to be ${file.type}`);
        } else {
          const filedContentType: string = field + 'ContentType';
          this.dataUtils.toBase64(file, base64Data => {
            this.editForm.patchValue({
              [field]: base64Data,
              [filedContentType]: file.type
            });
          });
        }
      } else {
        reject(`Base64 data was not set as file could not be extracted from passed parameter: ${event}`);
      }
    }).then(
      // eslint-disable-next-line no-console
      () => console.log('blob added'), // success
      this.onError
    );
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const conteudoAnexo = this.createFromForm();
    if (conteudoAnexo.id !== undefined) {
      this.subscribeToSaveResponse(this.conteudoAnexoService.update(conteudoAnexo));
    } else {
      this.subscribeToSaveResponse(this.conteudoAnexoService.create(conteudoAnexo));
    }
  }

  private createFromForm(): IConteudoAnexo {
    return {
      ...new ConteudoAnexo(),
      id: this.editForm.get(['id']).value,
      dataContentType: this.editForm.get(['dataContentType']).value,
      data: this.editForm.get(['data']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IConteudoAnexo>>) {
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

  trackAnexoById(index: number, item: IAnexo) {
    return item.id;
  }
}
