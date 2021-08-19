import { Component, OnInit } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Module, AllCommunityModules } from "@ag-grid-community/all-modules";
import { ActivatedRoute } from '@angular/router';
import { HttpClientService } from '../service/httpclient.service';

@Component({
  selector: 'app-storno-group',
  templateUrl: './storno-group.component.html',
  styleUrls: ['./storno-group.component.css']
})
export class StornoGroupComponent implements OnInit {
  public NOJobId: any;
  gridOptions = {
    enableRangeSelection: true,
    getContextMenuItems: this.getContextMenuItems,
    allowContextMenuWithControlKey: true
  };

  getContextMenuItems() {

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
  constructor(public http: HttpClient, public route: ActivatedRoute, private appConfigService: HttpClientService) {
    this.columnDefs = [
      { headerName: 'Trade', field: '0', resizable: true },
      { headerName: 'Reference Trade', field: '1', resizable: true },
      { headerName: 'Field Name', field: '2', sortable: true, resizable: true },
      { headerName: 'Field Value', field: '3', resizable: true },
      { headerName: 'Reference Value', field: '4', resizable: true },
      { headerName: 'Field Value Change', field: '5', resizable: true },
    ];

  }

  ngOnInit() {
    console.log(this.route.snapshot.params['id']);
    console.log(this.route.snapshot.params['jobid'] + this.route.snapshot.paramMap.get('jobid'));
    console.log(this.route.snapshot.params['tradeid'] + this.route.snapshot.params['job1.t02_SRC_TRADE_ID']);
    console.log(this.route.snapshot.params['tradeid1'] + this.route.snapshot.paramMap.get('trade1id'));
  }
  
  onGridReady(params) {
    this.NOJobId = this.appConfigService.jobID;
    var jobid = this.route.snapshot.params['jobid'];
    console.log(this.route.snapshot.params['id'] + this.route.snapshot.params['job1.t02_SRC_TRADE_ID'] + this.route.snapshot.params['tradeid']
      + this.route.snapshot.paramMap.get('job1.t02_SRC_TRADE_ID') + this.route.snapshot.paramMap.get('tradeid'));
    if (this.route.snapshot.params['tradeid']) {
      console.log(this.route.snapshot.params['job1.t02_SRC_TRADE_ID'] + this.route.snapshot.params['tradeid']);
      this.http
        .get(this.appConfigService.URL + "Mandants/" + this.route.snapshot.params['id'] + "/Job/" + this.route.snapshot.params['Jobid'] + '/Trade/' + this.route.snapshot.params['tradeid'] + '/StornoGroup')
        .subscribe(data => {
          this.rowData = data;
          params.api.paginationGoToPage(4);
        });

    }
    if (this.route.snapshot.params['job1.t02_SRC_TRADE_ID']) {
      console.log(this.route.snapshot.params['job1.t02_SRC_TRADE_ID'] + this.route.snapshot.params['tradeid']);
      this.http
        .get(this.appConfigService.URL + "Mandants/" + this.route.snapshot.params['id'] + "/Job/" + this.route.snapshot.params['Jobid'] + '/Trade/' + this.route.snapshot.params['job1.t02_SRC_TRADE_ID'] + '/StornoGroup')
        .subscribe(data => {
          this.rowData = data;
          params.api.paginationGoToPage(4);
        });
    }
    if (this.route.snapshot.params['jobid'] != null) {
      console.log(this.route.snapshot.params['tradeid'] + this.route.snapshot.params['job1.t02_SRC_TRADE_ID']);
      this.http
        .get(this.appConfigService.URL + "Mandants/" + this.route.snapshot.params['id'] + '/Job/' + this.route.snapshot.params['jobid'] + '/Trade/' + this.route.snapshot.params['tradeid'] + '/StornoGroup')
        .subscribe(data => {
          this.rowData = data;
          params.api.paginationGoToPage(4);
        });
    }
  }
}
