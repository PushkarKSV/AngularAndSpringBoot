import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { HttpClientService } from '../service/httpclient.service';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class OverviewComponent implements OnInit {
  public Overview: String;
  public assetsData: any;
  id: any;
  id1: any;
  Mandant: any;
  editFlag: boolean = false;
  selectedResultValue: String;
  Comment: any;
  tcode: any;
  ResultAndComment: any[];
  public NOJobId: any;
  ResultField: any;
  path: any;
  public value: boolean = false;
  val: number;
  val1: number;
  fileToUpload: File = null;
  message:String;
  LoggedInUser:String="";
  constructor(private httpClientService: HttpClientService, public route: ActivatedRoute, public http: HttpClient, private appConfigService: HttpClientService, private router: Router) { }
  ngOnInit() {
    console.log(this.httpClientService.UserLoggedIn);
    this.LoggedInUser=this.httpClientService.UserLoggedIn;
    this.path = this.router.url;
    if (this.path.includes('Searchtrades')) {
      this.val = 1;
    }
    if (this.path.includes('Job')) {
      this.val = 0;
    }
    if (this.path.includes('TradeList') && !this.path.includes('Job')) {
      this.val1 = 1;
    }
    if (!(this.path.includes('TradeList') || this.path.includes('Job') || this.path.includes('Searchtrades'))) {
      this.val1 = 2;

    }
    this.id1 = this.route.snapshot.params['id'];
    console.log(this.route.snapshot.params['id']);
    this.id = this.route.snapshot.params['job1.t02_SRC_TRADE_ID'];
    if (this.id1 == null) {
      this.value = true;
      console.log(this.value)
    }

    console.log(this.id1 + "abcgd")
    console.log(this.id + "HIIII");

    this.Overview="";
    this.path = this.router.url;
    console.log(this.httpClientService.jobID + "----" + this.path);
    this.NOJobId = this.httpClientService.jobID;
    this.id = this.route.snapshot.params['job1.t02_SRC_TRADE_ID']
    this.id1 = this.route.snapshot.params['tradeid']
    console.log(this.id + "000000000000000000000" + this.route.snapshot.paramMap.get('jobid'));
    this.Mandant = this.route.snapshot.params['id'];
    console.log(this.route.snapshot.params['id']);

    this.http
      .get(this.appConfigService.URL + "Mandants/" + this.route.snapshot.params['id'] + '/ResultandComment')
      .subscribe(data1 => {
        this.handleSuccessfulResponseR(data1)
      });
    console.log(this.ResultField + "0000000");

    if (this.id1 == null) {
      console.log(this.id1 + "0000000000")
      this.http
        .get(this.appConfigService.URL + 'Mandants/' + this.route.snapshot.params['id'] + '/Job/' + this.route.snapshot.paramMap.get('Jobid') + '/Trade/' + this.id + '/Overview')
        .subscribe(data1 => {
          this.handleSuccessfulResponse(data1)
        });
    }

    if (this.id1 != null) {
      console.log(this.id1 + "11111111")
      this.http
        .get(this.appConfigService.URL + 'Mandants/' + this.route.snapshot.params['id'] + '/Job/' + this.route.snapshot.paramMap.get('jobid') + '/Trade/' + this.id1 + "/Overview")
        .subscribe(data1 => {
          this.handleSuccessfulResponse(data1)
        });
    }

    if (this.route.snapshot.params['id'] == "Derivate EAA" || this.route.snapshot.params['id'] == "Derivative CBB" || this.route.snapshot.params['id'] == "Derivative PAG") {
      console.log(this.route.snapshot.params['job1.t02_SRC_TRADE_ID'] + "AssetsData---------" + this.route.snapshot.params['id'] + this.route.snapshot.params['jobid'] + this.route.snapshot.params['tradeid']);
      if (this.route.snapshot.params['job1.t02_SRC_TRADE_ID']) {
        console.log(this.route.snapshot.params['tradeid'] + this.route.snapshot.params['job1.t02_SRC_TRADE_ID']);
        this.http
          .get(this.appConfigService.URL + 'Mandants/' + this.route.snapshot.params['id'] + '/Job/' + this.NOJobId + '/Trade/' + this.route.snapshot.params['job1.t02_SRC_TRADE_ID'] + "/AssetsData")
          .subscribe(data => {
            this.assetsData = data;
          });
      }

      else {
        console.log(this.route.snapshot.params['tradeid'] + this.route.snapshot.params['job1.t02_SRC_TRADE_ID']);
        this.http
          .get(this.appConfigService.URL + 'Mandants/' + this.route.snapshot.params['id'] + '/Job/' + this.route.snapshot.params['jobid'] + '/Trade/' + this.route.snapshot.params['tradeid'] + "/AssetsData")
          .subscribe(data => {
            this.assetsData = data;
          });
      }
    }

  }

  handleSuccessfulResponse(response) {
    console.log(response + "**********");
    this.Overview = response;
    console.log(this.Overview);
  }

  handleSuccessfulResponseR(response) {
    console.log(response + "**********");
    this.ResultField = response;
  }

  handleSuccessfulResponseAssetsData(response) {
    console.log(response + "**********Assets");
    this.assetsData = response;
    console.log(this.assetsData);
  }

  EditAndSave() {
    this.editFlag = !this.editFlag;
  }

  jobId: any[];
  tradeId: any[];
  result: any[];
  comment: any[];
  comment1: any;
  mandant: any[];
  t: any[];
  c: any[];


  Save() {
    // this.appConfigService.postFile(this.fileToUpload).subscribe(data => {
    //   // do something, if upload success
    //   }, error => {
    //     console.log(error);
    //   });
    this.jobId = [];
    this.tradeId = [];
    this.result = [];
    this.comment = [];
    this.mandant = [];
    this.comment1 = [];
    this.t = [];
    this.c = [];
    this.t[0] = this.tcode;
    this.c[0] = this.selectedResultValue;
    this.result[0] = this.selectedResultValue;
    this.comment[0] = this.tcode;
    this.mandant[0] = this.route.snapshot.paramMap.get('id');
    console.log(this.selectedResultValue + "---" + this.tcode + this.route.snapshot.paramMap.get('id') + "--" + this.route.snapshot.paramMap.get('jobid') + "--" + this.route.snapshot.paramMap.get('tradeid'));
    this.ResultAndComment = [];
    if (this.tcode != null && this.selectedResultValue != null) {
      this.ResultAndComment[0] = this.c;
      this.ResultAndComment[1] = this.t;
      this.ResultAndComment[2] = this.mandant;
    }

    if (this.path.includes('TradeList') && !this.path.includes('Job')) {
      if(this.fileToUpload!=null){
        const uploadImageData = new FormData();
  uploadImageData.append('File', this.fileToUpload, this.fileToUpload.name);   
      this.http.post('http://localhost:8080/Mandants/FileUploadOverview', uploadImageData, { observe: 'response' }) .subscribe((response) => {
        if (response.status === 200) {this.message = 'Image uploaded successfully';
       } else {
         this.message = 'Image not uploaded successfully';
        }
         }  );
        }
      this.jobId[0] = this.route.snapshot.paramMap.get('Jobid');
      this.tradeId[0] = this.route.snapshot.paramMap.get('job1.t02_SRC_TRADE_ID');
      this.ResultAndComment[3] = this.jobId
      this.ResultAndComment[4] = this.tradeId

      this.comment1[0] = this.route.snapshot.paramMap.get('Jobid');
      this.comment1[1] = this.route.snapshot.paramMap.get('job1.t02_SRC_TRADE_ID');
      this.comment1[2] = this.selectedResultValue;
      this.comment1[3] = this.tcode;
      this.comment1[4] = this.route.snapshot.paramMap.get('id');
      this.comment1[5] = this.LoggedInUser;
      console.log(this.comment1+this.route.snapshot.paramMap.get('id')+this.LoggedInUser);
      this.appConfigService.updateCommentsOverview(this.comment1)
        .subscribe(data =>
          console.log("success", data),
          error => console.log("eror", error))
      console.log("submitted");

      this.ngOnInit();
    }

    if (this.path.includes('Searchtrades')) {
      if(this.fileToUpload!=null){
        const uploadImageData = new FormData();
  uploadImageData.append('File', this.fileToUpload, this.fileToUpload.name);   
      this.http.post('http://localhost:8080/Mandants/FileUploadOverview', uploadImageData, { observe: 'response' }) .subscribe((response) => {
        if (response.status === 200) {this.message = 'Image uploaded successfully';
       } else {
         this.message = 'Image not uploaded successfully';
        }
         }  );
        }
      this.jobId[0] = this.route.snapshot.paramMap.get('Jobid');
      this.tradeId[0] = this.route.snapshot.paramMap.get('job1.t02_SRC_TRADE_ID');
      this.ResultAndComment[3] = this.jobId
      this.ResultAndComment[4] = this.tradeId

      this.comment1[0] = this.route.snapshot.paramMap.get('Jobid');
      this.comment1[1] = this.route.snapshot.paramMap.get('job1.t02_SRC_TRADE_ID');
      this.comment1[2] = this.selectedResultValue;
      this.comment1[3] = this.tcode;
      this.comment1[4] = this.route.snapshot.paramMap.get('id');
      this.comment1[5] = this.LoggedInUser;
      console.log(this.comment1+this.route.snapshot.paramMap.get('id')+this.LoggedInUser);
      this.appConfigService.updateCommentsOverview(this.comment1)
        .subscribe(data =>
          console.log("success", data),
          error => console.log("eror", error))
      console.log("submitted");

      this.ngOnInit();

    }

    if (this.path.includes('Job')) {
      if(this.fileToUpload!=null){
      const uploadImageData = new FormData();
uploadImageData.append('File', this.fileToUpload, this.fileToUpload.name);   
    this.http.post('http://localhost:8080/Mandants/FileUploadOverview', uploadImageData, { observe: 'response' }) .subscribe((response) => {
      if (response.status === 200) {this.message = 'Image uploaded successfully';
     } else {
       this.message = 'Image not uploaded successfully';
      }
       }  );
      }
      this.jobId[0] = this.route.snapshot.paramMap.get('jobid');
      this.tradeId[0] = this.route.snapshot.paramMap.get('tradeid');
      this.ResultAndComment[3] = this.jobId
      this.ResultAndComment[4] = this.tradeId

      this.comment1[0] = this.route.snapshot.paramMap.get('jobid');
      this.comment1[1] = this.route.snapshot.paramMap.get('tradeid');
      this.comment1[2] = this.selectedResultValue;
      this.comment1[3] = this.tcode;
      this.comment1[4] = this.route.snapshot.paramMap.get('id');

      this.comment1[5] = this.LoggedInUser;
      console.log(this.comment1+this.route.snapshot.paramMap.get('id')+this.LoggedInUser);
      this.appConfigService.updateCommentsOverview(this.comment1)
        .subscribe(data =>
          console.log("success", data),
          error => console.log("eror", error))
      console.log("submitted");

      this.ngOnInit();
    }
    

    this.editFlag = !this.editFlag;
    this.ngOnInit();

  }
  
  fileEvent(files: FileList) {
    this.fileToUpload = files.item(0);
    console.log(this.fileToUpload);
    // this.fileToUpload = files.item(0);
    // console.log(files.item(0));
    // this.fileToUpload = files.item(0); 
    // let formData = new FormData(); 
    // formData.append('file', this.fileToUpload, this.fileToUpload.name); 
    // this.http.post(this.appConfigService.URL+'Mandants/FileUpload', formData).subscribe((val) => {

    //   console.log(val);
    //   });
    //   return false; 
}
}
