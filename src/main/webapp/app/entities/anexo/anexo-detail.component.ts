import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAnexo } from 'app/shared/model/anexo.model';

@Component({
  selector: 'jhi-anexo-detail',
  templateUrl: './anexo-detail.component.html'
})
export class AnexoDetailComponent implements OnInit {
  anexo: IAnexo;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ anexo }) => {
      this.anexo = anexo;
    });
  }

  previousState() {
    window.history.back();
  }
}
