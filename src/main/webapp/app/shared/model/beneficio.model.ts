import { Moment } from 'moment';
import { IAnexo } from 'app/shared/model/anexo.model';
import { IServidor } from 'app/shared/model/servidor.model';
import { SituacaoBeneficio } from 'app/shared/model/enumerations/situacao-beneficio.model';

export interface IBeneficio {
  id?: number;
  descricao?: string;
  dataCriacao?: Moment;
  dataUltimaMovimentacao?: Moment;
  situacao?: SituacaoBeneficio;
  anexos?: IAnexo[];
  servidor?: IServidor;
}

export class Beneficio implements IBeneficio {
  constructor(
    public id?: number,
    public descricao?: string,
    public dataCriacao?: Moment,
    public dataUltimaMovimentacao?: Moment,
    public situacao?: SituacaoBeneficio,
    public anexos?: IAnexo[],
    public servidor?: IServidor
  ) {}
}
