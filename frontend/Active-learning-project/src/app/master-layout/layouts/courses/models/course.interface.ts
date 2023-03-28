import { IUnit } from "./unit.interface";

export interface ICourse{
    id?:any,
    avatar:string,
    name:string,
    units:Array<IUnit>
}