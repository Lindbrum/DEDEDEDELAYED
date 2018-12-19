import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
/*import {Login} from "../../model/login.model";
import {UserService} from "../../services/user.service";*/
import {NgForm} from '@angular/forms';
//import {User} from "../../model/user.model";

/**
 * Generated class for the LoginPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-login',
  templateUrl: 'login.html',
})
export class LoginPage {
/*
  user: User = {
    idUser: 1,
    name: "",
    mat: "111",
    surname: "",
    email: "pippo",
    age: 0,
    res: "",
    numero: 9,
    password: "pippo",
    ruoloutente: "tallonatore",
    pwmod: true,
    partecipate: true,

  }
*/

  constructor(public navCtrl: NavController, public navParams: NavParams,/* private userService: UserService*/) {
  }

  //login: Login = null;
  loginTitle: string;
  loginSubTitle: string;

  ionViewDidLoad() {
    console.log('ionViewDidLoad LoginPage');
  }


  Accedi() {
/*
    console.log('lanciato metodo onLogin');
    console.log("email: " + this.user.email);
    console.log("password: " + this.user.password);


    if (LoginForm.valid) {

      console.log("form valida");


      console.log("Oggetto login1 " + this.login);

      this.userService.login(this.user).subscribe((data: Login) => {
        this.login = data;

        console.log("Oggetto login2 " + this.login);
      });*/
      this.navCtrl.setRoot('HomePage');
    
  }

}
