import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestorBeneficioSharedModule } from 'app/shared/shared.module';
import { MovimentacaoBeneficioComponent } from './movimentacao-beneficio.component';
import { MovimentacaoBeneficioDetailComponent } from './movimentacao-beneficio-detail.component';
import { MovimentacaoBeneficioUpdateComponent } from './movimentacao-beneficio-update.component';
import { MovimentacaoBeneficioDeleteDialogComponent } from './movimentacao-beneficio-delete-dialog.component';
import { movimentacaoBeneficioRoute } from './movimentacao-beneficio.route';

@NgModule({
  imports: [GestorBeneficioSharedModule, RouterModule.forChild(movimentacaoBeneficioRoute)],
  declarations: [
    MovimentacaoBeneficioComponent,
    MovimentacaoBeneficioDetailComponent,
    MovimentacaoBeneficioUpdateComponent,
    MovimentacaoBeneficioDeleteDialogComponent
  ],
  entryComponents: [MovimentacaoBeneficioDeleteDialogComponent]
})
export class GestorBeneficioMovimentacaoBeneficioModule {}
