import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Orgao } from 'app/shared/model/orgao.model';
import { OrgaoService } from './orgao.service';
import { OrgaoComponent } from './orgao.component';
import { OrgaoDetailComponent } from './orgao-detail.component';
import { OrgaoUpdateComponent } from './orgao-update.component';
import { IOrgao } from 'app/shared/model/orgao.model';

@Injectable({ providedIn: 'root' })
export class OrgaoResolve implements Resolve<IOrgao> {
  constructor(private service: OrgaoService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOrgao> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((orgao: HttpResponse<Orgao>) => orgao.body));
    }
    return of(new Orgao());
  }
}

export const orgaoRoute: Routes = [
  {
    path: '',
    component: OrgaoComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'gestorBeneficioApp.orgao.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: OrgaoDetailComponent,
    resolve: {
      orgao: OrgaoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'gestorBeneficioApp.orgao.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: OrgaoUpdateComponent,
    resolve: {
      orgao: OrgaoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'gestorBeneficioApp.orgao.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: OrgaoUpdateComponent,
    resolve: {
      orgao: OrgaoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'gestorBeneficioApp.orgao.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
