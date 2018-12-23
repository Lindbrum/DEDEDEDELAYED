import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { EdificioPage } from './edificio';

@NgModule({
  declarations: [
    EdificioPage,
  ],
  imports: [
    IonicPageModule.forChild(EdificioPage),
  ],
})
export class EdificioPageModule {}
