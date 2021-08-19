import { Component, OnInit } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Module, AllCommunityModules } from "@ag-grid-community/all-modules";
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { HttpClientService } from '../service/httpclient.service';
import { FileSideComponent } from '../file-side/file-side.component';
import { DatePipe } from '@angular/common'

@Component({
  selector: 'app-business-day',
  templateUrl: './business-day.component.html',
  styleUrls: ['./business-day.component.css']
})

export class BusinessDayComponent implements OnInit {
  startdateValue: Date;
  enddateValue: Date;
  isShow = false;
  display1 = 'none';
  startDateConvert: any;
  endDateConvert: any;
  start_date: any
  end_date: any
  onClickMe(start: Date, end: Date) {
    console.log(start.toDateString() + "-------------" + end);
    this.display1 = 'flex';
    console.log(this.appConfigService.URL + 'Bonds CBB' + '/trade?FromDate=' + this.startDateConvert + '&ToDate=' + this.endDateConvert)
    this.start_date = this.datepipe.transform(start, 'yyyy-MM-dd');
    this.end_date = this.datepipe.transform(end, 'yyyy-MM-dd');
    console.log(this.start_date)
    console.log(this.end_date)
    this.id = this.route.snapshot.paramMap.get('id');
    this.http
      .get(this.appConfigService.URL + this.id + '/trade?FromDate=' + this.start_date + '&ToDate=' + this.end_date)
      .subscribe(data => {
        this.rowData = data;
      })
  }

  public gridApi;
  public gridColumnApi;
  public modules: Module[] = AllCommunityModules;
  public columnDefs;
  public autoGroupColumnDef;
  public defaultColDef;
  public paginationPageSize;
  public paginationNumberFormatter;
  public rowData: any;
  id$: Observable<string>;
  id: string;
  event1: any;
  constructor(public http: HttpClient, public route: ActivatedRoute, private appConfigService: HttpClientService, public datepipe: DatePipe, public fileside: FileSideComponent) {
    this.columnDefs = [
      {
        field: 'RowSelect',
        headerName: ``,
        headerCheckboxSelection: true,
        headerCheckboxSelectionFilteredOnly: true,
        checkboxSelection: true,
        width: 50
      },
      {
        headerName: 'Trade', field: '0',
        cellRenderer: function (params) {
          return '<a routerLink="../TradeList" routerLinkActive="active">' + params.value + '</a>'
        },
        width: 100, filter: true,
        resizable: true
      },
      { headerName: 'ISIN', field: '1', resizable: true },
      { headerName: 'Currency', field: '2', sortable: true, resizable: true },
      { headerName: 'Portfolio', field: '3', resizable: true },
      { headerName: 'Counterparty', field: '4', resizable: true },
      { headerName: 'Volume', field: '5', width: 100, resizable: true },
      { headerName: 'Price', field: '6', width: 110, resizable: true },
      { headerName: 'Trader', field: '7', resizable: true },
      { headerName: 'Trade Time', field: '8', resizable: true },
      { headerName: 'Amend Time', field: '9', resizable: true },

    ];
    this.paginationPageSize = 25;
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
  onRowClicked(event: any) {
    this.event1 = event.data[0];
    console.log('row', event.data[0]);
    console.log('event', this.event1);
  }

  onPageSizeChanged(newPageSize) {
    var value = (<HTMLInputElement>document.getElementById("page-size")).value;
    this.gridApi.paginationSetPageSize(Number(value));
  }

  onGridReady(params) {
    this.gridApi = params.api;
    this.gridColumnApi = params.columnApi;
    this.id = this.route.snapshot.paramMap.get('id');
    //const params1 = new HttpParams().set('client', mantant[0]);
    console.log("this.route.params", this.route.params);
    console.log("id", this.id);
    this.http
      .get(this.appConfigService.URL + 'Bonds CBB' + '/trade?FromDate=' + this.start_date + '&ToDate=' + this.end_date)
      .subscribe(data => {
        this.rowData = data;
        params.api.paginationGoToPage(4);
      });
    console.log(this.start_date)
    console.log(this.appConfigService.URL + 'Bonds CBB' + '/trades?FromDate=2020-02-20&ToDate=2020-06-21')

  }
  ngOnInit() {
  }
}
