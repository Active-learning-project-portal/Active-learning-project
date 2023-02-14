export interface SignInModel {
  email: string,
  password: string,
  provider: "GOOGLE" | "GITHUB" | "MANUAL"
}
