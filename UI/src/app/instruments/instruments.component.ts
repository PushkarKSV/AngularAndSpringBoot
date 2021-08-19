import { Component, Input } from '@angular/core';
import { HttpClient } from "@angular/common/http";

import { Module, AllCommunityModules } from "@ag-grid-community/all-modules";
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { FileSideComponent } from '../file-side/file-side.component';
import { IgxCsvExporterService, IgxExcelExporterOptions, IgxExcelExporterService } from "igniteui-angular";
import { HttpClientService } from '../service/httpclient.service';
import { Location, PercentPipe } from '@angular/common';

import { ButtonRendererComponent } from './Renderer/button-renderer/button-renderer.component';



@Component({
  selector: 'app-instruments',
  templateUrl: './instruments.component.html',
  styleUrls: ['./instruments.component.css']
})

export class InstrumentsComponent {
  public gridApi;
  public gridColumnApi;
  public modules: Module[] = AllCommunityModules;
  public columnDefs;
  public autoGroupColumnDef;
  public defaultColDef;
  public paginationPageSize;
  public paginationNumberFormatter;
  public rowData: any;
  public cellValue:boolean=true;
  public priceCheck;
public context:any;
frameworkComponents:any;
modifiedRows:any;
update:boolean=true;
submit:boolean=false;
cancel:boolean=false;



  id$: Observable<string>;
  id: string;
  event1: any;

  pageOfItems: Array<any>;
  route1: string;
  public gridOptions: any;
  categoryRowData: any;
  objCategoryMappings: any;
  getRowHeight: (params: any) => number;


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
      this.frameworkComponents = {
        buttonRenderer: ButtonRendererComponent,
      }
      this.getAllpricecheckCategory();  
      this.getRowHeight = function(params) {
        return 24.5;
  
      };
     
     
    });
    function percentageFormatter(params) {
      return params.value+' '+'percent';
    }

    this.paginationPageSize = 20;
    this.gridOptions = {
      enableRangeSelection: true,
      // rowMultiSelectWithClick: true
    allowContextMenuWithControlKey: true,
      context: {
        componentParent: this
      },
    //  components:{
    //     buttonRenderer: ButtonRendererComponent
    // }
    }
  }
 
  
  quickSearchValue: any = '';
  public onQuickFilterChanged() {
    this.gridOptions.columnDefs.filter(this.quickSearchValue);
  }
  onCellValueChanged(params) {
    this.cellValue=true;
    var colId = params.column.getId();
    console.log(colId)

  this.priceCheck=params.data;
    console.log(this.priceCheck);
   
    params.data.modified = true; 
    // const gridData = this.getAllData();
    this.id = this.route.snapshot.paramMap.get('id');
    // this.appConfigService.save(this.id,this.priceCheck).subscribe(savedpriceCheck=>{
    //   console.log('Data Saved');
     
    // },
    // error => console.log(error)
  
    // );
   
    
  }
  
  getAllpricecheckCategory()
  {
    this.id = this.route.snapshot.paramMap.get('id');
    this.appConfigService.pricecheckCategory(this.id).subscribe(getpriceCheck=>{
      console.log(getpriceCheck)
      this.categoryRowData =getpriceCheck;               
      // this.objCategoryMappings["f"] = "No";
      //this.objCategoryMappings["t"] = "Yes";    
      this.createColumnsDefinition() ;            
  },
      (error) => { alert(error) });
  }
  createColumnsDefinition(){
      
    this.columnDefs = [
      { headerName: 'Instrument', field: 't05_instrument', sortable: true, width: 200, resizable: true, filter: true },
      { headerName: 'Instrument Name', field: 't05_instrument_name', sortable: true, width: 200, resizable: true },
      { headerName: 'PricecheckCategory', field: 't11_name', sortable: true, width: 200, resizable: true, 
      editable:true,
      cellEditor: 'agSelectCellEditor',
            // valueFormatter:percentageFormatter,
      cellEditorParams: {
        // values: this.extractValues(this.objCategoryMappings), 
      
       values:this.categoryRowData
       // values: ['0.1 percent','0.2 percent','0.3 percent','0.4 percent ','0.5 percent','0.1 percent','0.2 percent','0.3 percent','0.4 percent ','0.5 percent']
      },
      refData: this.objCategoryMappings,

      // editable: function(params) {
      //   // allow `min_value` cell to be edited for rows with correct `validation_tagPopupSelectCellEditorype`
      //   return params.node.data.validation_type === 'NUMERIC'; 
      // }
    },
      {
        headerName: 'Pending Change', field: '', width: 100, resizable: true,
        cellRenderer: params => {
          return `<input type='checkbox'  ${params.value ? 'unchecked' : ''} />`;
        }
      },
     
      { headerName: 'Changed By', field:'t05_CHANGED_BY',width: 150, resizable: true },
      { headerName: 'Changed Date', field:'t05_CHANGED_DATE',width: 150, resizable: true },
      
      // {
      //   headerName: 'Action',
      //   cellRenderer: 'buttonRenderer',
      //   cellRendererParams: {
      //     onClick: this.onSubmit.bind(this),
      //     label: 'Click 1'
      //   }
    //},
        
        
      
      
      

    ];
  }
    
  extractValues(mappings) {
    return Object.keys(mappings);
}


