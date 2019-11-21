import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { MovimentacaoBeneficio } from 'app/shared/model/movimentacao-beneficio.model';
import { MovimentacaoBeneficioService } from './movimentacao-beneficio.service';
import { MovimentacaoBeneficioComponent } from './movimentacao-beneficio.component';
import { MovimentacaoBeneficioDetailComponent } from './movimentacao-beneficio-detail.component';
import { MovimentacaoBeneficioUpdateComponent } from './movimentacao-beneficio-update.component';
import { IMovimentacaoBeneficio } from 'app/shared/model/movimentacao-beneficio.model';

@Injectable({ providedIn: 'root' })
export class MovimentacaoBeneficioResolve implements Resolve<IMovimentacaoBeneficio> {
  constructor(private service: MovimentacaoBeneficioService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMovimentacaoBeneficio> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((movimentacaoBeneficio: HttpResponse<MovimentacaoBeneficio>) => movimentacaoBeneficio.body));
    }
    return of(new MovimentacaoBeneficio());
  }
}

export const movimentacaoBeneficioRoute: Routes = [
  {
    path: '',
    component: MovimentacaoBeneficioComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'gestorBeneficioApp.movimentacaoBeneficio.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MovimentacaoBeneficioDetailComponent,
    resolve: {
      movimentacaoBeneficio: MovimentacaoBeneficioResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'gestorBeneficioApp.movimentacaoBeneficio.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MovimentacaoBeneficioUpdateComponent,
    resolve: {
      movimentacaoBeneficio: MovimentacaoBeneficioResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'gestorBeneficioApp.movimentacaoBeneficio.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MovimentacaoBeneficioUpdateComponent,
    resolve: {
      movimentacaoBeneficio: MovimentacaoBeneficioResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'gestorBeneficioApp.movimentacaoBeneficio.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
