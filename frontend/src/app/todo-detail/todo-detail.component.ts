import { Component, OnInit, Input } from '@angular/core';
import { Todo } from '../todo/todo';

@Component({
  selector: 'app-todo-detail',
  templateUrl: './todo-detail.component.html',
  styleUrls: ['./todo-detail.component.css']
})
export class TodoDetailComponent implements OnInit {

  @Input() todo?: Todo;

  constructor() { }

  ngOnInit(): void {
  }

  hideDetail(): void {
    this.todo = undefined;
  }
}
