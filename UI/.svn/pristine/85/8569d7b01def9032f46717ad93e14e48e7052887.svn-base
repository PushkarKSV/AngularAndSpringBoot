import { Component, OnInit, Input, ViewChild, Injectable } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Location } from '@angular/common';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { HttpClientService } from '../service/httpclient.service';

@Component({
  selector: 'app-file-side',
  templateUrl: './file-side.component.html',
  styleUrls: ['./file-side.component.css']
})
@Injectable({ providedIn: 'root' })

export class FileSideComponent implements OnInit {
  mandantList: String[];
  id$: Observable<string>;
  id: string;
  parentData: string;
  Bond: String = "";
  selectedValue = '';
  public dropdownValue = '';
  urlFlag: boolean = false;
  texttrade: boolean = false;
  textstatistic: boolean = false;
  textadministration: boolean = false;

  ShowTrade() {
    this.texttrade = !this.texttrade;
    this.textstatistic = false;
    this.textadministration = false;

  }

  ShowStatistic() {
    this.textstatistic = !this.textstatistic;
    this.texttrade = false;
    this.textadministration = false;
  }

  ShowAdministration() {

    this.textadministration = !this.textadministration;
    this.texttrade = false;
    this.textstatistic = false;
  }

  //BONDS CBB
  BondsCBBTrade = [
    { id: 'BloombergCheck', name: 'Automatic BLOOMBERG Check' },
    { id: 'ManualConsistancy', name: 'Manual Consistancy Check' },
    { id: 'ManualCheck', name: 'Manual Check' },
    { id: 'Job', name: 'Jobs' },
    { id: 'Searchtrades', name: 'Search trades' }

  ];

  BondsCBBStatistic = [
    { id: 'BusinessMonth', name: 'Business Month Statistic' },
    { id: 'BusinessDay', name: 'Business Day Statistic' },
    { id: 'JobStatistics', name: 'Job Statistic' },
    { id: 'TradeCategory', name: 'Trade Category Statistic' },
    { id: 'LimitUnit', name: 'Limit Unit Statistic' },
    { id: 'Trader', name: 'Trader Statistic' }
  ];
  BondsCBBAdministration = [
    { id: 'Employees', name: 'Employees' },
    { id: 'StateCodes', name: 'State Codes' },
    { id: 'Instrument', name: 'Instruments' },
    { id: 'Configuration', name: 'Configuration' }
  ]

  //BOND EAA
  BondEAATrade = [
    { id: 'BloombergCheck', name: 'Automatic BLOOMBERG Check' },
    { id: 'ManualConsistancy', name: 'Manual Consistancy Check' },
    { id: 'ManualCheck', name: 'Manual Check' },
    { id: 'Job', name: 'Jobs' },
    { id: 'Searchtrades', name: 'Search trades' }

  ];

  BondEAAStatistic = [
    { id: 'BusinessMonth', name: 'Business Month Statistic' },
    { id: 'BusinessDay', name: 'Business Day Statistic' },
    { id: 'JobStatistics', name: 'Job Statistic' },
    { id: 'TradeCategory', name: 'Trade Category Statistic' },
    { id: 'LimitUnit', name: 'Limit Unit Statistic' },
    { id: 'Trader', name: 'Trader Statistic' }
  ];
  BondEAAAdministration = [
    { id: 'Employees', name: 'Employees' },
    { id: 'StateCodes', name: 'State Codes' },
    { id: 'Instrument', name: 'Instruments' },
    { id: 'Configuration', name: 'Configuration' }
  ]

  //BOND PAG
  BondPAGTrade = [
    { id: 'BloombergCheck', name: 'Automatic BLOOMBERG Check' },
    { id: 'ManualConsistancy', name: 'Manual Consistancy Check' },
    { id: 'ManualCheck', name: 'Manual Check' },
    { id: 'Job', name: 'Jobs' },
    { id: 'Searchtrades', name: 'Search trades' }
  ];

  BondPAGStatistic = [
    { id: 'BusinessMonth', name: 'Business Month Statistic' },
    { id: 'BusinessDay', name: 'Business Day Statistic' },
    { id: 'JobStatistics', name: 'Job Statistic' },
    { id: 'TradeCategory', name: 'Trade Category Statistic' },
    { id: 'LimitUnit', name: 'Limit Unit Statistic' },
    { id: 'Trader', name: 'Trader Statistic' }
  ];
  BondPAGAdministration = [
    { id: 'BNG/Employees', name: 'Employees' },
    { id: 'BNG/StateCodes', name: 'State Codes' },
    { id: 'Instrument', name: 'Instruments' },
    { id: 'Configuration', name: 'Configuration' }
  ]

