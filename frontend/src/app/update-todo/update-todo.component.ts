import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { TodoService } from '../services/todo.service';
import { ValidatorService } from '../services/validator.service';

import { ITodo } from '../models/itodo.model';
import { IResponse } from '../models/iresponse.model';

@Component({
  selector: 'app-update-todo',
  templateUrl: './update-todo.component.html',
  styleUrls: ['./update-todo.component.css']
})
export class UpdateTodoComponent implements OnInit {

  private id: number;

  readonly todo: ITodo = {
    id: null,
    description: null,
    deadline: null,
    isCompleted: null
  };

  constructor(private service: TodoService,
    private validator: ValidatorService,
    private router: Router,
    private activatedRoute: ActivatedRoute) {
    this.id = +this.activatedRoute.snapshot.paramMap.get("id");
  }

  ngOnInit() {
    this.service.fetchTodo(this.id).subscribe((response) => {
      this.todo.id = response.id;
      this.todo.description = response.description;
      this.todo.deadline = response.deadline;
      this.todo.isCompleted = response.isCompleted;
    }, (error: IResponse) => {
      window.alert(error.message);
      this.router.navigate(["**"]);
    });
  }

  invalidDeadline(): boolean {
    return this.validator.invalidDeadline(this.todo);
  }

  updateTodo(): void {
    this.service.updateTodo(this.todo).subscribe((response: IResponse) => {
      window.alert(response.message);
      this.router.navigate(["/home"]);
    }, (error: IResponse) => {
      window.alert(error.message);
      this.router.navigate(["/home"]);
    })
  }

}
