import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IConteudoAnexo } from 'app/shared/model/conteudo-anexo.model';

@Component({
  selector: 'jhi-conteudo-anexo-detail',
  templateUrl: './conteudo-anexo-detail.component.html'
})
export class ConteudoAnexoDetailComponent implements OnInit {
  conteudoAnexo: IConteudoAnexo;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ conteudoAnexo }) => {
      this.conteudoAnexo = conteudoAnexo;
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }
  previousState() {
    window.history.back();
  }
}
