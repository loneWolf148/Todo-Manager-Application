import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AddTodoComponent } from './add-todo/add-todo.component';
import { HomeComponent } from './home/home.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { TodoComponent } from './todo/todo.component';
import { UpdateTodoComponent } from './update-todo/update-todo.component';

const routes: Routes = [
    { path: "home", component: HomeComponent },
    { path: "add-todo", component: AddTodoComponent },
    { path: "update-todo/:id", component: UpdateTodoComponent },
    { path: "todo/:id", component: TodoComponent },
    { path: "", redirectTo: "home", pathMatch: "full" },
    { path: "**", component: PageNotFoundComponent }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
