export interface SignUpModel {
  firstname: string,
  lastname: string,
  email: string,
  avatar: string | null,
  password: string,
  provider: "GOOGLE" | "GITHUB" | "MANUAL"
}
