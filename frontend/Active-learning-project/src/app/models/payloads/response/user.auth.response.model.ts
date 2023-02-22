export interface UserAuthResponseModel {
  id: string;
  username: string;
  firstname:string;
  lastname:string;
  roles: [];
  tokenType: string;
  token: string;
}
