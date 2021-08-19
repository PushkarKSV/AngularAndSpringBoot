import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule, ChildrenOutletContexts } from '@angular/router';
import { Applet1Component } from './applet1/applet1.component';
import { HomeComponent } from './home/home.component';

import { InstrumentsComponent } from './instruments/instruments.component';
import { ConfigurationComponent } from './configuration/configuration.component';
import { TradeInfoComponent } from './trade-info/trade-info.component';
import { TradeListComponent } from './trade-list/trade-list.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { UserMaintenanceComponent } from './user-maintenance/user-maintenance.component';
import { JobComponent } from './job/job.component';
import { BusinessMonthComponent } from './business-month/business-month.component';
import { BusinessDayComponent } from './business-day/business-day.component';
import { JobStatsComponent } from './job-stats/job-stats.component';
import { TradeCategoryStatsComponent } from './trade-category-stats/trade-category-stats.component';
import { LimitUnitStatsComponent } from './limit-unit-stats/limit-unit-stats.component';
import { TraderStatsComponent } from './trader-stats/trader-stats.component';
import { LocalreportComponent } from './localreport/localreport.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { AccessDetailsComponent } from './access-details/access-details.component';
import { SidemenuComponent } from './sidemenu/sidemenu.component';
import { OverviewComponent } from './overview/overview.component';

const routes: Routes = [

  { path: '', component: HomeComponent },
  {path:'LocalReport',component:LocalreportComponent},
  { path: 'UserMaintenance', component: UserMaintenanceComponent, data: { breadcrumb: 'UserMaintenance' } },
  { path: 'UserMaintenance/:Userid', component: UserProfileComponent, data: { breadcrumb: '' } },
  { path: 'UserMaintenance/:Userid/NewUser', component: AccessDetailsComponent, data: { breadcrumb: '' } },
  { path: 'UserMaintenance/:Userid/EditAccess', component: AccessDetailsComponent, data: { breadcrumb: '' } },
 
  
  { path: 'Profile', component: UserDetailsComponent },
  {
    path: ':id', component: Applet1Component,
    children: [
      { path: ':jobid/TradeList', component: TradeListComponent, data: { breadcrumb: 'TradeList' } },
      {
        path: '',
        children: [
          
          { path: 'Job', component: JobComponent, data: { breadcrumb: 'Job' } },
          { path: 'TradeList', component: TradeListComponent, data: { breadcrumb: 'TradeList' } },
          { path: 'Searchtrades', component: TradeListComponent, data: { breadcrumb: 'Searchtrades' } },
          { path: 'TradeList/:job1.t02_SRC_TRADE_ID/TradeDetails', component: OverviewComponent, data: { breadcrumb: 'TradeDetails' } },
          { path: 'Instrument', component: InstrumentsComponent, data: { breadcrumb: 'Instruments' } },
          { path: 'Configuration', component: ConfigurationComponent, data: { breadcrumb: 'Configuration' } },
          { path: 'Searchtrades/:Jobid/:job1.t02_SRC_TRADE_ID/TradeDetails', component: OverviewComponent },
          { path: 'Job/:jobid/TradeList', component: TradeListComponent, data: { breadcrumb: 'TradeList' } },
          { path: 'TradeList/:Jobid/:job1.t02_SRC_TRADE_ID/TradeDetails', component: OverviewComponent, data: { breadcrumb: 'Trade Details' } },
          { path: 'Job/:jobid/TradeList/:tradeid/TradeDetails', component: OverviewComponent, data: { breadcrumb: 'Trade Details' } },
          { path: 'BusinessMonth', component: BusinessMonthComponent },
          { path: 'BusinessDay', component: BusinessDayComponent },
          { path: 'JobStatistics', component: JobStatsComponent },
          { path: 'TradeCategory', component: TradeCategoryStatsComponent },
          { path: 'LimitUnit', component: LimitUnitStatsComponent },
          { path: 'Trader', component: TraderStatsComponent },
        ]
      },
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    onSameUrlNavigation: 'reload'
  })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
