import { Component, OnInit } from '@angular/core';
import { HttpClientService } from '../service/httpclient.service';
import { AppComponent } from '../app.component';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})

export class UserDetailsComponent implements OnInit {
  UserDetails: any;
  User: String;
  constructor(public http: HttpClient,private httpClientService: HttpClientService, public UserIdApp: AppComponent) { }
  ngOnInit() {
    console.log("------==============" + this.UserIdApp.UserID);
    this.User = this.UserIdApp.UserID;
    this.http
      .get(this.httpClientService.URL + "/Users/" + this.httpClientService.UserLoggedIn + "/UserDetails")
      .subscribe(data1 => {
        this.UserDetails = data1;
      });
  }  

  Report: boolean = false;

  ShowReport() {
    this.Report = !this.Report;
  }

  ReportData = [
    { id: '/OpenJobs', name: 'Open Jobs' },
    { id: '/LocalReport', name: 'Local Report' }
  ];

}