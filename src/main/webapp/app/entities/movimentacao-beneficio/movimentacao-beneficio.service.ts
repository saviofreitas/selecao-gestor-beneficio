import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMovimentacaoBeneficio } from 'app/shared/model/movimentacao-beneficio.model';

type EntityResponseType = HttpResponse<IMovimentacaoBeneficio>;
type EntityArrayResponseType = HttpResponse<IMovimentacaoBeneficio[]>;

@Injectable({ providedIn: 'root' })
export class MovimentacaoBeneficioService {
  public resourceUrl = SERVER_API_URL + 'api/movimentacao-beneficios';

  constructor(protected http: HttpClient) {}

  create(movimentacaoBeneficio: IMovimentacaoBeneficio): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(movimentacaoBeneficio);
    return this.http
      .post<IMovimentacaoBeneficio>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(movimentacaoBeneficio: IMovimentacaoBeneficio): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(movimentacaoBeneficio);
    return this.http
      .put<IMovimentacaoBeneficio>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IMovimentacaoBeneficio>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IMovimentacaoBeneficio[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(movimentacaoBeneficio: IMovimentacaoBeneficio): IMovimentacaoBeneficio {
    const copy: IMovimentacaoBeneficio = Object.assign({}, movimentacaoBeneficio, {
      dataTramitacao:
        movimentacaoBeneficio.dataTramitacao != null && movimentacaoBeneficio.dataTramitacao.isValid()
          ? movimentacaoBeneficio.dataTramitacao.toJSON()
          : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dataTramitacao = res.body.dataTramitacao != null ? moment(res.body.dataTramitacao) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((movimentacaoBeneficio: IMovimentacaoBeneficio) => {
        movimentacaoBeneficio.dataTramitacao =
          movimentacaoBeneficio.dataTramitacao != null ? moment(movimentacaoBeneficio.dataTramitacao) : null;
      });
    }
    return res;
  }
}
