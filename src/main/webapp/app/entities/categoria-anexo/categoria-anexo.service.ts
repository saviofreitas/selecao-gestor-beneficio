import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICategoriaAnexo } from 'app/shared/model/categoria-anexo.model';

type EntityResponseType = HttpResponse<ICategoriaAnexo>;
type EntityArrayResponseType = HttpResponse<ICategoriaAnexo[]>;

@Injectable({ providedIn: 'root' })
export class CategoriaAnexoService {
  public resourceUrl = SERVER_API_URL + 'api/categoria-anexos';

  constructor(protected http: HttpClient) {}

  create(categoriaAnexo: ICategoriaAnexo): Observable<EntityResponseType> {
    return this.http.post<ICategoriaAnexo>(this.resourceUrl, categoriaAnexo, { observe: 'response' });
  }

  update(categoriaAnexo: ICategoriaAnexo): Observable<EntityResponseType> {
    return this.http.put<ICategoriaAnexo>(this.resourceUrl, categoriaAnexo, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICategoriaAnexo>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICategoriaAnexo[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
