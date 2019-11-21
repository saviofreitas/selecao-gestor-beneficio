import { IBeneficio } from 'app/shared/model/beneficio.model';
import { IOrgao } from 'app/shared/model/orgao.model';

export interface IServidor {
  id?: number;
  nome?: string;
  cpf?: string;
  matricula?: number;
  beneficios?: IBeneficio[];
  orgao?: IOrgao;
}

export class Servidor implements IServidor {
  constructor(
    public id?: number,
    public nome?: string,
    public cpf?: string,
    public matricula?: number,
    public beneficios?: IBeneficio[],
    public orgao?: IOrgao
  ) {}
}
