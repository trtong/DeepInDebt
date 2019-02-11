import { IUser } from 'app/core/user/user.model';

export const enum RepaymentMethod {
    AVALANCHE = 'AVALANCHE',
    SNOWBALL = 'SNOWBALL',
    CUSTOM = 'CUSTOM'
}

export interface IUserDetails {
    id?: number;
    firstName?: string;
    lastName?: string;
    email?: string;
    repaymentMethod?: RepaymentMethod;
    user?: IUser;
}

export class UserDetails implements IUserDetails {
    constructor(
        public id?: number,
        public firstName?: string,
        public lastName?: string,
        public email?: string,
        public repaymentMethod?: RepaymentMethod,
        public user?: IUser
    ) {}
}
