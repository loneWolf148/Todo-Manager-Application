# TODO Manager Frontend Angular Application

## About Project
This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 1.4.10.
* Author : Subham Kumar Das
* Portfolio : [Check Here](https://crio-profile.netlify.app/)

## Components 
* [AddTodoComponent](src/app/add-todo) - Component responsible for adding new TODO
* [HomeComponent](src/app/home) -  Component responsible for rendering home page
* [PageNotFoundComponent](src/app/page-not-found) - Component that renders fallback page for incorrect route
* [TodoComponent](src/app/todo) - Component encapsulating each TODO
* [UpdateTodoComponent](src/app/update-todo) - Component resposible for editing TODO payload

## Models 
* [IResponse](src/app/models/iresponse.model.ts) - Model encapsulating response payload if HTTP response is successful
* [ITodo](src/app/models/itodo.model.ts) - Model encapsulating properties of TODO
* [TodoResponse](src/app/models/todo-response.model.ts) - Model Encapsulating list of TODO with Terminal Date

## Services 
* [TodoService](src/app/services/todo.service.ts) - Service associated with HTTP calls to fetch,add,update and delete TODO to remote server
* [ValidatorService](src/app/services/validator.service.ts) - Service associated with custom validations

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `-prod` flag for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).
