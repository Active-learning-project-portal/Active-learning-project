import { IGitHub } from './github.interface';
import { IGoogle } from './google.interface';
export interface IGlobalPayload extends IGitHub, IGoogle {
  id: any;
  email: string;
  username: string;
  password: string;
  provider: string;
}
