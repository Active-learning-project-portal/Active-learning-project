import { Course } from './course.interface';
import { RoleList } from './roles.interface';
export interface UsersList {
  id: number;
  username: string;
  firstname: string;
  lastname:string;
  provider:string;
  avatar: string;
  course?: Course;
  joined: Date;
  lastSeen: Date;
  roles: RoleList[];
  isActive: Boolean;
}