  //DerivativeEAA
  DerivativeEAATrade = [

    { id: 'ManualCheck', name: 'Manual Check' },
    { id: 'Job', name: 'Jobs' },
    { id: 'Searchtrades', name: 'Search trades' }
  ];

  DerivativeEAAStatistic = [
    { id: 'BusinessMonth', name: 'Business Month Statistic' },
    { id: 'BusinessDay', name: 'Business Day Statistic' },
    { id: 'JobStatistics', name: 'Job Statistic' },
    { id: 'TradeCategory', name: 'Trade Category Statistic' },
    { id: 'Instrument Statistic', name: 'Instrument Statistic' },
    { id: 'Location', name: 'Location Statistic' },
    { id: 'LimitUnit', name: 'Limit Unit Statistic' },
    { id: 'Trader', name: 'Trader Statistic' }
  ];
  DerivativeEAAAdministration = [
    { id: 'Employees', name: 'Employees' },
    { id: 'StateCodes', name: 'State Codes' },
    { id: 'Instrument', name: 'Instruments' },
    { id: 'Configuration', name: 'Configuration' }
  ]

  //DerivativeCBB
  DerivativeCBBTrade = [
    { id: 'ManualCheck', name: 'Manual Check' },
    { id: 'Job', name: 'Jobs' },
    { id: 'Searchtrades', name: 'Search trades' }
  ];

  DerivativeCBBStatistic = [
    { id: 'BusinessMonth', name: 'Business Month Statistic' },
    { id: 'BusinessDay', name: 'Business Day Statistic' },
    { id: 'JobStatistics', name: 'Job Statistic' },
    { id: 'TradeCategory', name: 'Trade Category Statistic' },
    { id: 'Instrument Statistic', name: 'Instrument Statistic' },
    { id: 'Location', name: 'Location Statistic' },
    { id: 'LimitUnit', name: 'Limit Unit Statistic' },
    { id: 'Trader', name: 'Trader Statistic' }
  ];
  DerivativeCBBAdministration = [
    { id: 'Employees', name: 'Employees' },
    { id: 'StateCodes', name: 'State Codes' },
    { id: 'Instrument', name: 'Instruments' },
    { id: 'Configuration', name: 'Configuration' }
  ]

  //DerivativePAG
  DerivativePAGTrade = [
    { id: 'ManualCheck', name: 'Manual Check' },
    { id: 'Job', name: 'Jobs' },
    { id: 'Searchtrades', name: 'Search trades' }
  ];

  DerivativePAGStatistic = [
    { id: 'BusinessMonth', name: 'Business Month Statistic' },
    { id: 'BusinessDay', name: 'Business Day Statistic' },
    { id: 'JobStatistics', name: 'Job Statistic' },
    { id: 'TradeCategory', name: 'Trade Category Statistic' },
    { id: 'Instrument Statistic', name: 'Instrument Statistic' },
    { id: 'Location', name: 'Location Statistic' },
    { id: 'LimitUnit', name: 'Limit Unit Statistic' },
    { id: 'Trader', name: 'Trader Statistic' }
  ];
  DerivativePAGAdministration = [
    { id: 'Employees', name: 'Employees' },
    { id: 'StateCodes', name: 'State Codes' },
    { id: 'Instrument', name: 'Instruments' },
    { id: 'Configuration', name: 'Configuration' }
  ]


  //MoneyMarketEAA
  MoneyMarketEAATrade = [
    { id: 'ManualCheck', name: 'Manual Check' },
    { id: 'TraderReclamationReport', name: 'Trader Reclamation Reports Statistic' },
    { id: 'Job', name: 'Jobs' },
    { id: 'Searchtrades', name: 'Search trades' }
  ];

  MoneyMarketEAAStatistic = [
    { id: 'BusinessMonth', name: 'Business Month Statistic' },
    { id: 'BusinessDay', name: 'Business Day Statistic' },
    { id: 'JobStatistics', name: 'Job Statistic' },
    { id: 'TradeCategory', name: 'Trade Category Statistic' },
    { id: 'Location', name: 'Location Statistic' },
    { id: 'BusinessUnit', name: 'Business Unit Statistic' },
    { id: 'LimitUnit', name: 'Limit Unit Statistic' },
    { id: 'Book statistic', name: 'Book Statistic' },
    { id: 'Trader', name: 'Trader Statistic' }
  ];
  MoneyMarketEAAAdministration = [
    { id: 'Employees', name: 'Employees' },
    { id: 'StateCodes', name: 'State Codes' },
    { id: 'Price Check', name: 'Price Check Categories' },
    { id: 'Configuration', name: 'Configuration' }
  ]

