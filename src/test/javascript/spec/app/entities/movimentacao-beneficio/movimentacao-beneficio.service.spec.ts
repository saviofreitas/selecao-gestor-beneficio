import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { MovimentacaoBeneficioService } from 'app/entities/movimentacao-beneficio/movimentacao-beneficio.service';
import { IMovimentacaoBeneficio, MovimentacaoBeneficio } from 'app/shared/model/movimentacao-beneficio.model';

describe('Service Tests', () => {
  describe('MovimentacaoBeneficio Service', () => {
    let injector: TestBed;
    let service: MovimentacaoBeneficioService;
    let httpMock: HttpTestingController;
    let elemDefault: IMovimentacaoBeneficio;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MovimentacaoBeneficioService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new MovimentacaoBeneficio(0, currentDate, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dataTramitacao: currentDate.format(DATE_TIME_FORMAT)
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

      it('should create a MovimentacaoBeneficio', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dataTramitacao: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            dataTramitacao: currentDate
          },
          returnedFromService
        );
        service
          .create(new MovimentacaoBeneficio(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MovimentacaoBeneficio', () => {
        const returnedFromService = Object.assign(
          {
            dataTramitacao: currentDate.format(DATE_TIME_FORMAT),
            responsavel: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dataTramitacao: currentDate
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

      it('should return a list of MovimentacaoBeneficio', () => {
        const returnedFromService = Object.assign(
          {
            dataTramitacao: currentDate.format(DATE_TIME_FORMAT),
            responsavel: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            dataTramitacao: currentDate
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

      it('should delete a MovimentacaoBeneficio', () => {
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
