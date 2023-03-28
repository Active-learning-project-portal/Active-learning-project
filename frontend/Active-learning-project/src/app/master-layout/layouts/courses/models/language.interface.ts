import { ICourse } from "./course.interface";

export interface ILanguage{
    id?:any,
    name:string,
    courses:Array<ICourse>
    avatar:string
}