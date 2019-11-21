import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CategoriaAnexo } from 'app/shared/model/categoria-anexo.model';
import { CategoriaAnexoService } from './categoria-anexo.service';
import { CategoriaAnexoComponent } from './categoria-anexo.component';
import { CategoriaAnexoDetailComponent } from './categoria-anexo-detail.component';
import { CategoriaAnexoUpdateComponent } from './categoria-anexo-update.component';
import { ICategoriaAnexo } from 'app/shared/model/categoria-anexo.model';

@Injectable({ providedIn: 'root' })
export class CategoriaAnexoResolve implements Resolve<ICategoriaAnexo> {
  constructor(private service: CategoriaAnexoService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICategoriaAnexo> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((categoriaAnexo: HttpResponse<CategoriaAnexo>) => categoriaAnexo.body));
    }
    return of(new CategoriaAnexo());
  }
}

export const categoriaAnexoRoute: Routes = [
  {
    path: '',
    component: CategoriaAnexoComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'gestorBeneficioApp.categoriaAnexo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CategoriaAnexoDetailComponent,
    resolve: {
      categoriaAnexo: CategoriaAnexoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'gestorBeneficioApp.categoriaAnexo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CategoriaAnexoUpdateComponent,
    resolve: {
      categoriaAnexo: CategoriaAnexoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'gestorBeneficioApp.categoriaAnexo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CategoriaAnexoUpdateComponent,
    resolve: {
      categoriaAnexo: CategoriaAnexoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'gestorBeneficioApp.categoriaAnexo.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
