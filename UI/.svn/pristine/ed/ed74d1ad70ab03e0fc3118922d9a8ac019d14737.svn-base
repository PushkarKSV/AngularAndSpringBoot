import { Component, OnInit, Input } from '@angular/core';
import { HttpClientService } from '../service/httpclient.service';
import { HttpClient } from "@angular/common/http";
import { ActivatedRoute } from '@angular/router';
import { Location, NgIf } from'@angular/common';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import {FormGroup,FormBuilder,Validators} from '@angular/forms';
import { UserProfileComponent } from '../user-profile/user-profile.component';
import { FormsModule } from '@angular/forms';
import {MatCardModule} from '@angular/material/card';

@Component({
  selector: 'app-access-details',
  templateUrl: './access-details.component.html',
  styleUrls: ['./access-details.component.css']
})



export class AccessDetailsComponent implements OnInit{

  Userid:any;
  User:any;
  Userid1:any;
  public accessid:any;
  public accessvalues:any;
  path:any;
  public eventdata:any;
  userValidations: FormGroup;
  public submitted = false;
  id: string;
  id1:string;
  message: any;
  value: boolean;
  
  constructor(private httpClientService:HttpClientService,public http: HttpClient, public route: ActivatedRoute,private appConfigService:HttpClientService,  
    private Userlocation: Location,public cookieService:CookieService,private router: Router,private formBuilder: FormBuilder) {

    router.events.subscribe((val) => {
      if(Userlocation.path() != ''){
        this.Userid1 = Userlocation.path();
        console.log("Value of Access details URL"+this.Userid1);
       
        // this.id1=this.httpClientService.sendMandant()
        }
    });

    this.path=this.router.url;
    
   }

    ngOnInit() {
      
      this.route.paramMap.subscribe(params => { 
        this.User = params.get('Userid'); 
      });
      // this.User = this.route.snapshot.paramMap.get('Userid');
        console.log(this.User);
      this.appConfigService. currentMessage.subscribe(message => this.message = message)
      
      this.userValidations = this.formBuilder.group({
      firstname :['', [Validators.required]],
      lastname :['', [Validators.required]],
      email : ['', [Validators.required, Validators.email]],
      nt_id :['', [Validators.required]]

      });

      if(this.path.includes('NewUser'))
    {
      this.appConfigService.accessdetails = "";
      this.value=true;
    }

    this.http
      .get(this.appConfigService.URL + "Mandants/" + this.route.snapshot.paramMap.get('Userid') + "/UserMaintenance")
    .subscribe(Backend_data => {
      
       this.accessvalues = Backend_data;
       console.log("Row values",this.accessvalues);
       console.log("Mandant",this.accessid = this.accessvalues[0][2]);
        
     });

     console.log("accessdetails",this.appConfigService.accessdetails);
     this.eventdata=this.appConfigService.accessdetails;
  }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.userValidations.invalid) {
        return;
    }

    console.log(this.userValidations.value)
}
  
}
