export interface IEmployee {
    id: number
    username: string
    name: string
    email: string
    job: string
    admin: boolean
}

export interface ICompany {
    id: number
    name: string
    address: string
    email: string
}

export enum ProposalStatus {
    placed = "PLACED",
    review_period = "REVIEW_PERIOD",
    approved = "APPROVED",
    declined = "DECLINED"
}

export interface IProposal {
    id: number
    approver: IEmployee
    companyProposed: ICompany
    status: string
    partnerCompany: ICompany
}

export interface ISection {
    text: string
    type: string
    proposal: IProposal
    id?: number
}

export interface IUser {
    id: number
    username: string
    name: string
    email: string
    job: string
    company: ICompany
    admin: boolean
}

export interface IComment {
    id: number
    author: IEmployee
    comment: string
    proposal: IProposal
}

export interface IBid {
    // TODO
    id: number
}

export interface IReview {
    id: number
    author: IEmployee
    text: string
    score: string
    proposal: IProposal
}