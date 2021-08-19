import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material';
import { HttpClient } from '@angular/common/http';
import { HttpClientService } from '../service/httpclient.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})

export class HistoryComponent {
 
  rowDataUpdate:any;
  rowData:any;
  name:String;
  ResultData:any;
  selectedResultValue:String="";
  ResultData1:any;
  id:any;
  mandant:any;
  rowJobId:any[];
  rowTradeId:any[];
  LoggedInUser:String="";
  public domLayout;
  constructor(private httpClientService: HttpClientService,public route: ActivatedRoute,private dialogRef: MatDialogRef<HistoryComponent>,public http: HttpClient,private appConfigService: HttpClientService) {
    this.id = this.route.snapshot.paramMap.get('id');
    console.log(this.route.snapshot.paramMap.get('id')+this.route.snapshot.params['id']);
    // this.domLayout = 'autoHeight';
  }

  closeDialog() {
    this.dialogRef.close();
  }
  ngOnInit(){
    this.rowJobId=[];
    this.rowTradeId=[];
    this.LoggedInUser=this.httpClientService.UserLoggedIn;
this.rowData=this.rowDataUpdate;
let j = 0;
for(let row in this.rowData){
  console.log(this.rowData[row][0]+"--"+this.rowData[row][1]+this.mandant);
  this.rowJobId[j]=this.rowData[row][0];
  this.rowTradeId[j]=this.rowData[row][1];
  j=j+1;
}
this.http
      .get(this.appConfigService.URL + "Mandants/" + this.mandant + '/ResultandComment')
      .subscribe(data1 => {
        this.ResultData = data1
      })
  }

  columnDefs = [
    {
      headerName: 'Trade', field: '0',
      cellRenderer: function (params) {
        return '<a routerLink="../TradeList" routerLinkActive="active">' + params.value + '</a>'
      },
      width: 100, filter: true,
      resizable: true
    },
    { headerName: 'Job ID', field: '1', width: 100, filter: true,resizable: true },
    { headerName: 'ISIN', field: '2',width: 120, resizable: true },
    { headerName: 'Currency', field: '3', sortable: true,width: 90, resizable: true },
    { headerName: 'Portfolio', field: '4',width: 100, resizable: true },
    { headerName: 'Counterparty', field: '5', width: 110,resizable: true },
    { headerName: 'Volume', field: '6', width: 100, resizable: true },
    { headerName: 'Price', field: '7', width: 100, resizable: true },
    { headerName: 'Trader', field: '8', width: 90,resizable: true },
    { headerName: 'Trade Time', field: '9', resizable: true },
    { headerName: 'Amend Time', field: '10', resizable: true },

  ];

  tcode: any;
  comment: any;
  t:any[];
  c:any[];
  mandant1:any[];
  ResultAndComment: any[];
  comment1:any;
  message: any;  
  fileToUpload: File = null;
  fileMessage:String;
  Submit() {
    
    this.ResultAndComment = [];
    this.t=[];
    this.c=[];
    this.mandant1=[];
    this.t[0]=this.tcode;
    this.c[0]=this.selectedResultValue;
    this.mandant1[0]=this.mandant;
    if (this.tcode != null && this.selectedResultValue != null) {
      this.ResultAndComment[0] = this.c;
      this.ResultAndComment[1] = this.t;
      this.ResultAndComment[2] = this.mandant1;
      this.ResultAndComment[3] = this.LoggedInUser;
    }

//     console.log(this.rowJobId+"--"+this.rowTradeId)
this.comment1=[];
this.comment1[0]=this.rowJobId;
this.comment1[1]=this.rowTradeId;
this.comment1[2]=this.ResultAndComment[0];
this.comment1[3]=this.ResultAndComment[1];
this.comment1[4]=this.ResultAndComment[2];
this.comment1[5]=this.ResultAndComment[3];
console.log(this.comment1);
this.appConfigService.updateComments(this.comment1) 
    .subscribe(data=>
      console.log("success",data),
     error=>console.log("eror",error))
      console.log("submitted");
      if(this.fileToUpload!=null){
        const uploadImageData = new FormData();
        uploadImageData.append('File', this.fileToUpload, this.fileToUpload.name);   
            this.http.post('http://localhost:8080/Mandants/FileUpload', uploadImageData, { observe: 'response' }) .subscribe((response) => {
              if (response.status === 200) {this.message = 'Image uploaded successfully';
             } else {
               this.message = 'Image not uploaded successfully';
              }
               }  );
              }
      
    this.dialogRef.close();
  }

  onGridReady(params) {
     // Following line to make the currently visible columns fit the screen  
   params.api.sizeColumnsToFit();

   // Following line dymanic set height to row on content
   params.api.resetRowHeights();
  }

  fileEvent(files: FileList) {
    this.fileToUpload = files.item(0);
    console.log(this.fileToUpload);
  }
}