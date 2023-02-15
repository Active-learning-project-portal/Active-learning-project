export interface UserAuthModel {
  firstname?: string,
  lastname?: string,
  avatar?: string,
  email: string,
  password?: string | null,
  provider: "GOOGLE" | "GITHUB" | "MANUAL",
  authType: "signin" | "signup"
}
