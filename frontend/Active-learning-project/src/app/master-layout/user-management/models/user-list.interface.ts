export interface UsersList {
  id: number;
  email: string;
  name: string;
  avatar: string;
  course?: string;
  joined: Date;
  'last seen': Date;
  roles: String[];
  action: string;
}
