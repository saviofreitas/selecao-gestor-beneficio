import { Moment } from 'moment';
import { IBeneficio } from 'app/shared/model/beneficio.model';
import { ISetor } from 'app/shared/model/setor.model';

export interface IMovimentacaoBeneficio {
  id?: number;
  dataTramitacao?: Moment;
  responsavel?: string;
  beneficio?: IBeneficio;
  setorOrigem?: ISetor;
  setorDestino?: ISetor;
}

export class MovimentacaoBeneficio implements IMovimentacaoBeneficio {
  constructor(
    public id?: number,
    public dataTramitacao?: Moment,
    public responsavel?: string,
    public beneficio?: IBeneficio,
    public setorOrigem?: ISetor,
    public setorDestino?: ISetor
  ) {}
}
