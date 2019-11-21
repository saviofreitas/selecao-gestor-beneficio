import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Beneficio } from 'app/shared/model/beneficio.model';
import { BeneficioService } from './beneficio.service';
import { BeneficioComponent } from './beneficio.component';
import { BeneficioDetailComponent } from './beneficio-detail.component';
import { BeneficioUpdateComponent } from './beneficio-update.component';
import { IBeneficio } from 'app/shared/model/beneficio.model';

@Injectable({ providedIn: 'root' })
export class BeneficioResolve implements Resolve<IBeneficio> {
  constructor(private service: BeneficioService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBeneficio> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((beneficio: HttpResponse<Beneficio>) => beneficio.body));
    }
    return of(new Beneficio());
  }
}

export const beneficioRoute: Routes = [
  {
    path: '',
    component: BeneficioComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'gestorBeneficioApp.beneficio.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: BeneficioDetailComponent,
    resolve: {
      beneficio: BeneficioResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'gestorBeneficioApp.beneficio.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: BeneficioUpdateComponent,
    resolve: {
      beneficio: BeneficioResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'gestorBeneficioApp.beneficio.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BeneficioUpdateComponent,
    resolve: {
      beneficio: BeneficioResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'gestorBeneficioApp.beneficio.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
