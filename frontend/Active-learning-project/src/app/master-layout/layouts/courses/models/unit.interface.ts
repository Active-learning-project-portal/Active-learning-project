import { IPrerequestine } from "./prerequestine.interface";

export interface IUnit{
    id?:any,
    avatar:string,
    name:string,
    title:string,
    unitContent:string,
    prerequestines:Array<IUnit>,
}