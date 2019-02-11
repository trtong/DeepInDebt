import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ILoan } from 'app/shared/model/loan.model';
import { AccountService } from 'app/core';
import { LoanService } from './loan.service';

@Component({
    selector: 'jhi-loan',
    templateUrl: './loan.component.html'
})
export class LoanComponent implements OnInit, OnDestroy {
    loans: ILoan[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected loanService: LoanService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.loanService
            .query()
            .pipe(
                filter((res: HttpResponse<ILoan[]>) => res.ok),
                map((res: HttpResponse<ILoan[]>) => res.body)
            )
            .subscribe(
                (res: ILoan[]) => {
                    this.loans = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInLoans();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ILoan) {
        return item.id;
    }

    registerChangeInLoans() {
        this.eventSubscriber = this.eventManager.subscribe('loanListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
