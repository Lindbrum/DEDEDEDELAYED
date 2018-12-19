import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { SensorePage } from './sensore';

@NgModule({
  declarations: [
    SensorePage,
  ],
  imports: [
    IonicPageModule.forChild(SensorePage),
  ],
})
export class SensorePageModule {}
