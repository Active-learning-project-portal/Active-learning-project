import { Component, Input, OnInit } from '@angular/core';
import { UsersList } from 'src/app/shared/models/user-list.interface';

@Component({
  selector: 'alp-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css'],
})
export class ModalComponent implements OnInit {

  btnType!: 'Deactivate' | 'Activate' | 'Update' | 'View';
  @Input() user!: UsersList;
  modalTittle!:string;
  btnMessage!:string;
  disableInputs!:Boolean;

  ngOnInit(): void {
  }

  onActionButtonClicked(action:any):void{
    console.log(this.user)
    this.btnType =action;
     if(this.btnType === "Activate"){
      this.modalTittle == "Are you sure you want to activate user"
      this.btnMessage = "Activate"
      this.disableInputs = true;
     }else if(this.btnType === "Deactivate"){
      this.modalTittle == "Are you sure you want to deactivate user"
      this.btnMessage = "Deactivate"
      this.disableInputs = true;
     }else if(this.btnType === 'Update'){
      this.modalTittle == "Are you sure you want to update user"
      this.btnMessage = "Update"
      this.disableInputs = false;
     }else if(this.btnType === "View"){
      this.modalTittle == "View user"
      this.btnMessage = "View"
      this.disableInputs = true;
     }else{}
  }
}
