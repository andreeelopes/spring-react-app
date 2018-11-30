
export interface IEmployee {
    id : number;
    username: string;
    name: string;
    email: string;
    job: string;
    admin:boolean;
}
export interface ICompany{
    id: number;
    name: string;
    address:string;
    email:string;
}
export interface IProposal {
    id : number;
    approver: IEmployee;
    companyProposed: ICompany;
    status: string;
    partnerCompany: ICompany;
}
export interface ISection {
    text : string;
    type:string;
    proposal:IProposal;
    id?: number;

}