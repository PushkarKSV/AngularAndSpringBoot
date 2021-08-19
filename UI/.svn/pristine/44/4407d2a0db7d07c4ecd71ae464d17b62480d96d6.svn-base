import { Component, Input, OnInit } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Module, AllCommunityModules } from "@ag-grid-community/all-modules";
import { ActivatedRoute, Params, Router } from '@angular/router';
import { HttpClientService } from '../service/httpclient.service';
import { Observable, Subscription } from 'rxjs';
import { IgxCsvExporterService, IgxExcelExporterOptions, IgxExcelExporterService } from "igniteui-angular";
import { DatePipe } from '@angular/common';
import { HistoryComponent } from '../history/history.component';
import { MatDialog } from '@angular/material';

@Component({
  selector: 'app-trade-list',
  templateUrl: './trade-list.component.html',
  styleUrls: ['./trade-list.component.css']
})

export class TradeListComponent implements OnInit {
  path: any;
  message: any;
  mess: boolean;
  mess1: boolean;
  Mandant: any;
  startdateValue: Date;
  enddateValue: Date;
  start_date: any;
  end_date: any;
  fileToUpload: File = null;
  fileMessage:String;
  LoggedInUser:String="";
  getRowHeight: (params: any) => number;
  ngOnInit(): void {
    this.appConfigService.currentMessage.subscribe(message => this.message = message)
    console.log(this.message)
    console.log(this.set );
    this.id1 = this.route.snapshot.params['jobid'];
    if (this.id1 != null) {
      this.mess1 = true
    }

    if (this.id1 != null) {
      this.mess = true;
    }
    if (this.message === "Hello from Sibling") {
      this.mess = false
    }
    if (this.message === "Hello from Sibling1") {
      this.mess = true
    }
    if (this.path.includes('Searchtrades')) {
      this.mess = true
    }
    if (this.path.includes('Job')) {
      this.mess = true
    }

    this.id2 = this.route.snapshot.params['id'];
    console.log(this.id2)
    console.log(this.id1 + "abcdher");
    if (this.id1 == null) {
      this.value = true;
      console.log(this.value)
    }
    console.log(this.value)
  }
  public gridOptions: any;
  public value: boolean = false;
  @Input() childMessage: string; public gridApi;
  public gridColumnApi;
  public modules: Module[] = AllCommunityModules;
  public columnDefs;
  public columnDefsSelected;
  public autoGroupColumnDef;
  public defaultColDef;
  public paginationPageSize;
  public paginationNumberFormatter;
  public rowData: any;
  public rowData1: any;
  public rowDataSelected: any;
  id$: Observable<string>;
  id: string;
  id1: any;
  id2: string;
  event1: any;
  selectedResultValue: String = '';
  subscription: Subscription;

  constructor(public dialog: MatDialog,private httpClientService: HttpClientService, public http: HttpClient, public datepipe: DatePipe, public router: Router, public route: ActivatedRoute,
    private appConfigService: HttpClientService, private csvExportService: IgxCsvExporterService,
    private excelExportService: IgxExcelExporterService) {
    this.path = this.router.url;
    console.log(this.path)
    this.id = this.route.snapshot.paramMap.get('id')
    this.getRowHeight = function(params) {
      return 23;

    };

    this.columnDefs = [
      {
        field: 'RowSelect',
        headerName: ``,
        headerCheckboxSelection: true,
        headerCheckboxSelectionFilteredOnly: true,
        checkboxSelection: true,
        width: 50,

        
      },
      {
        headerName: 'Trade', field: '0',
        cellRenderer: function (params) {
          return '<a routerLink="../TradeList" routerLinkActive="active">' + params.value + '</a>'
        },
        width: 120, filter: true,
        resizable: true
      },
      { headerName: 'Job ID', field: '1', resizable: true,width:100 },
      { headerName: 'ISIN', field: '2', resizable: true,width:180 },
      { headerName: 'Currency', field: '3', sortable: true, resizable: true,width:80 },
      { headerName: 'Portfolio', field: '4', resizable: true,width:150 },
      { headerName: 'Counterparty', field: '5', resizable: true,width:120 },
      { headerName: 'Volume', field: '6', width: 150, resizable: true},
      { headerName: 'Price', field: '7', width: 110, resizable: true },
      { headerName: 'Trader', field: '8',width:100, resizable: true },
      { headerName: 'Trade Time', field: '9',width:160, resizable: true,valueFormatter:DateFormatter },
      { headerName: 'Amend Time', field: '10',width:160, resizable: true,valueFormatter:DateFormatter },

    ];
    this.paginationPageSize = 20;
    function DateFormatter(params) {
      return params.value+' '+'CEST';
    }
  }
 


