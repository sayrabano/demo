import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RegistrationService } from 'src/app/registration.service';
import { User } from 'src/app/user';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  // public user={
  //   id:'',
  //   fullname: '',
  //   surname: '',
  //   mailid: '',
  //   phonenumber: '',
  //   address:'',
  //   password: '',
  //   status: ''

  //      }
  user : User = new User();
  msg=''


  constructor(private service1:RegistrationService, private router1: Router) { }

  ngOnInit(): void {
  }

 RegisterUser()
{
  console.log('entered')
  this.service1.UserRegister(this.user).subscribe(
    data => {
      console.log("good response");
      this.msg="Successfully registered"
      this.router1.navigate(['/login']);
    },
    error => {
      console.log("exception occured");
      this.msg="Check entered credentials";
    }
  )
}}
