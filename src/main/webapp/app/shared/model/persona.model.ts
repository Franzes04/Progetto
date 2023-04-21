import dayjs from 'dayjs';

export interface IPersona {
  id?: number;
  nome?: string | null;
  cognome?: string | null;
  dataNascita?: string | null;
  email?: string | null;
  citta?: string | null;
  eta?: number | null;
  telefono?: string | null;
}

export const defaultValue: Readonly<IPersona> = {};