  gridOption = {
    enableRangeSelection: true,
    allowContextMenuWithControlKey: true,
    // rowData: this.rowData,
    enableFilter: true
  }

  quickSearchValue: any = '';
  public onQuickFilterChanged() {
    this.gridOptions.rowData.filter(this.quickSearchValue);
  }

  rowevent1: any[5];
  set = new Set();
  rowDataStore: Array<any> = [];
  selectedRows1: any[];
  selectedRows1JobId: any[];
  selectedRows2: any[];
  selectedRowsAll: any[];
  setSelected1 = new Set();
  setSelected2 = new Set();
  setSelectedJobId: any[];
  setselected2Size: Number;
  flag: boolean;
  ResultField: any;
  rowJobId:any[];
  rowTradeId:any[];
  showTrade:boolean=false;
  submitTrade:boolean=false;
  someMethod() {
    i: Number;
    let i = 0;
    this.setSelected2.clear();
    let selectedRows;
    this.selectedRowsAll = [];
    this.rowJobId=[];
    this.rowTradeId=[];
    selectedRows = this.gridApi.getSelectedRows();
    console.log(selectedRows);
    j: Number;
    let j = 0;
    selectedRows.map((row) => {
      console.log(row[0]);
      this.selectedRowsAll[j] = selectedRows;
      this.setSelected2.add(row[0]);
      this.rowJobId[j]=row[0];
      this.rowTradeId[j]=row[1];
      j = j + 1;
    });
    this.flag = true;
    console.log(this.setSelected2 + "-----" + this.setSelectedJobId);
    this.selectedRows1 = [];
    for (let set of this.setSelected2) {
      str: String;
      console.log(set);
      let str = set;
      this.selectedRows1[i] = str;
      i = i + 1;
    }
    this.selectedRows1JobId = [];
    let k = 0;
    for (let row of this.selectedRowsAll[0]) {
      console.log(row);
      this.selectedRows1JobId[k] = row[1];
      k = k + 1;
    }
    console.log(this.selectedRows1);
    console.log(this.selectedRows1JobId);
    this.setselected2Size = this.setSelected2.size;
    console.log(this.selectedRowsAll+"-="+this.LoggedInUser);
    // this.http
    //   .get(this.appConfigService.URL + "Mandants/" + this.id + '/ResultandComment')
    //   .subscribe(data1 => {
    //     this.ResultField = data1
    //   })
      // this.rowData=[];
      // this.rowData=this.gridApi.getSelectedRows();
      this.submitTrade=!this.submitTrade;
      const dialogRef =this.dialog.open(HistoryComponent);
      let instance = dialogRef.componentInstance;
    instance.rowDataUpdate = this.gridApi.getSelectedRows();
    instance.mandant = this.route.snapshot.paramMap.get('id');
    // instance.rowJobId1=this.rowJobId;
    // instance.rowTradeId1=this.rowTradeId;
    // instance.ResultData = this.ResultField;
    console.log('dialogRef',dialogRef);
  }

  Reset() {
    this.setSelected1.clear();
    this.setSelected2.clear();
    this.selectedRows1 = null;
    this.selectedRows1JobId = null;
    this.flag = false;
    this.tcode = null;
    this.flagSave = false;
  }

  tcode: any;
  comment: any;
  t:any[];
  c:any[];
  mandant:any[];
  ResultAndComment: any[];
  comment1:any;
  
