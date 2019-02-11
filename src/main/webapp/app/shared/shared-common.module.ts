import { NgModule } from '@angular/core';

import { DeepInDebtSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [DeepInDebtSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [DeepInDebtSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class DeepInDebtSharedCommonModule {}
