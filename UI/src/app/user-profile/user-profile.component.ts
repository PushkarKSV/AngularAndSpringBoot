import { Component, OnInit } from '@angular/core';
import { HttpClientService } from '../service/httpclient.service';
import { HttpClient } from "@angular/common/http";
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Location, NgIf } from '@angular/common';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { FileSideComponent } from '../file-side/file-side.component';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})

export class UserProfileComponent {
  Userid: string;
  route1: string;
  urlFlag: boolean = false;
  public href: any;
  public gridApi;
  public gridColumnApi;
  dataFromParent: string;
  public columnDefs;
  public autoGroupColumnDef;
  public defaultColDef;
  public paginationPageSize;
  public paginationNumberFormatter;
  public rowData: any;
  click: any;
  id$: Observable<string>;
  id: string;
  data2:any;
  User: any;
  public stringToSplit: string;
  public count: any;
  constructor(private httpClientService: HttpClientService, public http: HttpClient, public route: ActivatedRoute, private appConfigService: HttpClientService,
    private Userlocation: Location, public cookieService: CookieService, public fileside: FileSideComponent, private router: Router) {
    router.events.subscribe((val) => {
      if (Userlocation.path() != '') {
        this.Userid = Userlocation.path();
        console.log("Value of UserMaintenance URL" + this.Userid);
      }
    });
 

    

    this.columnDefs = [
      {
        headerName: 'Edit', field: 'notes', width: 50,
        cellRenderer: function (params) {
          return '<span><i class="material-icons">edit</i>' + '</span>'
        }
      },
      { headerName: 'First Name', field: '0',width: 90, resizable: true },
      { headerName: 'Last Name', field: '1',width: 85, sortable: true, resizable: true },
      { headerName: 'Mandant', field: '2', width: 75, resizable: true },
      { headerName: 'Phone', field: '3', width: 130, resizable: true },
      { headerName: 'Email', field: '4',width: 280, resizable: true },
      { headerName: 'NT-ID', field: '5',width: 75, resizable: true },
      // function (params)
      // {
      //   let stringToSplit = params.data[6] ;
      //   let Role = stringToSplit.split(" ");
      //   const count = stringToSplit.split(" ").length;
      //   console.log(Role);
      // for (let x in count)
      // {
      {
        headerName: 'Trader Role', width: 60, disable: true, cellRenderer: function (params) {
          let stringToSplit = params.data[6] ;
          let Role = stringToSplit.split(",");
          if (Role[0] == "TRADER" || Role[1] == "TRADER") { return `<input type='checkbox' checked />`; }
          else { return `<input type='checkbox' />`; }
        },
      },
      {
        headerName: 'Controller Role',width: 65, disable: true, cellRenderer: function (params) {
          let stringToSplit = params.data[6] ;
          let Role = stringToSplit.split(",");
          let count = stringToSplit.split(",").length;
          console.log("Role details = "+Role+"\t Count = "+count + "\t Role[0] = "+Role[0]+"\t Role[1] = "+Role[1]);
          if (Role[0] == "CONTROLLER" || Role[1] == "CONTROLLER") { return `<input type='checkbox' checked />`; }
          else { return `<input type='checkbox' />`; }
        },
      },
      {
        headerName: 'Admin Role',width: 65, disable: true, cellRenderer: function (params) {
          let stringToSplit = params.data[6] ;
          let Role = stringToSplit.split(",");
          if (Role[0] == "ADMIN" || Role[1] == "ADMIN") { return `<input type='checkbox' checked />`; }
          else { return `<input type='checkbox' />`; }
        },
      },
      {
        headerName: 'System Admin Role',width: 90,disable: true,cellRenderer: function (params) {
          let stringToSplit = params.data[6] ;
          let Role = stringToSplit.split(",");
          if (Role[0] == "SYSTEM_ADMIN" || Role[1] == "SYSTEM_ADMIN") { return `<input type='checkbox' checked />`; }
          else { return `<input type='checkbox' />`; }
        },
      },
      {
        headerName: 'Sparkassen Admin Role',width: 90, disable: true, cellRenderer: function (params) {
          let stringToSplit = params.data[6] ;
          let Role = stringToSplit.split(",");
          if (Role[0] == "SPK_ADMIN" || Role[1] == "SPK_ADMIN") { return `<input type='checkbox' checked />`; }
          else { return `<input type='checkbox' />`; }
        },
      },
      {
        headerName: 'User Maintainance Role',width: 125, disable: true, cellRenderer: function (params) {
          let stringToSplit = params.data[6] ;
          let Role = stringToSplit.split(",");
          if (Role[0] == "USER_MAINTAIN_ADMIN" || Role[1] == "USER_MAINTAIN_ADMIN") { return `<input type='checkbox' checked />`; }
          else { return `<input type='checkbox' />`; }
        },
      },
      {
        headerName: 'Reporter Role',width: 75, disable: true, cellRenderer: function (params) {
          let stringToSplit = params.data[6] ;
          let Role = stringToSplit.split(",");
          if (Role[0] == "REPORTER" || Role[1] == "REPORTER") { return `<input type='checkbox' checked />`; }
          else { return `<input type='checkbox' />`; }
        },
      },
      {
        headerName: 'Read Only Role',width: 85, disable: true, cellRenderer: function (params) {
          let stringToSplit = params.data[6] ;
          let Role = stringToSplit.split(",");
          if (Role[0] == "READ_ONLY" || Role[1] == "READ_ONLY") { return `<input type='checkbox' checked />`; }
          else { return `<input type='checkbox' />`; }
        },
      },
      { headerName: 'Report Type',width: 85, field: '7', resizable: true }
    ];
    this.paginationPageSize = 20;
  }

