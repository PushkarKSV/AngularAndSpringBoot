import { Component, Input, OnInit } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Module, AllCommunityModules } from "@ag-grid-community/all-modules";
import { ActivatedRoute, NavigationEnd } from '@angular/router';
import { Observable } from 'rxjs';
import { HttpClientService } from '../service/httpclient.service';
import { Router } from '@angular/router';
import { Location, DatePipe } from '@angular/common';
import { FileSideComponent } from '../file-side/file-side.component';
import { CookieService } from 'ngx-cookie-service';
import { IgxExcelExporterOptions, IgxExcelExporterService } from "igniteui-angular";

@Component({
  selector: 'app-job',
  templateUrl: './job.component.html',
  styleUrls: ['./job.component.css']
})

export class JobComponent {
  public gridApi;
  public gridColumnApi;
  public modules: Module[] = AllCommunityModules;
  public columnDefs;
  public defaultColDef;
  public autoGroupColumnDef;
  public paginationPageSize;
  public paginationNumberFormatter;
  public rowData: any;
  public hasChildren: boolean;
  id$: Observable<string>;
  id: string;
  event1: any;
  dataFromParent: string;
  urlFlag: boolean = false;
  public href: any;
  route1: string;
  public gridOptions: any;
  message: any;
  start_date: string;
  end_date: string;
  startdateValue: Date;
  enddateValue: Date;
  getRowHeight:any;

  constructor(public http: HttpClient, public route: ActivatedRoute, public datepipe: DatePipe, private excelExportService: IgxExcelExporterService, private appConfigService: HttpClientService, public fileside: FileSideComponent,
    public cookieService: CookieService, location1: Location, private router: Router, private _router: Router) {
    console.log(this.message);
    router.events.subscribe((val) => {
      if (location1.path() != '') {
        this.route1 = location1.path();
        console.log(this.route1 + "000000000000000000");
      } else {
        this.route1 = this.fileside.dropdownValue
        console.log(this.route1 + "111111111111111111");
      }
      this.getRowHeight = function(params) {
        return 25;

      };
    });

    this.columnDefs = [
      {
        headerName: 'Action', field: '15',
        cellRenderer: function (params) {
          if(params.data[5]!='CLOSED')
            return '<button type="button" style="font-size:10px;padding-top:0px;padding-bottom:1px;" class="btn btn-secondary btn-sm" (onclick) = "closeJob($event)" id="button" >Close</button>'
          else
            return '<button type="button" style="font-size:10px;padding-top:0px;padding-bottom:1px;" class="btn btn-secondary btn-sm" (onclick) = "closeJob($event)" id="button" disabled>Close</button>'

        },
        width: 80, resizable: true,
      },
      {
        headerName: 'Job ID', field: '0', cellStyle: function(params) {
        
          return { 'font-size': '11px'}
      },
        cellRenderer: function (params) {
          return '<a routerLink="../Row" routerLinkActive="active">' + params.value + '</a>'
        },
        width: 72, filter: true, resizable: true
      },
      { headerName: 'Creation Time', field: '1',width:150, resizable: true,valueFormatter:DateFormatter, cellStyle: function(params) {
        
        return { 'font-size': '11px'}
    }},
        
       
      { headerName: 'Cob', field: '2', sortable: true,width:85, resizable: true, cellStyle: function(params) {
        
        return { 'font-size': '11px'}
    }},
      { headerName: 'Start Business Day', field: '3',width:150, resizable: true,valueFormatter:DateFormatter, cellStyle: function(params) {
        
        return { 'font-size': '11px'}
    }},
      { headerName: 'Stop Business Day', field: '4',width:150, resizable: true,valueFormatter:DateFormatter, cellStyle: function(params) {
        
        return { 'font-size': '11px'}
    }},
      { headerName: 'State', field: '5', width: 75, resizable: true, filter: true, cellStyle: function(params) {
        
        return { 'font-size': '11px'}
    }},
      { headerName: 'Source System', field: '6', width: 62, resizable: true, cellStyle: function(params) {
        
        return { 'font-size': '11px'}
    }},
      { headerName: 'Start Load', field: '7',width:150, resizable: true,valueFormatter:DateFormatter, cellStyle: function(params) {
        
        return { 'font-size': '11px'}
    }},
      { headerName: 'Stop Load', field: '8',width:150, resizable: true,valueFormatter:DateFormatter, cellStyle: function(params) {
        
        return { 'font-size': '11px'}
    }},
    //   { headerName: 'Start Convert', field: '9',width:150, resizable: true,valueFormatter:DateFormatter, cellStyle: function(params) {
        
    //     return { 'font-size': '11px'}
    // } },
    //   { headerName: 'Stop Convert', field: '10',width:150, resizable: true,valueFormatter:DateFormatter, cellStyle: function(params) {
        
    //     return { 'font-size': '11px'}
    // }},
      { headerName: 'Total Records', field: '11', width: 70, resizable: true, cellStyle: function(params) {
        
        return { 'font-size': '11px'}
    }},
      { headerName: 'Load Errors', field: '12', width: 55, resizable: true, cellStyle: function(params) {
        
        return { 'font-size': '11px'}
    }},
      { headerName: 'Convert Errors', field: '13', width: 70, resizable: true, cellStyle: function(params) {
        
        return { 'font-size': '11px'}
    }},
      { headerName: 'Archived', field: '14', width: 75, resizable: true, cellStyle: function(params) {
        
        return { 'font-size': '11px'}
    }},
    ];
    this.paginationPageSize = 20;
    function DateFormatter(params) {
      if(params.value!=null)
      return params.value+' '+'CEST';
    }
  }

  closeJob(event: Event) {
    console.log("-----------------------" + event);
    this._router.navigate(["/Instrument"]);
  }

