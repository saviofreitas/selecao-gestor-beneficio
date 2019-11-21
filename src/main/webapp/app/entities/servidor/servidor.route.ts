import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Servidor } from 'app/shared/model/servidor.model';
import { ServidorService } from './servidor.service';
import { ServidorComponent } from './servidor.component';
import { ServidorDetailComponent } from './servidor-detail.component';
import { ServidorUpdateComponent } from './servidor-update.component';
import { IServidor } from 'app/shared/model/servidor.model';

@Injectable({ providedIn: 'root' })
export class ServidorResolve implements Resolve<IServidor> {
  constructor(private service: ServidorService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IServidor> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((servidor: HttpResponse<Servidor>) => servidor.body));
    }
    return of(new Servidor());
  }
}

export const servidorRoute: Routes = [
  {
    path: '',
    component: ServidorComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'gestorBeneficioApp.servidor.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ServidorDetailComponent,
    resolve: {
      servidor: ServidorResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'gestorBeneficioApp.servidor.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ServidorUpdateComponent,
    resolve: {
      servidor: ServidorResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'gestorBeneficioApp.servidor.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ServidorUpdateComponent,
    resolve: {
      servidor: ServidorResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'gestorBeneficioApp.servidor.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
