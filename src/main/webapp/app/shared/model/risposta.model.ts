import { IDomande } from 'app/shared/model/domande.model';
import { IPersona } from 'app/shared/model/persona.model';

export interface IRisposta {
  id?: number;
  risposte?: string | null;
  domande?: IDomande | null;
  persona?: IPersona | null;
}

export const defaultValue: Readonly<IRisposta> = {};
