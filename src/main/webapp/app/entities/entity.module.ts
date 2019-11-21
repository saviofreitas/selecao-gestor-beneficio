import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'orgao',
        loadChildren: () => import('./orgao/orgao.module').then(m => m.GestorBeneficioOrgaoModule)
      },
      {
        path: 'servidor',
        loadChildren: () => import('./servidor/servidor.module').then(m => m.GestorBeneficioServidorModule)
      },
      {
        path: 'beneficio',
        loadChildren: () => import('./beneficio/beneficio.module').then(m => m.GestorBeneficioBeneficioModule)
      },
      {
        path: 'categoria-anexo',
        loadChildren: () => import('./categoria-anexo/categoria-anexo.module').then(m => m.GestorBeneficioCategoriaAnexoModule)
      },
      {
        path: 'anexo',
        loadChildren: () => import('./anexo/anexo.module').then(m => m.GestorBeneficioAnexoModule)
      },
      {
        path: 'setor',
        loadChildren: () => import('./setor/setor.module').then(m => m.GestorBeneficioSetorModule)
      },
      {
        path: 'movimentacao-beneficio',
        loadChildren: () =>
          import('./movimentacao-beneficio/movimentacao-beneficio.module').then(m => m.GestorBeneficioMovimentacaoBeneficioModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class GestorBeneficioEntityModule {}
