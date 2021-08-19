import { Component, OnInit } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Module, AllCommunityModules } from "@ag-grid-community/all-modules";
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { HttpClientService } from '../service/httpclient.service';
import { FileSideComponent } from '../file-side/file-side.component';

@Component({
  selector: 'app-limit-unit-stats',
  templateUrl: './limit-unit-stats.component.html',
  styleUrls: ['./limit-unit-stats.component.css']
})

export class LimitUnitStatsComponent implements OnInit {
  startdateValue: Date;
  enddateValue: Date;
  isShow = false;
  display1 = 'none';

  onClickMe() {
    this.display1 = 'flex';
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
  constructor(public http: HttpClient, public route: ActivatedRoute, private appConfigService: HttpClientService, public fileside: FileSideComponent) {
    this.columnDefs = [
      {
        headerName: 'Total', field: '0',
        cellRenderer: function (params) {
          return '<a routerLink="../Row" routerLinkActive="active">' + params.value + '</a>'
        },
        width: 75, filter: true, resizable: true
      },
      { headerName: 'Filtered automatically (Sum)', field: '2', sortable: true, resizable: true },
      { headerName: 'Auto mcc checked (Sum)', field: '7', resizable: true },
      { headerName: 'Manual checked (Sum)', field: '10', resizable: true },
      { headerName: 'Reclamation (Sum)', field: '12', resizable: true },
      { headerName: 'Reclamation open', field: '13', resizable: true },
      { headerName: 'Reclamation closed', field: '14', resizable: true },
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
    console.log("this.route.params", this.route.params);
    console.log("id", this.id);
    this.http
      .get(this.appConfigService.URL + "Mandants/" + this.id + '/Job')
      .subscribe(data => {
        this.rowData = data;
        params.api.paginationGoToPage(4);
      });

  }
  ngOnInit() {
  }

}