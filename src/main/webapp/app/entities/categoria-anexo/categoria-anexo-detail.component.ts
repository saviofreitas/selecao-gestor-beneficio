import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICategoriaAnexo } from 'app/shared/model/categoria-anexo.model';

@Component({
  selector: 'jhi-categoria-anexo-detail',
  templateUrl: './categoria-anexo-detail.component.html'
})
export class CategoriaAnexoDetailComponent implements OnInit {
  categoriaAnexo: ICategoriaAnexo;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ categoriaAnexo }) => {
      this.categoriaAnexo = categoriaAnexo;
    });
  }

  previousState() {
    window.history.back();
  }
}
