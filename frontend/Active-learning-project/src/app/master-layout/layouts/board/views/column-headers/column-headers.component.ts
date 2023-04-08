import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'alp-column-headers',
  templateUrl: './column-headers.component.html',
  styleUrls: ['./column-headers.component.css']
})
export class CardColumnHeadersComponent implements OnInit {
  @Input()
  backlogCount!: number;

  @Input()
  startedCount!: number;

  @Input()
  pausedCount!: number;

  @Input()
  completedCount!: number;

  @Input()
  archivedCount!: number;

  constructor() { }

  ngOnInit(): void {
  }

}
