import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, Refresher } from 'ionic-angular';
import { Distretto } from '../../model/distretto.model';

import { DettaglioDistrettoPage} from '../dettaglio-distretto/dettaglio-distretto';
/*import { Notizia } from '../../model/notizia.model';
import {NotiziaService} from "../../services/notizia.service";
//import {Utente} from "../../model/utente.model";

/**
 * Generated class for the NotiziaPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */


@IonicPage()
@Component({
  selector: 'page-home',
  templateUrl: 'home.html',
})

export class HomePage {
  distretti: Array<Distretto> = [
    {
      IdDistretto: 1,
      Nome: 'x',
      Referente: 'Dario',
      Alert : false
    },
    {
      IdDistretto: 2,
      Nome: 'y',
      Referente: 'Alessandro',
      Alert : false
    }
  ];

  constructor(public navCtrl: NavController, public navParams: NavParams/*, public notiziaService: NotiziaService*/) {
  }



    distretto (d: Distretto) {
      this.navCtrl.push('DettaglioDistrettoPage', { distrettoId: d.IdDistretto });
    }
  }
