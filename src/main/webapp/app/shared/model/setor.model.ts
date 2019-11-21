export interface ISetor {
  id?: number;
  descricao?: string;
}

export class Setor implements ISetor {
  constructor(public id?: number, public descricao?: string) {}
}
