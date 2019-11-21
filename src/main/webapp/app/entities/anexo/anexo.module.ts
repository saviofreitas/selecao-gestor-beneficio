import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestorBeneficioSharedModule } from 'app/shared/shared.module';
import { AnexoComponent } from './anexo.component';
import { AnexoDetailComponent } from './anexo-detail.component';
import { AnexoUpdateComponent } from './anexo-update.component';
import { AnexoDeleteDialogComponent } from './anexo-delete-dialog.component';
import { anexoRoute } from './anexo.route';

@NgModule({
  imports: [GestorBeneficioSharedModule, RouterModule.forChild(anexoRoute)],
  declarations: [AnexoComponent, AnexoDetailComponent, AnexoUpdateComponent, AnexoDeleteDialogComponent],
  entryComponents: [AnexoDeleteDialogComponent]
})
export class GestorBeneficioAnexoModule {}
