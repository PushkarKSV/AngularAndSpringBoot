import { Component, OnInit } from '@angular/core';
import {MatDatepickerInputEvent} from '@angular/material/datepicker';
import { NgForm, FormGroup } from '@angular/forms';
import { User } from './model/user';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { HttpClientService } from '../service/httpclient.service';

@Component({
  selector: 'app-localreport',
  templateUrl: './localreport.component.html',
  styleUrls: ['./localreport.component.css']
})
export class LocalreportComponent implements OnInit {
 
  user: User;
  todayDate:Date;
  startdatevalue:Date;
  
  
 
  
    dateFilters: string[] = ['Job CoB date', 'Job import date/time'];
    dateFilter= this.dateFilters[0]
   
   
    reporttypes: string[] = ['Show all trades', 'Only show the trades that failed the automatic check'];
    reportType=this.reporttypes[0]
   
    marketDataDisplay: string[] = ['Show market data', 'Hide market Data'];
  marketdata=this.marketDataDisplay[0];
    
    reportLocations: string[];
    reportLocation='';
    
    dateFrom(type: string, event: MatDatepickerInputEvent<Date>) {
        console.log("Date From:" +  `${type}: ${event.value}`);
       
      }

      dateTo(type: string, event: MatDatepickerInputEvent<Date>) {
        console.log("Date To :" + `${type}: ${event.value}`);
      }

      selectDateFilter(event){
        console.log("Date Filter:" + event.value);
      }
      selectReportType(event){
        console.log("Report Type:" + event.value);
      }
      selectMarketData(event){
        console.log("Market Data:" + event.value);
      }
      selectReportLocation(event){
        console.log("Report Location:" + event.value);
      }
      constructor(
        private route: ActivatedRoute, 
          private router: Router, 
          public http: HttpClient,private appConfigService:HttpClientService) {
            console.log(this.marketdata);
            console.log(this.dateFilter);
            this.startdatevalue=new Date();
 
 this.startdatevalue.setDate( this.startdatevalue.getDate()-1)
            this.appConfigService.getReportLocations().subscribe(
              ReportLocation=>{
                // for(var key in ReportLocation) {
                //   this.reportLocation=this.reportLocations[key];
                //   break;
                // }
                this.handleSuccessfulResponse(ReportLocation)

              }
            );
           
            // handleSuccessfulResponse(response)
            // {
            //   this.reportLocations=response
            // }
              
        
      }
      handleSuccessfulResponse(response: any) {
        this.reportLocations=response;
        this.reportLocation=this.reportLocations[0]
        this.user = new User(this.startdatevalue,this.marketdata,this.reportLocation,this.dateFilter,this.reportType);

      }
    
onSubmit(
  )
  {
   this.appConfigService.localReport(this.user)
   .subscribe(data=>
    console.log("success",data),
   error=>console.log("eror",error))
    console.log("submitted");
  }

    ngOnInit(){}

}
