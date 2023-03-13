import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'removeRoleAndUnderScore',
})
export class RemoveRoleAndUnderScorePipe implements PipeTransform {
  transform(role: string): unknown {
    return role.replace('ROLE_', '').replace("_"," ");
  }
}
