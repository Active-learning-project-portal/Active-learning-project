import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'fullName'
})
export class FullNamePipe implements PipeTransform {

  transform(lastName: string, ...firstName: string[]): unknown {
    return lastName+" "+firstName.join(" ");
  }

}
