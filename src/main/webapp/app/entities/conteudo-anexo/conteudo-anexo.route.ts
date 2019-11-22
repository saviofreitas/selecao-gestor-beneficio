import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ConteudoAnexo } from 'app/shared/model/conteudo-anexo.model';
import { ConteudoAnexoService } from './conteudo-anexo.service';
import { ConteudoAnexoComponent } from './conteudo-anexo.component';
import { ConteudoAnexoDetailComponent } from './conteudo-anexo-detail.component';
import { ConteudoAnexoUpdateComponent } from './conteudo-anexo-update.component';
import { IConteudoAnexo } from 'app/shared/model/conteudo-anexo.model';

@Injectable({ providedIn: 'root' })
export class ConteudoAnexoResolve implements Resolve<IConteudoAnexo> {
  constructor(private service: ConteudoAnexoService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IConteudoAnexo> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((conteudoAnexo: HttpResponse<ConteudoAnexo>) => conteudoAnexo.body));
    }
    return of(new ConteudoAnexo());
  }
}

export const conteudoAnexoRoute: Routes = [
  {
    path: '',
    component: ConteudoAnexoComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'gestorBeneficioApp.conteudoAnexo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ConteudoAnexoDetailComponent,
    resolve: {
      conteudoAnexo: ConteudoAnexoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'gestorBeneficioApp.conteudoAnexo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ConteudoAnexoUpdateComponent,
    resolve: {
      conteudoAnexo: ConteudoAnexoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'gestorBeneficioApp.conteudoAnexo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ConteudoAnexoUpdateComponent,
    resolve: {
      conteudoAnexo: ConteudoAnexoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'gestorBeneficioApp.conteudoAnexo.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
