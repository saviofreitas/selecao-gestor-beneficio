import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { BeneficioService } from 'app/entities/beneficio/beneficio.service';
import { IBeneficio, Beneficio } from 'app/shared/model/beneficio.model';
import { SituacaoBeneficio } from 'app/shared/model/enumerations/situacao-beneficio.model';

describe('Service Tests', () => {
  describe('Beneficio Service', () => {
    let injector: TestBed;
    let service: BeneficioService;
    let httpMock: HttpTestingController;
    let elemDefault: IBeneficio;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(BeneficioService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Beneficio(0, currentDate, currentDate, SituacaoBeneficio.PENDENTE);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dataCriacao: currentDate.format(DATE_TIME_FORMAT),
            dataUltimaMovimentacao: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a Beneficio', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dataCriacao: currentDate.format(DATE_TIME_FORMAT),
            dataUltimaMovimentacao: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            dataCriacao: currentDate,
            dataUltimaMovimentacao: currentDate
          },
          returnedFromService
        );
        service
          .create(new Beneficio(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Beneficio', () => {
        const returnedFromService = Object.assign(
          {
            dataCriacao: currentDate.format(DATE_TIME_FORMAT),
            dataUltimaMovimentacao: currentDate.format(DATE_TIME_FORMAT),
            situacao: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataCriacao: currentDate,
            dataUltimaMovimentacao: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of Beneficio', () => {
        const returnedFromService = Object.assign(
          {
            dataCriacao: currentDate.format(DATE_TIME_FORMAT),
            dataUltimaMovimentacao: currentDate.format(DATE_TIME_FORMAT),
            situacao: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            dataCriacao: currentDate,
            dataUltimaMovimentacao: currentDate
          },
          returnedFromService
        );
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Beneficio', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
