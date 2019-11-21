import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISetor } from 'app/shared/model/setor.model';

type EntityResponseType = HttpResponse<ISetor>;
type EntityArrayResponseType = HttpResponse<ISetor[]>;

@Injectable({ providedIn: 'root' })
export class SetorService {
  public resourceUrl = SERVER_API_URL + 'api/setors';

  constructor(protected http: HttpClient) {}

  create(setor: ISetor): Observable<EntityResponseType> {
    return this.http.post<ISetor>(this.resourceUrl, setor, { observe: 'response' });
  }

  update(setor: ISetor): Observable<EntityResponseType> {
    return this.http.put<ISetor>(this.resourceUrl, setor, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISetor>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISetor[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
