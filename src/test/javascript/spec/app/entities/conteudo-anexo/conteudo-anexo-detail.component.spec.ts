import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestorBeneficioTestModule } from '../../../test.module';
import { ConteudoAnexoDetailComponent } from 'app/entities/conteudo-anexo/conteudo-anexo-detail.component';
import { ConteudoAnexo } from 'app/shared/model/conteudo-anexo.model';

describe('Component Tests', () => {
  describe('ConteudoAnexo Management Detail Component', () => {
    let comp: ConteudoAnexoDetailComponent;
    let fixture: ComponentFixture<ConteudoAnexoDetailComponent>;
    const route = ({ data: of({ conteudoAnexo: new ConteudoAnexo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestorBeneficioTestModule],
        declarations: [ConteudoAnexoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ConteudoAnexoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ConteudoAnexoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.conteudoAnexo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
