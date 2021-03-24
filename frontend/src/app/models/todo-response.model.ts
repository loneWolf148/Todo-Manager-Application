import { ITodo } from "./itodo.model";

export interface ITodoResponse {
    terminal: Date;
    items: ITodo[];
}