import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMovimentacaoBeneficio } from 'app/shared/model/movimentacao-beneficio.model';

@Component({
  selector: 'jhi-movimentacao-beneficio-detail',
  templateUrl: './movimentacao-beneficio-detail.component.html'
})
export class MovimentacaoBeneficioDetailComponent implements OnInit {
  movimentacaoBeneficio: IMovimentacaoBeneficio;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ movimentacaoBeneficio }) => {
      this.movimentacaoBeneficio = movimentacaoBeneficio;
    });
  }

  previousState() {
    window.history.back();
  }
}
