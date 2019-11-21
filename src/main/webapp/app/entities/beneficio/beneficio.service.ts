import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBeneficio } from 'app/shared/model/beneficio.model';

type EntityResponseType = HttpResponse<IBeneficio>;
type EntityArrayResponseType = HttpResponse<IBeneficio[]>;

@Injectable({ providedIn: 'root' })
export class BeneficioService {
  public resourceUrl = SERVER_API_URL + 'api/beneficios';

  constructor(protected http: HttpClient) {}

  create(beneficio: IBeneficio): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(beneficio);
    return this.http
      .post<IBeneficio>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(beneficio: IBeneficio): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(beneficio);
    return this.http
      .put<IBeneficio>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBeneficio>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBeneficio[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(beneficio: IBeneficio): IBeneficio {
    const copy: IBeneficio = Object.assign({}, beneficio, {
      dataCriacao: beneficio.dataCriacao != null && beneficio.dataCriacao.isValid() ? beneficio.dataCriacao.toJSON() : null,
      dataUltimaMovimentacao:
        beneficio.dataUltimaMovimentacao != null && beneficio.dataUltimaMovimentacao.isValid()
          ? beneficio.dataUltimaMovimentacao.toJSON()
          : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dataCriacao = res.body.dataCriacao != null ? moment(res.body.dataCriacao) : null;
      res.body.dataUltimaMovimentacao = res.body.dataUltimaMovimentacao != null ? moment(res.body.dataUltimaMovimentacao) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((beneficio: IBeneficio) => {
        beneficio.dataCriacao = beneficio.dataCriacao != null ? moment(beneficio.dataCriacao) : null;
        beneficio.dataUltimaMovimentacao = beneficio.dataUltimaMovimentacao != null ? moment(beneficio.dataUltimaMovimentacao) : null;
      });
    }
    return res;
  }
}
