import { IConteudoAnexo } from 'app/shared/model/conteudo-anexo.model';
import { ICategoriaAnexo } from 'app/shared/model/categoria-anexo.model';
import { IBeneficio } from 'app/shared/model/beneficio.model';

export interface IAnexo {
  id?: number;
  descricao?: string;
  tamanho?: number;
  mimeType?: string;
  conteudoAnexo?: IConteudoAnexo;
  categoria?: ICategoriaAnexo;
  beneficio?: IBeneficio;
}

export class Anexo implements IAnexo {
  constructor(
    public id?: number,
    public descricao?: string,
    public tamanho?: number,
    public mimeType?: string,
    public conteudoAnexo?: IConteudoAnexo,
    public categoria?: ICategoriaAnexo,
    public beneficio?: IBeneficio
  ) {}
}
