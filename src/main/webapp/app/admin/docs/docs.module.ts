import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { GestorBeneficioSharedModule } from 'app/shared/shared.module';

import { JhiDocsComponent } from './docs.component';

import { docsRoute } from './docs.route';

@NgModule({
  imports: [GestorBeneficioSharedModule, RouterModule.forChild([docsRoute])],
  declarations: [JhiDocsComponent]
})
export class DocsModule {}
