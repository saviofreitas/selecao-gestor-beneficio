import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestorBeneficioSharedModule } from 'app/shared/shared.module';
import { OrgaoComponent } from './orgao.component';
import { OrgaoDetailComponent } from './orgao-detail.component';
import { OrgaoUpdateComponent } from './orgao-update.component';
import { OrgaoDeleteDialogComponent } from './orgao-delete-dialog.component';
import { orgaoRoute } from './orgao.route';

@NgModule({
  imports: [GestorBeneficioSharedModule, RouterModule.forChild(orgaoRoute)],
  declarations: [OrgaoComponent, OrgaoDetailComponent, OrgaoUpdateComponent, OrgaoDeleteDialogComponent],
  entryComponents: [OrgaoDeleteDialogComponent]
})
export class GestorBeneficioOrgaoModule {}
