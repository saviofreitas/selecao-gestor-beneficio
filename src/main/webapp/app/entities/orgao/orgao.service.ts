import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOrgao } from 'app/shared/model/orgao.model';

type EntityResponseType = HttpResponse<IOrgao>;
type EntityArrayResponseType = HttpResponse<IOrgao[]>;

@Injectable({ providedIn: 'root' })
export class OrgaoService {
  public resourceUrl = SERVER_API_URL + 'api/orgaos';

  constructor(protected http: HttpClient) {}

  create(orgao: IOrgao): Observable<EntityResponseType> {
    return this.http.post<IOrgao>(this.resourceUrl, orgao, { observe: 'response' });
  }

  update(orgao: IOrgao): Observable<EntityResponseType> {
    return this.http.put<IOrgao>(this.resourceUrl, orgao, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOrgao>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOrgao[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