  //MoneyMarketCBB
  MoneyMarketCBBTrade = [
    { id: 'ManualCheck', name: 'Manual Check' },
    { id: 'TraderReclamationReport', name: 'Trader Reclamation Reports Statistic' },
    { id: 'Job', name: 'Jobs' },
    { id: 'Searchtrades', name: 'Search trades' }
  ];

  MoneyMarketCBBStatistic = [
    { id: 'BusinessMonth', name: 'Business Month Statistic' },
    { id: 'BusinessDay', name: 'Business Day Statistic' },
    { id: 'JobStatistics', name: 'Job Statistic' },
    { id: 'TradeCategory', name: 'Trade Category Statistic' },
    { id: 'Location', name: 'Location Statistic' },
    { id: 'BusinessUnit', name: 'Business Unit Statistic' },
    { id: 'LimitUnit', name: 'Limit Unit Statistic' },
    { id: 'Book statistic', name: 'Book Statistic' },
    { id: 'Trader', name: 'Trader Statistic' }
  ];
  MoneyMarketCBBAdministration = [
    { id: 'Employees', name: 'Employees' },
    { id: 'StateCodes', name: 'State Codes' },
    { id: 'Price Check', name: 'Price Check Categories' },
    { id: 'Configuration', name: 'Configuration' }
  ]

  //MoneyMarketPAG
  MoneyMarketPAGTrade = [
    { id: 'ManualCheck', name: 'Manual Check' },
    { id: 'TraderReclamationReport', name: 'Trader Reclamation Reports Statistic' },
    { id: 'Job', name: 'Jobs' },
    { id: 'Searchtrades', name: 'Search trades' }
  ];

  MoneyMarketPAGStatistic = [
    { id: 'BusinessMonth', name: 'Business Month Statistic' },
    { id: 'BusinessDay', name: 'Business Day Statistic' },
    { id: 'JobStatistics', name: 'Job Statistic' },
    { id: 'TradeCategory', name: 'Trade Category Statistic' },
    { id: 'Location', name: 'Location Statistic' },
    { id: 'BusinessUnit', name: 'Business Unit Statistic' },
    { id: 'LimitUnit', name: 'Limit Unit Statistic' },
    { id: 'Book statistic', name: 'Book Statistic' },
    { id: 'Trader', name: 'Trader Statistic' }
  ];
  MoneyMarketPAGAdministration = [
    { id: 'BNG/Employees', name: 'Employees' },
    { id: 'BNG/StateCodes', name: 'State Codes' },
    { id: 'Price Check', name: 'Price Check Categories' },
    { id: 'Configuration', name: 'Configuration' }
  ]


  //RepoEAA
  RepoEAATrade = [
    { id: 'BloombergCheck', name: 'Automatic BLOOMBERG Check' },
    { id: 'ManualConsistancy', name: 'Manual Consistancy Check' },
    { id: 'ManualCheck', name: 'Manual Check' },
    { id: 'TraderReclamationReport', name: 'Trader Reclamation Reports Statistic' },
    { id: 'Job', name: 'Jobs' },
    { id: 'Searchtrades', name: 'Search trades' }
  ];

  RepoEAAStatistic = [
    { id: 'BusinessMonth', name: 'Business Month Statistic' },
    { id: 'BusinessDay', name: 'Business Day Statistic' },
    { id: 'JobStatistics', name: 'Job Statistic' },
    { id: 'TradeCategory', name: 'Trade Category Statistic' },
    { id: 'BusinessUnit', name: 'Business Unit Statistic' },
    { id: 'LimitUnit', name: 'Limit Unit Statistic' },
    { id: 'Book statistic', name: 'Book Statistic' },
    { id: 'Trader', name: 'Trader Statistic' }
  ];
  RepoEAAAdministration = [
    { id: 'BNG/Employees', name: 'Employees' },
    { id: 'BNG/StateCodes', name: 'State Codes' },
    { id: 'Instrument', name: 'Instruments' },
    { id: 'Price Check', name: 'Price Check Categories' },
    { id: 'Configuration', name: 'Configuration' }
  ]

