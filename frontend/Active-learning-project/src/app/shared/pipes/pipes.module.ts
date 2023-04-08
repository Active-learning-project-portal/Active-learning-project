import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ArrayToStringPipe } from './array-string.pipe';



@NgModule({
  declarations: [ArrayToStringPipe],
  exports: [ArrayToStringPipe],
  imports: [
    CommonModule
  ]
})
export class PipesModule { }
