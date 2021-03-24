import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { TodoService } from '../services/todo.service';
import { ITodo } from '../models/itodo.model';
import { IResponse } from '../models/iresponse.model';
import { ITodoResponse } from '../models/todo-response.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  terminalDate: Date = null;
  items: ITodo[] = [];

  constructor(private service: TodoService, private router: Router) { }

  ngOnInit(): void {
    this.terminalDate = this.service.TerminalDate;
    this.fetchAllTodos();
  }

  fetchAllTodos() {
    this.service.fetchAllTodos(this.terminalDate).subscribe((response: ITodoResponse) => {
      this.items = response.items;
      this.terminalDate = response.terminal;
    }, (error: IResponse) => {
      window.alert(error.message);
      this.router.navigate(["/home"]);
    });
  }

  applyTerminalFilter(reset: boolean = false) {
    this.terminalDate = reset ? null : this.terminalDate;
    this.fetchAllTodos();
  }

  deleteTodo(id: number) {
    this.items = this.items.filter((todo) => todo.id !== id);
  }
}
