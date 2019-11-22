import * as FileSaver from 'file-saver';

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAnexo } from 'app/shared/model/anexo.model';
import { AnexoService } from './anexo.service';

@Component({
  selector: 'jhi-anexo-detail',
  templateUrl: './anexo-detail.component.html'
})
export class AnexoDetailComponent implements OnInit {
  anexo: IAnexo;

  constructor(protected activatedRoute: ActivatedRoute, protected anexoService: AnexoService) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ anexo }) => {
      this.anexo = anexo;
    });
  }

  previousState() {
    window.history.back();
  }

  downloadAnexo(anexo: IAnexo) {
    this.anexoService.download(anexo.id).subscribe(file => {
      FileSaver.saveAs(file, anexo.descricao);
    });
  }
}
