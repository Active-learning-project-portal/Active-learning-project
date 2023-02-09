import { IGitHub } from './github.interface';
import { IGoogle } from './google.interface';
import { IManual } from './manual.interface';
export interface IGlobalPayload extends IGitHub, IGoogle, IManual {
  id:any
}
