import { Component, OnInit } from '@angular/core';
import { NgForm } from '../../../node_modules/@angular/forms';
import { HttpClient } from '../../../node_modules/@angular/common/http';
import { User } from '../user';
import { Router } from '../../../node_modules/@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  regist: User
  constructor(private http: HttpClient, private router:Router) { }

  ngOnInit() {
  }

  register(reg:NgForm){
    this.regist = new User();
    this.regist.firstName=reg.value.fname;
    this.regist.lastName=reg.value.lname;
    this.regist.phoneNumber=reg.value.phone;
    this.regist.email=reg.value.email;
    this.regist.password=reg.value.pass;
    this.regist.addressLine1=reg.value.addressL1;
    this.regist.addressLine2=reg.value.addressL2;
    this.regist.state=reg.value.state;
    this.regist.pincode=reg.value.pcode;

    alert(this.regist.firstName+" "+this.regist.lastName+"\n"+this.regist.email);

    this.http.post<{message:number}>("http://localhost:8080/database/RegisterServlet",
  {"firstName": this.regist.firstName,"lastName":this.regist.lastName,"phone":this.regist.phoneNumber,"email":this.regist.email,"password":this.regist.password,"addressLine1":this.regist.addressLine1,"addressLine2":this.regist.addressLine2,"state":this.regist.state,"pincode":this.regist.pincode}).subscribe(
  
  (response) =>{
    console.log(response.message);


    if (response.message==1) {
      alert("Registration UnSuccessful");
      this.router.navigate(['']);
    } else {
      alert("Registration successful");
    }

  },

  (error) =>{

  });

  }
}