import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'app-trade-info',
  templateUrl: './trade-info.component.html',
  styleUrls: ['./trade-info.component.css']
})

export class TradeInfoComponent implements OnInit {
  selectedValue: String;
  public value: boolean = false;
  id: any;
  id1: string;
  path: string;
  val: number;
  val1: number;
  constructor(public route: ActivatedRoute, private router: Router) {
    this.id1 = this.route.snapshot.params['id'];
    console.log(this.router.url + "hello");

  }

  ngOnInit() {
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
  }

  onChange(evt) {
    console.log(evt.target.innerHTML);
    //console.log(this.selectedValue);
    this.selectedValue = evt.target.innerHTML;
  }

}
