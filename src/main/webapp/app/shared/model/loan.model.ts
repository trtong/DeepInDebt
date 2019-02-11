import { Moment } from 'moment';
import { IPayment } from 'app/shared/model/payment.model';
import { IUser } from 'app/core/user/user.model';

export interface ILoan {
    id?: number;
    servicer?: string;
    principal?: number;
    interest?: number;
    paymentDueDate?: Moment;
    payments?: IPayment[];
    user?: IUser;
}

export class Loan implements ILoan {
    constructor(
        public id?: number,
        public servicer?: string,
        public principal?: number,
        public interest?: number,
        public paymentDueDate?: Moment,
        public payments?: IPayment[],
        public user?: IUser
    ) {}
}
