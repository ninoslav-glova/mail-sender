import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MailFormComponent } from './mail-form/mail-form.component';
import { MailListComponent } from './mail-list/mail-list.component';

const routes: Routes = [
  { path: 'sendMail', component: MailFormComponent },
  { path: 'listAllMails', component: MailListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
