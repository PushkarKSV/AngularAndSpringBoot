import { Component, OnInit } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Module, AllCommunityModules } from "@ag-grid-community/all-modules";
import { ActivatedRoute, NavigationEnd } from '@angular/router';
import { Observable } from 'rxjs';
import { HttpClientService } from '../service/httpclient.service';
import { Router } from '@angular/router';

import { Location } from '@angular/common';
import { FileSideComponent } from '../file-side/file-side.component';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-search-trades',
  templateUrl: './search-trades.component.html',
  styleUrls: ['./search-trades.component.css']
})
export class SearchTradesComponent implements OnInit {

  public gridApi;
  public gridColumnApi;
  public modules: Module[] = AllCommunityModules;
  public columnDefs;
  public autoGroupColumnDef;
  public defaultColDef;
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
  constructor(public http: HttpClient, public route: ActivatedRoute, private appConfigService: HttpClientService,
    private location: Location, public fileside: FileSideComponent, public cookieService: CookieService, location1: Location, private router: Router) {
    router.events.subscribe((val) => {
      if (location1.path() != '') {
        this.route1 = location1.path();
        console.log(this.route1 + "000000000000000000");
      } else {
        this.route1 = this.fileside.dropdownValue
        console.log(this.route1 + "111111111111111111");
      }
      console.log((location1.path()) + "abcde");
      console.log(this.router.url);
    });

    this.columnDefs = [
      {
        headerName: 'Trade', field: '0',
        cellRenderer: function (params) {
          return '<a routerLink="../TradeList" routerLinkActive="active">' + params.value + '</a>'
        },
        width: 100, filter: true
      },
      { headerName: 'ISIN', field: '1' },
      { headerName: 'Currency', field: '2', sortable: true },
      { headerName: 'Portfolio', field: '3' },
      { headerName: 'Counterparty', field: '4' },
      { headerName: 'Volumn', field: '5', width: 100 },
      { headerName: 'Price', field: '6', width: 110 },
      { headerName: 'Trader', field: '7' },
      { headerName: 'Trade Time', field: '8' },
      { headerName: 'Amend Time', field: '9' },
    ];
    this.paginationPageSize = 50;

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
    console.log(this.id = this.route.snapshot.paramMap.get('id'));
    this.router.navigate([this.route.snapshot.paramMap.get('id') + '/Searchtrades/' + this.event1 + '/TradeDetails']);
  }


  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id')
    console.log(this.id = this.route.snapshot.paramMap.get('id'));
    console.log("drovalue", this.fileside.dropdownValue);
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
    this.gridApi.refreshCells();
    const data = this.route.parent.snapshot.params['id'];
    console.log("this.route.params", this.route.parent.params);
    console.log("ID", data);
    console.log("datafrom component", this.dataFromParent);
    console.log(this.appConfigService.URL + this.route + '/Searchtrades')
    console.log("------------------------------StorageValue-------------------------------------", storageData);
    var datafrom = this.cookieService.get('cookieValue');
    console.log(this.appConfigService.URL + this.id + '/Searchtrades')

    this.http
      .get(this.appConfigService.URL + this.id + '/Searchtrades')
      .subscribe(data1 => {
        this.rowData = data1;
        params.api.paginationGoToPage(4);
      });

  }
}


