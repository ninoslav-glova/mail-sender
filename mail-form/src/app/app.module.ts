import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms'
import { AppRoutingModule } from './app-routing.module';

import {AppComponent} from './app.component';
import {MailFormComponent} from './mail-form/mail-form.component';
import {HttpClientModule} from '@angular/common/http';
import {RouterModule} from "@angular/router";
import { MailListComponent } from './mail-list/mail-list.component';

@NgModule({
  declarations: [
    AppComponent,
    MailFormComponent,
    MailListComponent
  ],
    imports: [
        HttpClientModule,
        BrowserModule,
        ReactiveFormsModule,
        FormsModule,
        RouterModule,
        AppRoutingModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