  Submit() {
    console.log(this.LoggedInUser);
    if(this.fileToUpload!=null){
      const uploadImageData = new FormData();
      const man=this.selectedResultValue;
    uploadImageData.append('File', this.fileToUpload, this.fileToUpload.name);   
      this.http.post('http://localhost:8080/Mandants/FileUpload', uploadImageData, { observe: 'response' }) .subscribe((response) => {
        if (response.status === 200) {this.message = 'Image uploaded successfully';
       } else {
         this.message = 'Image not uploaded successfully';
        }
         }  );
        }
    console.log(this.selectedRows1 + "--------------------------- :" + this.tcode);
    console.log(this.comment+"--"+this.rowJobId);
    this.ResultAndComment = [];
    this.t=[];
    this.c=[];
    this.mandant=[];
    this.t[0]=this.tcode;
    this.c[0]=this.selectedResultValue;
    this.mandant[0]=this.route.snapshot.paramMap.get('id')
    if (this.tcode != null && this.selectedResultValue != null) {
      this.ResultAndComment[0] = this.c;
      this.ResultAndComment[1] = this.t;
      this.ResultAndComment[2] = this.mandant;
      this.ResultAndComment[3] = this.LoggedInUser;
    }

    console.log(this.rowJobId+"--"+this.rowTradeId)
this.comment1=[];
this.comment1[0]=this.rowJobId;
this.comment1[1]=this.rowTradeId;
this.comment1[2]=this.ResultAndComment[0];
this.comment1[3]=this.ResultAndComment[1];
this.comment1[4]=this.ResultAndComment[2];
// this.comment1[5]=this.ResultAndComment[2];

if(this.tcode!=null && this.selectedResultValue!=null){
this.appConfigService.updateComments(this.comment1) 
    .subscribe(data=>
      console.log("success",data),
     error=>console.log("eror",error))
      console.log("submitted");
}


    this.setSelected1.clear();
    this.setSelected2.clear();
    this.selectedRows1 = null;
    this.selectedRows1JobId = null;
    this.flag = false;
    this.tcode = null;
    this.flagSave = false;
    this.showTrade=false;
    this.submitTrade=false;
  }


  handleSuccessfulResponse(response) {
    console.log(response + "--------");
  }

  flagSave: boolean = false;
  checkboxCount: number = 0;
  onSelectionChanged(event: any) {
    this.flagSave = true;
    this.checkboxCount = this.checkboxCount + 1;
    console.log(event);
    this.showTrade=true;
  }

  onRowClicked(event: any) {
    this.event1 = event.data[0];
    var Jobid = event.data[1];
    this.httpClientService.jobID = event.data[1];
    console.log(this.route.snapshot.paramMap.get('id'));
    console.log(this.route.snapshot.paramMap.get('jobid'));
    console.log('row', event.data[0] + event.data[1]);
    console.log('event', this.event1);
    this.path = this.router.url;
    if (this.path.includes('Job')) {
      this.router.navigate([this.route.snapshot.paramMap.get('id') + '/Job/' + this.id1 + '/TradeList/' + this.event1 + '/TradeDetails']);
    }
    else if (this.path.includes('Searchtrades')) {
      this.router.navigate([this.route.snapshot.paramMap.get('id') + '/Searchtrades/' + Jobid + '/' + this.event1 + '/TradeDetails']);
    }
    else {
      this.router.navigate([this.route.snapshot.paramMap.get('id') + '/TradeList/' + Jobid + '/' + this.event1 + '/TradeDetails']);
    }
  }

  allTrades(e) {
    if (e.target.checked) {
      this.http
        .get(this.appConfigService.URL + "Mandants/" + this.id + '/OpenTrades')
        .subscribe(data => {
          if (data) {
            this.rowData = data;
          }
          else {
            this.rowData = null;
          }
        })
    }
    else {
      this.http
        .get(this.appConfigService.URL + "Mandants/" + this.id + '/AllTrades')
        .subscribe(data1 => {
          if (data1) {
            this.rowData = data1;
          }
          else {
            this.rowData = null;
          }
        })
    }
  }

