import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISetor } from 'app/shared/model/setor.model';

@Component({
  selector: 'jhi-setor-detail',
  templateUrl: './setor-detail.component.html'
})
export class SetorDetailComponent implements OnInit {
  setor: ISetor;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ setor }) => {
      this.setor = setor;
    });
  }

  previousState() {
    window.history.back();
  }
}