  gridOptions = {
    enableRangeSelection: true,
    allowContextMenuWithControlKey: true,
    rowData: null,
    enableFilter: true
  };

  quickSearchValue: any = '';
  public onQuickFilterChanged() {
    this.gridOptions.rowData.filter(this.quickSearchValue);
  }

 
  onRowClicked(event: any) 
   { 
     this.click=event.data;
    console.log('row', event.data); 
    console.log('event',this.click);
    this.User=this.route.snapshot.paramMap.get('Userid')
    this.httpClientService.changeMessage(this.User)
    this.appConfigService.accessdetails=event.data;
    this.router.navigate([this.Userid+'/EditAccess']);
   //  var url = event.target.value;
     console.log(this.Userid = this.route.snapshot.paramMap.get['Userid'].toString());
    //this.router.navigate([this.route.snapshot.paramMap.get('Userid')]);
    //this.router.navigate([url]);
   }
   sendMandant()
{
  this.User=this.route.snapshot.paramMap.get('Userid')
  this.httpClientService.changeMessage(this.User)
  console.log(this.User)
}



  onPageSizeChanged(newPageSize) {
    var value = (<HTMLInputElement>document.getElementById("page-size")).value;
    this.gridApi.paginationSetPageSize(Number(value));
  }

  ngOnInit() {
    
    console.log(this.id = this.route.snapshot.paramMap.get['Userid']);
 this.data2 = this.route.snapshot.paramMap.get('Userid');
  }

  onGridReady(params) {
    let storageData = localStorage.getItem('StorageValue');
    let eventType = localStorage.getItem('eventType');
    console.log("EVENTTYPE", eventType);
    if (storageData != localStorage.getItem('StorageValue')) {
      this.urlFlag = true;
    }
    else {
      this.urlFlag = false;
    }
    this.gridApi = params.api;
    this.gridColumnApi = params.columnApi;
    this.gridApi.refreshCells();

    
    console.log("this.route.params",this.route.parent.params);
    console.log("ID",this.data2);
    
    console.log("datafrom component",this.dataFromParent);
    
    console.log("this.route.params", this.route.parent.params);
    console.log("ID", this.data2);
    console.log("datafrom component", this.dataFromParent);
    console.log("------------------------------StorageValue-------------------------------------", storageData);
    var datafrom = this.cookieService.get('cookieValue');
    this.http
      .get(this.appConfigService.URL + "Mandants/" + this.route.snapshot.paramMap.get('Userid') + "/UserMaintenance")
      .subscribe(Backend_data => {
        this.rowData = Backend_data;
        console.log(this.rowData);
        params.api.paginationGoToPage(0);
      });
  }

}
