export interface IDomande {
  id?: number;
  descrizione?: string | null;
}

export const defaultValue: Readonly<IDomande> = {};
