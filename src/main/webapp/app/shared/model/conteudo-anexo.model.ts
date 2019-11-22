import { IAnexo } from 'app/shared/model/anexo.model';

export interface IConteudoAnexo {
  id?: number;
  dataContentType?: string;
  data?: any;
  anexo?: IAnexo;
}

export class ConteudoAnexo implements IConteudoAnexo {
  constructor(public id?: number, public dataContentType?: string, public data?: any, public anexo?: IAnexo) {}
}
