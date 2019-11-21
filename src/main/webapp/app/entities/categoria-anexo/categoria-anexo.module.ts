import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestorBeneficioSharedModule } from 'app/shared/shared.module';
import { CategoriaAnexoComponent } from './categoria-anexo.component';
import { CategoriaAnexoDetailComponent } from './categoria-anexo-detail.component';
import { CategoriaAnexoUpdateComponent } from './categoria-anexo-update.component';
import { CategoriaAnexoDeleteDialogComponent } from './categoria-anexo-delete-dialog.component';
import { categoriaAnexoRoute } from './categoria-anexo.route';

@NgModule({
  imports: [GestorBeneficioSharedModule, RouterModule.forChild(categoriaAnexoRoute)],
  declarations: [
    CategoriaAnexoComponent,
    CategoriaAnexoDetailComponent,
    CategoriaAnexoUpdateComponent,
    CategoriaAnexoDeleteDialogComponent
  ],
  entryComponents: [CategoriaAnexoDeleteDialogComponent]
})
export class GestorBeneficioCategoriaAnexoModule {}
