import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs';
import { User } from '../localreport/model/user';

export class Mandant {
  constructor(
    public fk_t01_t02_mandant: String,
    public T09_ID: String,

  ) { }
}

@Injectable({
  providedIn: 'root'
})

export class HttpClientService {
 
  
 
  private appConfig: any;
  public jobID: any;
  public accessdetails: any;
  UserLoggedIn: any;
  public URL: any ='http://localhost:8080/';
   //public URL: any='http://zpsx2220:6001/mgb-backend/';
  constructor(private httpClient: HttpClient) {
  }
  getMandants(){
    console.log("Mandants");
    return this.httpClient.get<Mandant[]>(this.URL + '/Users/IBM0368/Mandants')
  }
  getMandantList() {
    console.log("Mandants");
    return this.httpClient.get<Mandant[]>(this.URL + '/Users/'+this.UserLoggedIn+'/Mandants')
  }

  // getUserDetails(User: String) {
  //   console.log(this.UserLoggedIn+"****************************" + User);
  //   return this.httpClient.get<Mandant[]>(this.URL + User + 'Users/IBM0330/UserDetails')
  // }

  
  getHomeEAA() {
    console.log("EAA Home page jobs and trades"+this.UserLoggedIn);
    return this.httpClient.get<Mandant[]>(this.URL + 'Users/'+this.UserLoggedIn + '/GetEAAMandants')
  }

  getHomeCBB() {
    console.log("CBB Home page jobs and trades");
    return this.httpClient.get<Mandant[]>(this.URL + 'Users/'+this.UserLoggedIn + '/GetCBBMandants')
  }
  getHomePAG() {
    console.log("PAG Home page jobs and trades");
    return this.httpClient.get<Mandant[]>(this.URL + 'Users/'+this.UserLoggedIn + '/GetPAGMandants')
  }

    // private messageSource = new BehaviorSubject('default message');
    // currentMessage = this.messageSource.asObservable();
    // changeMessage(message: string) {
    //   this.messageSource.next(message)
    // }
    public value:any=this.URL+'localreport'
    public priceCheckURL=this.URL+'Instrument/'
    
localReport(user:User)
{
  console.log(user)
return this.httpClient.post<any>(this.value,user)
}
getReportLocations( )
{
  return this.httpClient.get<string[]>( this.URL+'ReportLocations') 
}
save(mandate:any,priceCheck: any) {
  
  console.log(mandate)
 console.log(priceCheck)

  return this.httpClient.post<any>(this.priceCheckURL+mandate+'/'+this.UserLoggedIn,priceCheck)
}
pricecheckCategory(id: string) {
  console.log(this.URL + id+'/'+'pricecheckCategory')
  return this.httpClient.get<Mandant[]>(this.URL + id+'/'+'pricecheckCategory')
}
    

  

  // getUserDetails(User:String) 
  // {
  //   console.log("****************************"+User);  
  //   return this.httpClient.get<Mandant[]>(this.URL+User+'/UserDetails')  
  // }
  

  private messageSource = new BehaviorSubject('default message');
  currentMessage = this.messageSource.asObservable();
  changeMessage(message: string) {
    this.messageSource.next(message)
  }

  httpOptions = {
    headers: new HttpHeaders({
      'Authorization': 'NTLM',
    })
  };

  getUserID() {
    console.log("Getting logged in UserID");
    console.log(this.httpOptions);
    console.log(this.UserLoggedIn=this.httpClient.get(this.URL + 'UserId/UserProfile'));
    return this.UserLoggedIn;
  }

  getTradeOverview(Mandant1: any, JobID: any, TradeID: any) {
    console.log("Overview");
    return this.httpClient.get<Mandant[]>(this.URL + "Mandants/" + Mandant1 + '/Job/' + JobID + '/Trade/' + TradeID + "/Overview")
  }

  getTradeOverviews(Mandant1: any, JobID: any, TradeID: any) {
    console.log("Overview");
    return this.httpClient.get<Mandant[]>(this.URL + "Mandants/" + Mandant1 + '/Job/' + JobID + '/Trade/' + TradeID + "/Overview")
  }

  getTradeDetails(url: any) {
    console.log("Job Details" + url);
    return this.httpClient.get<Mandant[]>(this.URL + "Mandants/" + url + '/OpenTrades')
  }
  
  updateComments(update:any[])
  {
    console.log(update);
    return this.httpClient.post<any>(this.URL + "Mandants/updateComment", update);
  }

  updateCommentsOverview(updateOverview:any[])
  {
    console.log(updateOverview);
    return this.httpClient.post<any>(this.URL + "Mandants/updateComment/Overview", updateOverview);
  }

  postFile(fileToUpload: File) {
    console.log(fileToUpload+"---");
    const formData: FormData = new FormData();
    // return this.httpClient
    // .post(this.URL, formData);
}
}