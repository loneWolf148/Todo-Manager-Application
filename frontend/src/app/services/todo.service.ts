import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../environments/environment';

import { ITodo } from '../models/itodo.model';
import { IResponse } from '../models/iresponse.model';
import { ITodoResponse } from '../models/todo-response.model';

@Injectable()
export class TodoService {

  private readonly baseUrl = environment.baseApiUrl;

  private terminalDate: Date = null;

  private readonly headers = new HttpHeaders({
    "Content-Type": "application/json"
  });

  get TerminalDate(): Date {
    return this.terminalDate;
  }

  constructor(private http: HttpClient) { }

  private throwError(error: HttpErrorResponse) {
    return Observable.throw(error.error);
  }

  fetchAllTodos(date: Date = null): Observable<ITodoResponse> {
    this.terminalDate = date;

    if (this.terminalDate == null) {
      return this.http.get<ITodoResponse>(this.baseUrl);
    } else {
      const requestParams: HttpParams = new HttpParams().append("terminal", this.terminalDate.toString());
      return this.http.get<ITodoResponse>(this.baseUrl, { params: requestParams }).catch(this.throwError);
    }
  }

  fetchTodo(id: number): Observable<ITodo> {
    if (this.terminalDate == null) {
      return this.http.get<ITodo>(`${this.baseUrl}/${id}`);
    } else {
      const requestParamas: HttpParams = new HttpParams().append("terminal", this.terminalDate.toString());
      return this.http.get<ITodo>(`${this.baseUrl}/${id}`, { params: requestParamas }).catch(this.throwError);
    }
  }

  addTodo(newTodo: ITodo): Observable<IResponse> {
    return this.http.post<IResponse>(`${this.baseUrl}`, newTodo, { headers: this.headers }).catch(this.throwError);
  }

  updateTodo(updatedTodo: ITodo): Observable<IResponse> {
    if (this.terminalDate == null) {
      return this.http.patch<IResponse>(this.baseUrl, updatedTodo, { headers: this.headers });
    } else {
      const requestParams: HttpParams = new HttpParams().append("terminal", this.terminalDate.toString());
      return this.http.patch<IResponse>(this.baseUrl, updatedTodo, { headers: this.headers, params: requestParams }).catch(this.throwError);
    }
  }

  deleteTodo(id: number): Observable<IResponse> {
    if (this.terminalDate == null) {
      return this.http.delete<IResponse>(`${this.baseUrl}/${id}`, { headers: this.headers });
    } else {
      const requestParams: HttpParams = new HttpParams().append("terminal", this.terminalDate.toString());
      return this.http.delete<IResponse>(`${this.baseUrl}/${id}`, { headers: this.headers, params: requestParams }).catch(this.throwError);
    }
  }

  resetTerminalDate() {
    this.terminalDate = null;
  }
}
