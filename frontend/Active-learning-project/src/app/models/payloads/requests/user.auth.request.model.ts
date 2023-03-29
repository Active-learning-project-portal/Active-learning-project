import { RoleList } from "src/app/shared/models/roles.interface";

export interface UserRequest {
  firstname?: string,
  lastname?: string,
  avatar?: string | null,
  username: string,
  password?: string,
  provider: "GOOGLE" | "GITHUB" | "MANUAL",
  authType: "signin" | "signup" | null,
  roles:Array<RoleList>
}