  gridOption = {
    enableRangeSelection: true,
    allowContextMenuWithControlKey: true,
    menuIcon: 'fa-bars',
    rowData: null,
    enableFilter: true,
    
   
   
    

  }
  

  quickSearchValue: any = '';
  public onQuickFilterChanged() {
    this.gridOptions.rowData.filter(this.quickSearchValue);

  }

  onCellClicked(event: any) {
    console.log("Button clicked--------------------");
  }

  cellClicked(event: any) {

    this.event1 = event.data[0];
    console.log(event.data + "row clicked--------------------" + event.data[0] + event.data[15] + "---" + event.column.getId());
  }

  GetOpenJobs(e) {
    if (e.target.checked) {
      this.http
        .get(this.appConfigService.URL + "/Mandants/" + this.route.parent.snapshot.params['id'] + "/OpenJobs")
        .subscribe(data1 => {
          this.rowData = data1;
        });
    }
    else {
      this.http
        .get(this.appConfigService.URL + "/Mandants/" + this.route.parent.snapshot.params['id'] + "/Job")
        .subscribe(data1 => {
          this.rowData = data1;
        });
    }
  }

  onClickMe(start: Date, end: Date) {
    console.log(start.toDateString() + "-------------" + end);
    this.start_date = this.datepipe.transform(start, 'yyyy-MM-dd');
    this.end_date = this.datepipe.transform(end, 'yyyy-MM-dd');
    console.log(this.start_date)
    console.log(this.end_date)
    this.id = this.route.snapshot.paramMap.get('id');
    this.http
      .get(this.appConfigService.URL + "/Mandants/" + this.id + '/Jobs?FromDate=' + this.start_date + '&ToDate=' + this.end_date)
      .subscribe(data => {
        this.rowData = data;
      })
  }

  GetJobs(e) {
    if (e.target.checked) {
      this.http
        .get(this.appConfigService.URL + "/Mandants/" + this.route.parent.snapshot.params['id'] + "/OpenJobs")
        .subscribe(data1 => {
          this.rowData = data1;
        });
    }
    else {
      this.http
        .get(this.appConfigService.URL + "/Mandants/" + this.route1)
        .subscribe(data1 => {
          this.rowData = data1;
        });
    }
  }

  onRowClicked(event: any) {
    this.event1 = event.data[0];
    console.log('row', event.data[0]);
    console.log('event', this.event1);
    console.log(this.id = this.route.snapshot.paramMap.get('id'));
    this.router.navigate([this.route.snapshot.paramMap.get('id') + '/Job/' + this.event1 + '/TradeList']);
  }

  ngOnInit() {
    this.appConfigService.currentMessage.subscribe(message => this.message = message)
    console.log(this.message);
    console.log(this.id = this.route.snapshot.paramMap.get('id'));
    console.log("drovalue", this.fileside.dropdownValue);
    this.router.navigate([this.appConfigService.URL + this.route.snapshot.paramMap.get('id') + '/Job']);
    console.log("value", this.fileside.getData());
    var x = this.cookieService.get('cookieValue');
    console.log("data from cookies", x);
  }

  onPageSizeChanged(newPageSize) {
    var value = (<HTMLInputElement>document.getElementById("page-size")).value;
    this.gridApi.paginationSetPageSize(Number(value));
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
      this.gridApi.sizeColumnsToFit();
    // this.gridColumnApi.autoSizeColumns();
    // this.gridApi.refreshCells();
    const data = this.route.parent.snapshot.params['id'];
    console.log("this.route.params", this.route.parent.params);
    console.log("ID", data);
    console.log("datafrom component", this.dataFromParent);
    console.log("------------------------------StorageValue-------------------------------------", storageData);
    var datafrom = this.cookieService.get('cookieValue');
    if (this.message == "Get OpenJobs") {
      this.http
        .get(this.appConfigService.URL + "/Mandants/" + data + "/OpenJobs")
        .subscribe(data1 => {
          this.rowData = data1;
          params.api.paginationGoToPage(0);
        });
    }

    if (this.message == "Get Jobs") {
      this.http
        .get(this.appConfigService.URL + "/Mandants/" + this.route1)
        .subscribe(data1 => {
          this.rowData = data1;
          params.api.paginationGoToPage(0);
        });
    }

    if (this.message == "default message") {
      this.http
        .get(this.appConfigService.URL + "/Mandants/" + this.route1)
        .subscribe(data1 => {
          this.rowData = data1;
          params.api.paginationGoToPage(0);
        });
    }

    if (this.message == "Hello from Sibling" || this.message == "Hello from Sibling1") {
      this.http
        .get(this.appConfigService.URL + "/Mandants/" + this.route1)
        .subscribe(data1 => {
          this.rowData = data1;
          params.api.paginationGoToPage(0);
        });
    }
  }


  onBtnExportDataAsCsv(): void {
    const params = {
      columnGroups: true,
      allColumns: true,
      fileName: 'jobs data',
    };
    this.gridApi.exportDataAsCsv(params);
  }

  exportexcelJobs(): void {
    let Jobs = [];
    let Jobpage = this.gridApi.paginationGetCurrentPage();
    let Pagenumber = Jobpage * this.paginationPageSize;
    for (let i = Pagenumber; i <= Pagenumber + (this.paginationPageSize - 1); i++) {
      if (this.rowData[i])
        Jobs.push(this.rowData[i]);
    }
    console.log("Current Page Jobs", Jobs);
    this.gridApi.paginationGetCurrentPage();
    this.excelExportService.exportData(Jobs, new IgxExcelExporterOptions("Current_Page_jobs"));

  }

  exportexcelAllJobs() {
    this.excelExportService.exportData(this.rowData, new IgxExcelExporterOptions("All_Job_details"));
  }

}
