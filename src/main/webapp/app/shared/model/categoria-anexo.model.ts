export interface ICategoriaAnexo {
  id?: number;
  descricao?: string;
}

export class CategoriaAnexo implements ICategoriaAnexo {
  constructor(public id?: number, public descricao?: string) {}
}
