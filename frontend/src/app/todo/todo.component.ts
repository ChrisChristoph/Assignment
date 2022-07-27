import { Component, OnInit } from '@angular/core';
import { Todo } from './todo';
import { TodoService } from '../todo.service';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit {

  todos: Todo[] = [];
  selectedTodo?: Todo;

  constructor(private todoService: TodoService) { }

  ngOnInit(): void {
    this.getTodos();
  }

  onSelect(todo: Todo): void {
    this.selectedTodo = todo;
  }

  getTodos(): void {
    this.todoService.getTodos()
      .subscribe(todos => this.todos = todos);
  }

  add(text: string, status: string): void {
    text = text.trim();
    status = status.trim();
    if (!text) { return; }
    this.todoService.addTodo({ text, status } as Todo)
      .subscribe(todo => {
        this.todos.push(todo);
      })
  }

  search(id: string): void {
    var newId: number = parseInt(id);
    this.todoService.getTodo(newId)
      .subscribe(todo => this.selectedTodo = todo);

  }
}
