import { Component, OnInit } from '@angular/core';
import { HttpClientService } from '../service/httpclient.service';
import { HttpClient } from "@angular/common/http";
import { Router } from '@angular/router';

import { Location } from'@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { FileSideComponent } from '../file-side/file-side.component';
import { CdkTableModule} from '@angular/cdk/table';
import {DataSource} from '@angular/cdk/table';

@Component({
  selector: 'app-job-details',
  templateUrl: './job-details.component.html',
  styleUrls: ['./job-details.component.css']
})
export class JobDetailsComponent implements OnInit {
  subscription: Subscription;
  

location: Location;
 router: Router;
 dataSource :String  [];
 route1: string;
 JobList :any ;
 JobList1 :any ;
 isShow :boolean = false;
 isShow1 :boolean = false;
 TradeListState : String[] =['OKAY','Ok','OKAY'];
 public rowData:any;
 route11:String;
 public httpclientService:HttpClientService;
 id:any;
  
  constructor(public http: HttpClient,private appConfigService:HttpClientService,public fileside:FileSideComponent,public route: ActivatedRoute,
    private location1: Location, router: Router,private httpClientService:HttpClientService) { 

        router.events.subscribe((val) => {
          
         
            this.route1 = location1.path();
            this.route11=this.route1;
            console.log(this.route1)
         console.log(this.route.snapshot.paramMap.get('id')+"Checking");
         console.log(this.fileside.selectedValue+"Hello");
            this.id=this.route.snapshot.paramMap.get('id');
            if(this.route11!=null){
              this.http
                  .get(this.appConfigService.URL+this.route11)
                  
              .subscribe(data1 => {
                 
                     this.JobList=data1;
                    });
    
       
           
        
        }
        
        
           else {
            this.route1 = this.fileside.dropdownValue
            console.log(this.route1+"111111111111111111");
          }
          if(this.route11!=null){ 
            console.log(this.fileside.dropdownValue);
            this.http
                .get(this.appConfigService.URL+this.route11+'/OpenTrades')
                
            .subscribe(data2 => {
               
                   this.JobList1=data2;
                  });
          
          
          
          
          }
        
        }); 
        
  }
  
  ngOnInit() {
    if(this.route11!=null){ 
      console.log(this.fileside.dropdownValue);
      this.http
          .get(this.appConfigService.URL+this.route11+"/OpenJobs")
          
      .subscribe(data1 => {
         
             this.JobList=data1;
            });
 

   

}



   else {
    this.route1 = this.fileside.dropdownValue
    console.log(this.route1+"111111111111111111");
  }
 
  if(this.route11!=null){ 
    console.log(this.fileside.dropdownValue);
    this.http
        .get(this.appConfigService.URL+this.route11+'/OpenTrades')
        
    .subscribe(data2 => {
       
           this.JobList1=data2;
          });
  
  
  
  
  }
  this.id=this.route.snapshot.paramMap.get('id');
    this.httpClientService.getTradeDetails(this.id).subscribe(
      response =>this.handleSuccessfulResponse(response),
     );

}
handleSuccessfulResponse(response)
{
    this.JobList1=response;
    console.log(this.JobList1);
}


    

      
    
 
closeJob(jobid_to_close:any){
    console.log("--------------------------------================================"+jobid_to_close);
  }
}
