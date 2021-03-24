import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { TodoComponent } from './todo/todo.component';
import { AddTodoComponent } from './add-todo/add-todo.component';
import { UpdateTodoComponent } from './update-todo/update-todo.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

import { TodoService } from './services/todo.service';
import { ValidatorService } from './services/validator.service';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    TodoComponent,
    AddTodoComponent,
    UpdateTodoComponent,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [
    TodoService,
    ValidatorService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
