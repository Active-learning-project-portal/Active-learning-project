import { RoleList } from "src/app/shared/models/roles.interface";

export interface UserResponse {
  id: string;
  username: string;
  firstname:string;
  lastname:string;
  roles: Array<RoleList>;
  tokenType: string;
  token: string;
}
