import { Moment } from 'moment';
import { ILoan } from 'app/shared/model/loan.model';

export interface IPayment {
    id?: number;
    paymentDate?: Moment;
    amount?: number;
    loan?: ILoan;
}

export class Payment implements IPayment {
    constructor(public id?: number, public paymentDate?: Moment, public amount?: number, public loan?: ILoan) {}
}
