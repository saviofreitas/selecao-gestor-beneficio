import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestorBeneficioSharedModule } from 'app/shared/shared.module';
import { ConteudoAnexoComponent } from './conteudo-anexo.component';
import { ConteudoAnexoDetailComponent } from './conteudo-anexo-detail.component';
import { ConteudoAnexoUpdateComponent } from './conteudo-anexo-update.component';
import { ConteudoAnexoDeleteDialogComponent } from './conteudo-anexo-delete-dialog.component';
import { conteudoAnexoRoute } from './conteudo-anexo.route';

@NgModule({
  imports: [GestorBeneficioSharedModule, RouterModule.forChild(conteudoAnexoRoute)],
  declarations: [ConteudoAnexoComponent, ConteudoAnexoDetailComponent, ConteudoAnexoUpdateComponent, ConteudoAnexoDeleteDialogComponent],
  entryComponents: [ConteudoAnexoDeleteDialogComponent]
})
export class GestorBeneficioConteudoAnexoModule {}
