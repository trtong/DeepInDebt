import { IUser } from 'app/core/user/user.model';

export interface IUserDetails {
    id?: number;
    firstName?: string;
    lastName?: string;
    email?: string;
    user?: IUser;
}

export class UserDetails implements IUserDetails {
    constructor(public id?: number, public firstName?: string, public lastName?: string, public email?: string, public user?: IUser) {}
}
