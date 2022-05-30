import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {MailData} from "../../model/MailData";

@Component({
  selector: 'app-mail-form',
  templateUrl: './mail-form.component.html',
  styleUrls: ['./mail-form.component.css']
})
export class MailFormComponent implements OnInit {

  formErrors: any[] = []
  mailForm: FormGroup;
  importance = ['Low', 'Normal', 'High']


  constructor(public fb: FormBuilder, private http: HttpClient) {
    this.mailForm = this.fb.group({
      fromEmail: ['my.mail@mail.com'],
      toEmail: [''],
      ccEmail: [''],
      subject: [''],
      importance: ['Normal'],
      mailContent: [''],
    })
  }

  ngOnInit(): void {
  }

  onSubmit() {
    let mailData: MailData = {
      fromEmail: this.mailForm?.get('fromEmail')?.value,
      toEmail: this.mailForm?.get('toEmail')?.value,
      ccEmail: this.mailForm?.get('ccEmail')?.value,
      subject: this.mailForm?.get('subject')?.value,
      importance: this.mailForm?.get('importance')?.value,
      mailContent: this.mailForm?.get('mailContent')?.value
    };

    console.log(JSON.stringify(mailData))

    const headers = new HttpHeaders().set('Content-Type', 'application/json');

    this.http
      .post('http://localhost:8080/api/mail/send', JSON.stringify(mailData), {headers: headers, responseType: 'json'})
      .subscribe(
        (response) => {
          this.formErrors = [];
          this.mailForm.reset()
          console.log(response)
        },
        (error) => {
          this.formErrors = [];
          for (const [key, value] of Object.entries(error.error)) {
            this.formErrors.push(`Please note: ${value}`);
          }
        }
      );
  }
  resetForm()
  {
    const confirmed = window.confirm('Are you sure, you will lose all your data?');

    if (confirmed) {
      this.mailForm.reset();
    } else {
      // user did not confirm
    }

  }
}
