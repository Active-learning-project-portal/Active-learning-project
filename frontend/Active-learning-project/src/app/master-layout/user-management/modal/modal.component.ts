import { Component, Input, OnInit } from '@angular/core';
import { UsersList } from '../models/user-list.interface';

@Component({
  selector: 'alp-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css'],
})
export class ModalComponent implements OnInit {

  btnType!: 'Deactivate' | 'Activate' | 'Update' | 'View';
  @Input() user!: UsersList;

  ngOnInit(): void {
  }

  onActionButtonClicked(action:any):void{
    this.btnType =action;
  }
}
