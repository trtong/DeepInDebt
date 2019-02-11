import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'loan',
                loadChildren: './loan/loan.module#DeepInDebtLoanModule'
            },
            {
                path: 'payment',
                loadChildren: './payment/payment.module#DeepInDebtPaymentModule'
            },
            {
                path: 'user-details',
                loadChildren: './user-details/user-details.module#DeepInDebtUserDetailsModule'
            },
            {
                path: 'loan',
                loadChildren: './loan/loan.module#DeepInDebtLoanModule'
            },
            {
                path: 'payment',
                loadChildren: './payment/payment.module#DeepInDebtPaymentModule'
            },
            {
                path: 'user-details',
                loadChildren: './user-details/user-details.module#DeepInDebtUserDetailsModule'
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DeepInDebtEntityModule {}
