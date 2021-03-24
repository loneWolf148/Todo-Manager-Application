import { Injectable } from '@angular/core';

import { ITodo } from '../models/itodo.model';

@Injectable()
export class ValidatorService {
  private readonly current = new Date();

  private currentDate(): string {
    const year = this.current.getFullYear();

    let month: number | string = this.current.getMonth() + 1;
    month = month < 9 ? `0${month}` : month.toString();

    let day: number | string = this.current.getDate();
    day = day < 9 ? `0${day}` : day.toString();

    return `${year}-${month}-${day}`;
  }

  constructor() { }

  invalidDeadline(todo: ITodo): boolean {
    return this.currentDate().localeCompare(todo.deadline.toString()) > 0;
  }
}
