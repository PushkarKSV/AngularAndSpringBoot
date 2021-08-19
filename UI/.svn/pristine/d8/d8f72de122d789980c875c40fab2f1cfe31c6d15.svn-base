import { Component, OnInit } from '@angular/core';
import { HttpClientService } from '../service/httpclient.service';

@Component({
  selector: 'app-user-maintenance',
  templateUrl: './user-maintenance.component.html',
  styleUrls: ['./user-maintenance.component.css']
})

export class UserMaintenanceComponent implements OnInit {

    mandantList:String[];
    user_route: string;

    constructor(private httpClientService:HttpClientService){}
  
    ngOnInit() 
    {
      this.httpClientService.getMandantList().subscribe(response =>this.handleSuccessfulResponse(response));
    }

    handleSuccessfulResponse(response)
  {
      this.mandantList=response;
  }
 

}
