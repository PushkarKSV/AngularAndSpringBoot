import { Component, Input } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Module, AllCommunityModules } from "@ag-grid-community/all-modules";
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { FileSideComponent } from '../file-side/file-side.component';
import { IgxCsvExporterService, IgxExcelExporterOptions, IgxExcelExporterService } from "igniteui-angular";
import { HttpClientService } from '../service/httpclient.service';
import { Location } from '@angular/common';


@Component({
  selector: 'app-configuration',
  templateUrl: './configuration.component.html',
  styleUrls: ['./configuration.component.css']
})

export class ConfigurationComponent {
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
  route1: string;
  pageOfItems: Array<any>;
  public gridOptions: any;

  constructor(public http: HttpClient, public route: ActivatedRoute, private csvExportService: IgxCsvExporterService,
    private excelExportService: IgxExcelExporterService, private appConfigService: HttpClientService,
    private location1: Location, public fileside: FileSideComponent, router: Router) {
    router.events.subscribe((val) => {
      if (location1.path() != '') {
        this.route1 = location1.path();
        // console.log(this.route1 + "000000000000000000");
      } else {
        this.route1 = this.fileside.dropdownValue
        // console.log(this.route1 + "111111111111111111");
      }
    });

    this.columnDefs = [
      { headerName: 'Key', field: '0', sortable: true, width: 300, resizable: true, filter: true },
      { headerName: 'Value', field: '1', sortable: true, width: 300, resizable: true },
      {
        headerName: 'Pending Change', field: '', width: 100,
        cellRenderer: params => {
          return `<input type='checkbox'  ${params.value ? 'unchecked' : ''} />`;
        }
      },
      { headerName: 'Changed By', width: 300 }

    ];
    this.paginationPageSize = 25;
    this.gridOptions = {
      context: {
        componentParent: this
      },
    }

  }
  quickSearchValue: any = '';
  public onQuickFilterChanged() {
    this.gridOptions.columnDefs.filter(this.quickSearchValue);
  }

  onPageSizeChanged(newPageSize) {
    var value = (<HTMLInputElement>document.getElementById("page-size")).value;
    this.gridApi.paginationSetPageSize(Number(value));
  }



  onGridReady(params) {
    this.gridApi = params.api;
    this.gridColumnApi = params.columnApi;
    this.http
      .get(this.appConfigService.URL + "Mandants/" +this.route.snapshot.paramMap.get('id')+'/Configuration')
      .subscribe(data => {
        this.rowData = data;
      });
  }

  onBtnExportDataAsCsv(): void {
    const params = {
      columnGroups: true,
      allColumns: true,
      fileName: 'jobs data',

    };
    this.gridApi.exportDataAsCsv(params);
  }

  onChangePage(pageOfItems: Array<any>) {
    // update current page of items
    alert("Inside");
    this.pageOfItems = pageOfItems;
    console.log("this.pageOfItems", this.pageOfItems);

  }

  exportexcel(): void {
    let dataArray = [];
    let pageNo = this.gridOptions.api.paginationGetCurrentPage();
    let startValue = pageNo * this.paginationPageSize;
    for (let i = startValue; i <= startValue + (this.paginationPageSize - 1); i++) {
      if (this.rowData[i])
        dataArray.push(this.rowData[i]);
    }
    console.log("dataArray", dataArray);
    this.gridOptions.api.paginationGetCurrentPage();
    this.excelExportService.exportData(dataArray, new IgxExcelExporterOptions("ExportFileFromData"));
  }

  exportexcelAll() {
    this.excelExportService.exportData(this.rowData, new IgxExcelExporterOptions("ExportFileFromData"));
  }

}