  onPageSizeChanged(newPageSize) {
    var value = (<HTMLInputElement>document.getElementById("page-size")).value;
    this.gridApi.paginationSetPageSize(Number(value));
  }

  onGridReady(params) {
    this.gridApi = params.api;
    this.gridColumnApi = params.columnApi;
       this.gridApi.sizeColumnsToFit();
    //  params.columnApi.autoSizeAllColumns();
    this.id = this.route.snapshot.paramMap.get('id');
    console.log("this.route.params", this.route.params);
    console.log("id", this.id);
    if (this.message === "Hello from Sibling" && this.id1 == null && !(this.path.includes('Searchtrades'))) {
      console.log(this.mess + this.message + this.mess1);
      console.log(this.id);
      this.http
        .get(this.appConfigService.URL + "Mandants/" + this.id + '/OpenTrades')
        .subscribe(data => {
          if (data) {
            this.rowData = data;
          }
          else {
            this.rowData = null;
          }
        });

    }
    if (this.message === "default message" && this.id1 == null) {
      console.log(this.mess + this.message + this.mess1);
      this.http
        .get(this.appConfigService.URL + "Mandants/" + this.id + '/OpenTrades')
        .subscribe(data => {
          if (data) {
            this.rowData = data;
            this.rowData1 = data;
          }
          else {
            this.rowData = null;
          }
        });
    }
    if (this.path.includes('Searchtrades')) {
      console.log(this.mess + this.message + this.mess1);
      this.http
        .get(this.appConfigService.URL + "Mandants/" + this.id + '/AllTrades')
        .subscribe(data => {
          if (data) {
            this.rowData = data;
          }
          else {
            this.rowData = null;
          }
        });
    }

    if (this.message === "Hello from Sibling1" && this.id1 == null) {
      console.log(this.mess + this.message + this.mess1);
      this.http
        .get(this.appConfigService.URL + "Mandants/" + this.id + '/AllTrades')
        .subscribe(data => {
          if (data) {
            this.rowData = data;
          }
          else {
            this.rowData = null;
          }
        });
    }
    if (this.id1 != null) {
      console.log(this.mess + this.message + this.mess1);
      this.http
        .get(this.appConfigService.URL + "Mandants/" + this.id + '/Job/' + this.id1 + '/TradeList')
        .subscribe(data => {
          if (data) {
            this.rowData = data;
          }
          else {
            this.rowData = null;
          }
        });
    }
  }


  exportexcelTrades(): void {
    let Trades = [];
    let Tradepagenumber = this.gridApi.paginationGetCurrentPage();
    let Currentpagenumber = Tradepagenumber * this.paginationPageSize;
    for (let i = Currentpagenumber; i <= Currentpagenumber + (this.paginationPageSize - 1); i++) {
      if (this.rowData[i])
        Trades.push(this.rowData[i]);
    }
    console.log("dataArray1", Trades);
    this.gridApi.paginationGetCurrentPage();
    this.excelExportService.exportData(Trades, new IgxExcelExporterOptions("ExportFileFromData"));
  }

  onClickMe(start: Date, end: Date) {
    console.log(start.toDateString() + "-------------" + end);
    this.start_date = this.datepipe.transform(start, 'yyyy-MM-dd');
    this.end_date = this.datepipe.transform(end, 'yyyy-MM-dd');
    console.log(this.start_date)
    console.log(this.end_date)
    this.id = this.route.snapshot.paramMap.get('id');
    this.http
      .get(this.appConfigService.URL + "Mandants/" + this.id + '/Trade?FromDate=' + this.start_date + '&ToDate=' + this.end_date)
      .subscribe(data => {
        this.rowData = data;
      })
  }


  exportexcelAllTrades() {
    this.excelExportService.exportData(this.rowData, new IgxExcelExporterOptions("ExportFileFromData"));
  }

  fileEvent(files: FileList) {
    this.fileToUpload = files.item(0);
    console.log(this.fileToUpload);
  }
}