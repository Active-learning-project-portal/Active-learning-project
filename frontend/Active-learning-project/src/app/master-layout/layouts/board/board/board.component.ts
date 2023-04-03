import {
  CdkDragDrop,
  moveItemInArray,
  transferArrayItem,
} from '@angular/cdk/drag-drop';
import { Component } from '@angular/core';
import { IBoardHeader } from '../../assignments/models/boadHeader.interface';

@Component({
  selector: 'alp-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css'],
})
export class BoardComponent {
  columns: Array<IBoardHeader> = [
    {
      id: 1,
      name: 'Backlog',
    },
    {
      id: 2,
      name: 'In Progress',
    },
    {
      id: 3,
      name: 'Requested Review',
    },
    {
      id: 4,
      name: 'Review',
    },
    {
      id: 5,
      name: 'Done',
    },
  ];

}
