import {Component, OnInit} from '@angular/core';
import {MailData} from "../../model/MailData";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-mail-list',
  templateUrl: './mail-list.component.html',
  styleUrls: ['./mail-list.component.css']
})
export class MailListComponent implements OnInit {

  mailList: MailData[] = [];

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    this.http
      .get('http://localhost:8080/api/mail')
      .subscribe(
        (response) => {
          // @ts-ignore
          this.mailList = response;
        });
  }

}
