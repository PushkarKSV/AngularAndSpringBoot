import { BrowserModule } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER, CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AgGridModule } from 'ag-grid-angular';
import { CookieService } from 'ngx-cookie-service';
import { IgxCsvExporterService, IgxExcelExporterService } from 'igniteui-angular';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { Applet1Component } from './applet1/applet1.component';
import { HomeComponent } from './home/home.component';
import { AppFooterComponent } from './app-footer/app-footer.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import {
  MatSidenavModule, MatButtonModule,
  MatIconModule,
  MatToolbarModule,
  MatMenuModule,
  MatListModule,
  MatDatepickerModule,
  MatNativeDateModule,
  MatInputModule,
  MatRadioModule,
  MAT_DATE_LOCALE,
  MAT_DATE_FORMATS
} from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { SidemenuComponent } from './sidemenu/sidemenu.component';
import { FileHeaderComponent } from './file-header/file-header.component';
import { FileSideComponent } from './file-side/file-side.component';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { InstrumentsComponent } from './instruments/instruments.component';
import { ConfigurationComponent } from './configuration/configuration.component';
import { OverviewComponent } from './overview/overview.component';
import { TradeInfoComponent } from './trade-info/trade-info.component';
import { TradeListComponent } from './trade-list/trade-list.component';
import { ParameterComponent } from './parameter/parameter.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { UserMaintenanceComponent } from './user-maintenance/user-maintenance.component';
import { HistoryComponent } from './history/history.component';
import { HelpComponent } from './help/help.component';
import { JobComponent } from './job/job.component';
import { BusinessMonthComponent } from './business-month/business-month.component';
import { BusinessDayComponent } from './business-day/business-day.component';
import { JobStatsComponent } from './job-stats/job-stats.component';
import { TradeCategoryStatsComponent } from './trade-category-stats/trade-category-stats.component';
import { LimitUnitStatsComponent } from './limit-unit-stats/limit-unit-stats.component';
import { TraderStatsComponent } from './trader-stats/trader-stats.component';
import { DatePickerModule } from '@syncfusion/ej2-angular-calendars';
import { HttpClientService } from './service/httpclient.service';
import { MatTabsModule } from '@angular/material/tabs';
import { LocalreportComponent } from './localreport/localreport.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { JobDetailsComponent } from './job-details/job-details.component';
import { MatTableModule } from '@angular/material';
import { BreadcrumbComponent } from './breadcrumb/breadcrumb.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { StornoGroupComponent } from './storno-group/storno-group.component';
import { SearchTradesComponent } from './search-trades/search-trades.component';
import { DatePipe } from '@angular/common';
import { AccessDetailsComponent } from './access-details/access-details.component';
import { MatCardModule } from '@angular/material/card';
import { ErrorPageComponent } from './error-page/error-page.component';
import { InterceptorService } from '../app/interceptor.service';

import { ButtonRendererComponent } from './instruments/Renderer/button-renderer/button-renderer.component'


@NgModule({
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA,
    NO_ERRORS_SCHEMA
  ],
  entryComponents: [HistoryComponent],
  declarations: [
    AppComponent,
    Applet1Component,
    HomeComponent,
    AppFooterComponent,
    SidemenuComponent,
    FileHeaderComponent,
    FileSideComponent,
   
    InstrumentsComponent,
    ConfigurationComponent,
    OverviewComponent,
    TradeInfoComponent,
    TradeListComponent,
    ParameterComponent,
    UserProfileComponent,
    UserMaintenanceComponent,
    HistoryComponent,
    HelpComponent,
    JobComponent,
    BusinessMonthComponent,
    BusinessDayComponent,
    JobStatsComponent,
    TradeCategoryStatsComponent,
    LimitUnitStatsComponent,
    TraderStatsComponent,
    LocalreportComponent,
    WelcomeComponent,
    JobDetailsComponent,
    BreadcrumbComponent,
    UserDetailsComponent,
    StornoGroupComponent,
    UserDetailsComponent,
    SearchTradesComponent,
    AccessDetailsComponent,
    ErrorPageComponent,
    ButtonRendererComponent,
   
   
  ],

  imports: [
    BrowserModule,
    DatePickerModule,
    AppRoutingModule,
     [AgGridModule.withComponents(JobComponent)],
     AgGridModule.withComponents([ButtonRendererComponent]),

    MatSidenavModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatIconModule,
    MatToolbarModule,
    HttpClientModule,
    MatListModule,
    MatMenuModule,
    NgbModule,
    FormsModule,
    MatCheckboxModule,
    MatTabsModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatNativeDateModule,
    MatInputModule,
    MatDatepickerModule,
    MatRadioModule,
    MatTableModule,
  
    ReactiveFormsModule,
    MatCardModule
  ],

  providers: [IgxCsvExporterService, IgxExcelExporterService, CookieService, DatePipe,
    {
    
      provide: APP_INITIALIZER,
      multi: true,
     
      deps: [HttpClientService],
     
      useFactory: (appConfigService: HttpClientService) => {
        return () => {
        };
      }
      
     
    },
    { provide: HTTP_INTERCEPTORS, useClass: InterceptorService, multi: true }],
  bootstrap: [AppComponent]
})

export class AppModule { }