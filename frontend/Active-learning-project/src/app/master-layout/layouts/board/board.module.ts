import { DragDropModule } from '@angular/cdk/drag-drop';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { NgbProgressbarModule } from '@ng-bootstrap/ng-bootstrap';
import { MaterialModule } from 'src/app/shared/modules/material-modules';
import { PipesModule } from 'src/app/shared/pipes/pipes.module';
import { CaptureCardComponent } from './modals/capture-cards/capture-card.component';
import { BoardComponent } from './board.component';
import { CreateCardTaskComponent } from './modals/create-card-task/create-card-task.component';
import { ViewSelectedCardComponent } from './modals/views/view-selected-card/view-selected-card.component';
import { CardComponent } from './views/card/card.component';
import { CardColumnHeadersComponent } from './views/column-headers/column-headers.component';
import { CardColumnListComponent } from './views/card-column-list/card-column-list.component';
import { CircularNumberComponent } from './widgets/circular-number/circular-number.component';
import { ContentComponent } from './widgets/circular-number/content/content.component';
import { TasksComponent } from './widgets/tasks/tasks.component';
import { CommentComponent } from './modals/comment/comment.component';
import { AddExtraCardTimeComponent } from './modals/add-extra-card-time/add-extra-card-time.component';

@NgModule({
  declarations: [
    BoardComponent,
    CardComponent,
    CardColumnListComponent,
    CardColumnHeadersComponent,  
    CreateCardTaskComponent,
    ViewSelectedCardComponent,
    CircularNumberComponent,
    ContentComponent,
    TasksComponent,
    CaptureCardComponent,
    CommentComponent,
    AddExtraCardTimeComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    DragDropModule,
    NgbProgressbarModule,
    ReactiveFormsModule
  ],
})
export class BoardModule { }
