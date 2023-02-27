import { Pipe, PipeTransform } from '@angular/core';
import { RoleList } from '../models/roles.interface';

@Pipe({
  name: 'arrayToString'
})
export class ArrayToStringPipe implements PipeTransform {

  transform(roleList: RoleList[]): string {
    let roleNames =""; 
    roleList.forEach(role =>{
       roleNames += role.name+" ";
    })
    return roleNames.trim();
  }

}
