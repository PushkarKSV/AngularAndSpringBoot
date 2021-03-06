import { Component, OnInit } from '@angular/core';
import { HttpClientService } from './service/httpclient.service';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {
  title = 'mgb';
  userValid:boolean=false;
  userValid1:boolean=false;
  Userprofile:String[];
  public UserID:String;
  rowData:any;
  ShowOpenJobs:boolean=true;
  ShowOpenJobs1:boolean=false;
  ShowOpenJobs2:boolean=false;  

  constructor(private httpClientService:HttpClientService,public http: HttpClient, private router: Router,public route: ActivatedRoute,private appConfigService:HttpClientService) { }
  ngOnInit() {
    this.httpClientService.getUserID().subscribe(
       response =>this.handleSuccessfulResponseUserID(response),
      );  
    } 

  handleSuccessfulResponseUserID(response)
  {
    console.log(response+"0000000000000000000000000000000000");
    this.httpClientService.UserLoggedIn=response;
      this.UserID=response;
      if(this.UserID==null)
      {
        this.userValid=true;
      }
      else{
        this.userValid1=true;
      }
      console.log(this.UserID+"------------------");
  }
}