import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Setor } from 'app/shared/model/setor.model';
import { SetorService } from './setor.service';
import { SetorComponent } from './setor.component';
import { SetorDetailComponent } from './setor-detail.component';
import { SetorUpdateComponent } from './setor-update.component';
import { ISetor } from 'app/shared/model/setor.model';

@Injectable({ providedIn: 'root' })
export class SetorResolve implements Resolve<ISetor> {
  constructor(private service: SetorService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISetor> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((setor: HttpResponse<Setor>) => setor.body));
    }
    return of(new Setor());
  }
}

export const setorRoute: Routes = [
  {
    path: '',
    component: SetorComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'gestorBeneficioApp.setor.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SetorDetailComponent,
    resolve: {
      setor: SetorResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'gestorBeneficioApp.setor.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SetorUpdateComponent,
    resolve: {
      setor: SetorResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'gestorBeneficioApp.setor.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SetorUpdateComponent,
    resolve: {
      setor: SetorResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'gestorBeneficioApp.setor.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
