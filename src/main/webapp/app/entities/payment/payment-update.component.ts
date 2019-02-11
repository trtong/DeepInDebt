import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { IPayment } from 'app/shared/model/payment.model';
import { PaymentService } from './payment.service';
import { ILoan } from 'app/shared/model/loan.model';
import { LoanService } from 'app/entities/loan';

@Component({
    selector: 'jhi-payment-update',
    templateUrl: './payment-update.component.html'
})
export class PaymentUpdateComponent implements OnInit {
    payment: IPayment;
    isSaving: boolean;

    loans: ILoan[];
    paymentDateDp: any;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected paymentService: PaymentService,
        protected loanService: LoanService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ payment }) => {
            this.payment = payment;
        });
        this.loanService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ILoan[]>) => mayBeOk.ok),
                map((response: HttpResponse<ILoan[]>) => response.body)
            )
            .subscribe((res: ILoan[]) => (this.loans = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.payment.id !== undefined) {
            this.subscribeToSaveResponse(this.paymentService.update(this.payment));
        } else {
            this.subscribeToSaveResponse(this.paymentService.create(this.payment));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPayment>>) {
        result.subscribe((res: HttpResponse<IPayment>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackLoanById(index: number, item: ILoan) {
        return item.id;
    }
}
