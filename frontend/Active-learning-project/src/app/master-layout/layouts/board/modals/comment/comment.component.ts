import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';
import { CardStatus } from '../../models/board-model';
import { CardCommentService } from '../../services/logic-handlers/card-comment.service';

@Component({
  selector: 'alp-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {
  commentType!: CardStatus

  formGroup: FormGroup = new FormGroup({
    comment: new FormControl(null, [Validators.required])
  });

  constructor(private cardCommentService: CardCommentService) { }

  ngOnInit(): void { }

  getFormControl(name: string): AbstractControl {
    return this.formGroup.controls[name];
  }

  isFormControlInvalid(name: string): boolean {
    return this.getFormControl(name).invalid;
  }

  isFormControlTouched(name: string): boolean {
    return this.getFormControl(name).touched;
  }

  saveUserComment(): void {
    this.formGroup.markAllAsTouched();

    if (this.formGroup.invalid) {
      return;
    }

    this.closeCommentDialog(this.getFormControl('comment').value)
  }

  closeCommentDialog(userResponse: string | null = null) {
    this.cardCommentService.closeUserCommentModal(userResponse)
  }
}
