import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidemenu',
  templateUrl: './sidemenu.component.html',
  styleUrls: ['./sidemenu.component.css']
})
export class SidemenuComponent implements OnInit {

  Report: boolean = false;
  constructor() { }
  ngOnInit() {
  }
  ShowReport() {
    this.Report = !this.Report;
  }
  ReportData = [
    { id: '/LocalReport', name: 'Local Report' }
  ];

}
