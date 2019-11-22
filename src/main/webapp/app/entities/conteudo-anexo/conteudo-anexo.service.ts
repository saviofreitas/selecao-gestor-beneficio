import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IConteudoAnexo } from 'app/shared/model/conteudo-anexo.model';

type EntityResponseType = HttpResponse<IConteudoAnexo>;
type EntityArrayResponseType = HttpResponse<IConteudoAnexo[]>;

@Injectable({ providedIn: 'root' })
export class ConteudoAnexoService {
  public resourceUrl = SERVER_API_URL + 'api/conteudo-anexos';

  constructor(protected http: HttpClient) {}

  create(conteudoAnexo: IConteudoAnexo): Observable<EntityResponseType> {
    return this.http.post<IConteudoAnexo>(this.resourceUrl, conteudoAnexo, { observe: 'response' });
  }

  update(conteudoAnexo: IConteudoAnexo): Observable<EntityResponseType> {
    return this.http.put<IConteudoAnexo>(this.resourceUrl, conteudoAnexo, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IConteudoAnexo>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IConteudoAnexo[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
