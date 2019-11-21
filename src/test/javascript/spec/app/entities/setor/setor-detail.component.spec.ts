import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestorBeneficioTestModule } from '../../../test.module';
import { SetorDetailComponent } from 'app/entities/setor/setor-detail.component';
import { Setor } from 'app/shared/model/setor.model';

describe('Component Tests', () => {
  describe('Setor Management Detail Component', () => {
    let comp: SetorDetailComponent;
    let fixture: ComponentFixture<SetorDetailComponent>;
    const route = ({ data: of({ setor: new Setor(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestorBeneficioTestModule],
        declarations: [SetorDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SetorDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SetorDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.setor).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
