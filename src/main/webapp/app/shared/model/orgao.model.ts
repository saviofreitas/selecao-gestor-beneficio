export interface IOrgao {
  id?: number;
  descricao?: string;
}

export class Orgao implements IOrgao {
  constructor(public id?: number, public descricao?: string) {}
}
