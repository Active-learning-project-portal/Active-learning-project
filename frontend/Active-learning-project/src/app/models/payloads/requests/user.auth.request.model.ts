export interface UserAuthRequestModel {
  firstname?: string,
  lastname?: string,
  avatar?: string | null,
  email: string,
  password?: string,
  provider: "GOOGLE" | "GITHUB" | "MANUAL",
  authType: "signin" | "signup" | null
}
