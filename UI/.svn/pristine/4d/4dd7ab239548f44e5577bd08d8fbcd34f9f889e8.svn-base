import { Component, OnInit, Input } from '@angular/core';
import { HttpClientService } from '../service/httpclient.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-parameter',
  templateUrl: './parameter.component.html',
  styleUrls: ['./parameter.component.css']
})
export class ParameterComponent implements OnInit {

  Overview:String[];
 id1:any;
 id:any;

  constructor(private httpClientService:HttpClientService,public route: ActivatedRoute) { }

  ngOnInit() {
  // this.id1=this.route.snapshot.params['job1.t02_SRC_TRADE_ID']
  // console.log(this.id1+"pqrsrtc")
  //    this.httpClientService.getTradeOverview(this.id1).subscribe(
  //      response =>this.handleSuccessfulResponse(response),
  //   );

  this.id=this.route.snapshot.params['job1.t02_SRC_TRADE_ID']
    this.id1=this.route.snapshot.params['tradeid']
    console.log(this.id+"000000000000000000000");
    if(this.id1==null)
    this.httpClientService.getTradeOverview(this.route.snapshot.params['id'],this.route.snapshot.params['jobid'],this.id).subscribe(
      response =>this.handleSuccessfulResponse(response),
     );
     if(this.id==null)
     this.httpClientService.getTradeOverviews(this.route.snapshot.params['id'],this.route.snapshot.params['jobid'],this.id1).subscribe(
      response =>this.handleSuccessfulResponse(response),
     );
     
     
  }

  handleSuccessfulResponse(response)
{
    this.Overview=response;
}

}
