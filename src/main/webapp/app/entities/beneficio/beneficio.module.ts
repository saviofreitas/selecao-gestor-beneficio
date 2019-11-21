import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestorBeneficioSharedModule } from 'app/shared/shared.module';
import { BeneficioComponent } from './beneficio.component';
import { BeneficioDetailComponent } from './beneficio-detail.component';
import { BeneficioUpdateComponent } from './beneficio-update.component';
import { BeneficioDeleteDialogComponent } from './beneficio-delete-dialog.component';
import { beneficioRoute } from './beneficio.route';

@NgModule({
  imports: [GestorBeneficioSharedModule, RouterModule.forChild(beneficioRoute)],
  declarations: [BeneficioComponent, BeneficioDetailComponent, BeneficioUpdateComponent, BeneficioDeleteDialogComponent],
  entryComponents: [BeneficioDeleteDialogComponent]
})
export class GestorBeneficioBeneficioModule {}