  //RepoCBB
  RepoCBBTrade = [
    { id: 'BloombergCheck', name: 'Automatic BLOOMBERG Check' },
    { id: 'ManualConsistancy', name: 'Manual Consistancy Check' },
    { id: 'ManualCheck', name: 'Manual Check' },
    { id: 'TraderReclamationReport', name: 'Trader Reclamation Reports Statistic' },
    { id: 'Job', name: 'Jobs' },
    { id: 'Searchtrades', name: 'Search trades' }
  ];

  RepoCBBStatistic = [
    { id: 'BusinessMonth', name: 'Business Month Statistic' },
    { id: 'BusinessDay', name: 'Business Day Statistic' },
    { id: 'JobStatistics', name: 'Job Statistic' },
    { id: 'TradeCategory', name: 'Trade Category Statistic' },
    { id: 'BusinessUnit', name: 'Business Unit Statistic' },
    { id: 'LimitUnit', name: 'Limit Unit Statistic' },
    { id: 'Book statistic', name: 'Book Statistic' },
    { id: 'Trader', name: 'Trader Statistic' }
  ];
  RepoCBBAdministration = [
    { id: 'BNG/Employees', name: 'Employees' },
    { id: 'BNG/StateCodes', name: 'State Codes' },
    { id: 'Instrument', name: 'Instruments' },
    { id: 'Price Check', name: 'Price Check Categories' },
    { id: 'Configuration', name: 'Configuration' }
  ]

  //RepoPAG
  RepoPAGTrade = [
    { id: 'BloombergCheck', name: 'Automatic BLOOMBERG Check' },
    { id: 'ManualConsistancy', name: 'Manual Consistancy Check' },
    { id: 'ManualCheck', name: 'Manual Check' },
    { id: 'TraderReclamationReport', name: 'Trader Reclamation Reports Statistic' },
    { id: 'Job', name: 'Jobs' },
    { id: 'Searchtrades', name: 'Search trades' }
  ];

  RepoPAGStatistic = [
    { id: 'BusinessMonth', name: 'Business Month Statistic' },
    { id: 'BusinessDay', name: 'Business Day Statistic' },
    { id: 'JobStatistics', name: 'Job Statistic' },
    { id: 'TradeCategory', name: 'Trade Category Statistic' },
    { id: 'BusinessUnit', name: 'Business Unit Statistic' },
    { id: 'LimitUnit', name: 'Limit Unit Statistic' },
    { id: 'Book statistic', name: 'Book Statistic' },
    { id: 'Trader', name: 'Trader Statistic' }
  ];
  RepoPAGAdministration = [
    { id: 'BNG/Employees', name: 'Employees' },
    { id: 'BNG/StateCodes', name: 'State Codes' },
    { id: 'Instrument', name: 'Instruments' },
    { id: 'Price Check', name: 'Price Check Categories' },
    { id: 'Configuration', name: 'Configuration' }
  ]

  constructor(private httpClientService: HttpClientService, public route: ActivatedRoute, private location: Location, private cookieService: CookieService, private router: Router) { }
  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');
    console.log("id", this.id);
    console.log("routePrasms", this.route.params);
    this.httpClientService.getMandantList().subscribe(
      response => {
        for (var key in response) {
          if (response[key][1] == this.id) {
            this.selectedValue = this.id;
          }
        }
        this.handleSuccessfulResponse(response)
      }
    );
  }

  handleSuccessfulResponse(response) {
    this.mandantList = response;
  }

  onChange(evt) {
    console.log(evt.target.value + '-------');
    var url = evt.target.value;
    this.dropdownValue = evt.target.value;
    this.Bond = evt.target.value;
    localStorage.setItem('StorageValue', this.dropdownValue);
    localStorage.setItem('eventType', evt.type);
    var path = this.router.url;
    console.log("dropdownvalue", this.dropdownValue + "url" + url);
    if (path.includes("TradeList") && !path.includes("TradeDetails") && !path.includes("Job")) {
      this.router.navigateByUrl(url, { skipLocationChange: true })
        .then(() => {
          this.router.navigate([url + '/TradeList']);
        });
    }
    else {
      this.router.navigateByUrl(url, { skipLocationChange: true })
        .then(() => {
          this.router.navigate([url + '/Job']);
        });
    }
  }

  value() {
    return this.selectedValue;
  }

  getData(): any {
    console.log("dropdownvalue", this.dropdownValue);
    if (this.dropdownValue) {
      return this.dropdownValue;
    }
  }

}