//   onSubmit() {
//    console.log(this.priceCheck)
//     this.id = this.route.snapshot.paramMap.get('id');
//     this.appConfigService.save(this.id,this.priceCheck).subscribe(savedpriceCheck=>{
//       console.log('Data Saved');
     
//     },
//     error => console.log(error)
// );

  
    
//   }

onSubmit()
{  this.gridApi.stopEditing();
  let rowData = [];
  this.gridApi.forEachNode(node => rowData.push(node.data));
   this.modifiedRows = rowData.filter(row => row['modified']);
  // this.rowData = this.gridApi.getSelectedRows();
        this.gridApi.setRowData(this.modifiedRows);
        this.submit=true;
        this.cancel=true
        this.update=false;

}
  onRowClicked(event: any)
  {
    this.cellValue=true;
  }
  
getAllData() {
  this.gridApi.stopEditing();
  let rowData = [];
  this.gridApi.forEachNode(node => rowData.push(node.data));
   this.modifiedRows = rowData.filter(row => row['modified']);
  console.log(rowData)
  console.log(this.modifiedRows)
  this.id = this.route.snapshot.paramMap.get('id');
  this.appConfigService.save(this.id,this.modifiedRows).subscribe(savedpriceCheck=>{
    console.log('Data Saved');
 
  error => console.log(error)
  
  });
this.refresh()

  this.submit=false;
  this.cancel=false;
this.update=true;
 

}
refresh()

{

  console.log("Hi")

    this.http
      .get(this.appConfigService.URL + "Mandants/" +this.route.snapshot.paramMap.get('id')+'/Instrument')
      .subscribe(data => {
        this.rowData = data;
        console.log(this.rowData)
      });
//   this.http
//   .get(this.appConfigService.URL + "Mandants/" +this.route.snapshot.paramMap.get('id')+'/Instrument')
//   .subscribe(data => {

// this.gridApi.setRowData([])
//     this.rowData = data;
//    console.log(this.rowData)
   
//   });
 
this.gridOptions.api.setRowData(this.rowData);
  
  
}



  onPageSizeChanged(newPageSize) {
    var value = (<HTMLInputElement>document.getElementById("page-size")).value;
    this.gridApi.paginationSetPageSize(Number(value));
  }

  onGridReady(params) {
    this.gridApi = params.api;
    this.gridColumnApi = params.columnApi;
    this.id = this.route.snapshot.paramMap.get('id');

    this.http
      .get(this.appConfigService.URL + "Mandants/" +this.route.snapshot.paramMap.get('id')+'/Instrument')
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
  onBtWhich() {
 var cellDefs =this. gridOptions.api.getEditingCells();
cellDefs.forEach(function(cellDef) {
      console.log(cellDef.rowIndex);
      console.log(cellDef.column.getId());
      console.log(cellDef.floating);
     
  }
  );

}
reset()
{
  this.http
  .get(this.appConfigService.URL + "Mandants/" +this.route.snapshot.paramMap.get('id')+'/Instrument')
  .subscribe(data => {
    this.rowData = data;
  });
  this.submit=false;
  this.cancel=false;
  this.update=true;
}


}
