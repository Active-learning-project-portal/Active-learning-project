import { Course } from './course.interface';
import { RoleList } from './roles.interface';
export interface UsersList {
  id: number;
  username: string;
  name: string;
  lastname:string;
  provider:string;
  avatar: string;
  course?: Course;
  joined: Date;
  lastSeen: Date;
  roles: RoleList[];
  isActive: Boolean;
}


