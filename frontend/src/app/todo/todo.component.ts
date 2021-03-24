import { Component, OnInit, Input, Output, EventEmitter, ViewContainerRef, ViewChild, TemplateRef } from '@angular/core';
import { Router } from '@angular/router';

import { TodoService } from '../services/todo.service';
import { ValidatorService } from '../services/validator.service';

import { ITodo } from '../models/itodo.model';
import { IResponse } from '../models/iresponse.model';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit {
  @Input("todo") item: ITodo;
  @Output("delete") delEmitter = new EventEmitter<number>();

  @ViewChild("todoTemplate") todoTemplate: TemplateRef<any>;

  constructor(private viewContainerRef: ViewContainerRef,
    private service: TodoService,
    private validator: ValidatorService,
    private router: Router) { }

  ngOnInit() {
    this.viewContainerRef.createEmbeddedView(this.todoTemplate);
  }

  toggleCompletionStatus(): void {
    this.item.isCompleted = !this.item.isCompleted;
    this.service.updateTodo(this.item).subscribe((response: IResponse) => {
      console.log(response);
    }, (error: IResponse) => {
      window.alert(error.message);
      this.router.navigate(["/home"]);
    });
  }

  isOverDue(): boolean {
    return this.validator.invalidDeadline(this.item);
  }

  editTodo(): void {
    this.router.navigate(["/update-todo", this.item.id]);
  }

  deleteTodo(): void {
    const confirmDelete: boolean = window.confirm("Are you sure you want to delete ? ");
    if (confirmDelete) {
      const id = this.item.id;

      this.service.deleteTodo(this.item.id).subscribe((response: IResponse) => {
        window.alert(response.message);
        this.delEmitter.emit(id);
      }, (error: IResponse) => {
        window.alert(error.message);
        this.router.navigate(["**"]);
      })
    }
  }

}
