import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import {HomePage} from '../home/home';
import { Distretto } from '../../model/distretto.model';
import { Edificio } from '../../model/edificio.model';
import { EdificioPage } from '../edificio/edificio';


/*import { distrettoService } from '../../services/distretto.service';


/**
 * Generated class for the DettagliodistrettoPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-dettaglio-distretto',
  templateUrl: 'dettaglio-distretto.html',
})
export class DettaglioDistrettoPage {
 EDIFICIO: Edificio;
  edifici: Array<Edificio> = [
    {
      IdEdificio: 1,
      Nome: 'coppito 1',
      Referente: 'Dario',
      Alert : false
    },
    {
      IdEdificio: 2,
      Nome: 'coppito 2',
      Referente: 'Alessandro',
      Alert : false
    }
  ];

  constructor(public navCtrl: NavController, public navParams: NavParams/*, public distrettoService: distrettoService*/) {
  }

ionViewDidLoad() {
    console.log('ionViewDidLoad DettaglioNotiziaPage');
    const edificioID = this.navParams.data.edificioID;
    this.EDIFICIO = this.edifici.find(d => d.IdEdificio === edificioID);
  }
  /*dettaglioDistretti (d:Distretto){
  
    //  let idnotizia:number = n.id;
      console.log(d.IdDistretto +  'distretto.ts');
       this.navCtrl.push('DettaglioDistrettoPage', { DistrettoID: d.IdDistretto });
   
     }*/
 Home() {
    this.navCtrl.push('HomePage');
  }
  edificio (e: Edificio) {
    this.navCtrl.push('EdificioPAge', { edificioID: e.IdEdificio });
  }
}