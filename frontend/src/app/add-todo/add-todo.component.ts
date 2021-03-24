import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { TodoService } from '../services/todo.service';
import { ValidatorService } from '../services/validator.service';

import { ITodo } from '../models/itodo.model';
import { IResponse } from '../models/iresponse.model';

@Component({
  selector: 'app-add-todo',
  templateUrl: './add-todo.component.html',
  styleUrls: ['./add-todo.component.css']
})
export class AddTodoComponent implements OnInit {

  readonly todo: ITodo = {
    id: null,
    description: null,
    deadline: null,
    isCompleted: false
  };

  constructor(private service: TodoService, private validator: ValidatorService, private router: Router) { }

  ngOnInit() { }

  addTodo(): void {
    this.service.addTodo(this.todo).subscribe((response: IResponse) => {
      window.alert(response.message);
      this.router.navigate(["/home"]);
    }, (error: IResponse) => {
      window.alert(error.message);
      this.router.navigate(["**"]);
    })
  }

  invalidDeadline(): boolean {
    return this.validator.invalidDeadline(this.todo);
  }
}
