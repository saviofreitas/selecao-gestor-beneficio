import { ICategoriaAnexo } from 'app/shared/model/categoria-anexo.model';
import { IBeneficio } from 'app/shared/model/beneficio.model';

export interface IAnexo {
  id?: number;
  descricao?: string;
  caminho?: string;
  categoria?: ICategoriaAnexo;
  beneficio?: IBeneficio;
}

export class Anexo implements IAnexo {
  constructor(
    public id?: number,
    public descricao?: string,
    public caminho?: string,
    public categoria?: ICategoriaAnexo,
    public beneficio?: IBeneficio
  ) {}
}
