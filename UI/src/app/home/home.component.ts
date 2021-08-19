import { Component, OnInit } from '@angular/core';
import { HttpClientService } from '../service/httpclient.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  /*templateUrl: './tmphome.component.html',*/
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  mandants: String[];
  mandantList: String[];
  HomeEAA: String;
  HomeEAAJobsTrades: any;
  HomeCBB: String;
  HomeCBBJobsTrades: any;
  HomePAG: String;
  HomePAGJobsTrades: any;
  id$: Observable<string>;
  id: string;
  message: string;
  PAGflag = false;
  EAAflag = false;
  CBBflag = false;

  constructor(private httpClientService: HttpClientService, public route: ActivatedRoute, private _router: Router) { this.route.parent.params.subscribe(params => console.log("params@", params)); }
  ngOnInit() {
    this.httpClientService.currentMessage.subscribe(message => this.message = message)
    let id = this.route.snapshot.params.typeId;
    console.log("id", id);
    
    this.httpClientService.getMandants().subscribe(
      response => this.handleSuccessfulResponseNew(response),
    );

    this.httpClientService.getMandantList().subscribe(
      response => this.handleSuccessfulResponse(response),
    );

    this.httpClientService.getHomeEAA().subscribe(
      response => this.handleSuccessfulResponseHomeEAA(response),
    );

    this.httpClientService.getHomeCBB().subscribe(
      response => this.handleSuccessfulResponseHomeCBB(response),
    );

    this.httpClientService.getHomePAG().subscribe(
      response => this.handleSuccessfulResponseHomePAG(response),
    );
  }

  handleSuccessfulResponseNew(response) {
    this.mandants = response;
  }

  handleSuccessfulResponse(response) {
    this.mandantList = response;
  }

  handleSuccessfulResponseHomeEAA(response) {
    this.HomeEAAJobsTrades = response;
    if (this.HomeEAAJobsTrades == null) {
      this.EAAflag = false;
    }
    else {
      this.EAAflag = true;
    }
  }

  handleSuccessfulResponseHomeCBB(response) {
    this.HomeCBBJobsTrades = response;
    if (this.HomeCBBJobsTrades == null) {
      this.CBBflag = false;
    }
    else {
      this.CBBflag = true;
    }
  }

  handleSuccessfulResponseHomePAG(response) {
    console.log("response==" + response);
    this.HomePAGJobsTrades = response;
    if (this.HomePAGJobsTrades == null) {
      this.PAGflag = false;
    }
    else {
      this.PAGflag = true;
    }

  }

  onClick() {
    console.log("This is sibiling 2");
    this.httpClientService.changeMessage("Hello from Sibling");
  }

  onClick1() {
    console.log("This is sibiling 2");
    this.httpClientService.changeMessage("Hello from Sibling1");
  }

  routing(client: any) {
    this._router.navigate([client + "/Job"]);
  }

  getAllJobs(client: any) {
    this.httpClientService.changeMessage("Get Jobs");
    this._router.navigate([client + "/Job"]);
  }

  getOpenJobs(client: any) {
    this.httpClientService.changeMessage("Get OpenJobs");
    this._router.navigate([client + "/Job"]);
  }
}
