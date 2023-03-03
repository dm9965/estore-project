import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatIconModule} from "@angular/material/icon";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatButtonModule} from "@angular/material/button";
import {HomeComponent} from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import {MatInputModule} from "@angular/material/input";
import {FormsModule} from "@angular/forms";
import { SearchbarComponent } from './navbar/searchbar/searchbar.component';
import {MatButtonToggleModule} from "@angular/material/button-toggle";
import { BrandBarComponent } from './home/brand-bar/brand-bar.component';
import { FeaturedComponent } from './home/featured/featured.component';


@NgModule({
	declarations: [
		AppComponent,
		HomeComponent,
  		NavbarComponent,
    	SearchbarComponent,
     BrandBarComponent,
     FeaturedComponent
	],
	imports: [
		BrowserModule,
		AppRoutingModule,
		BrowserAnimationsModule,
		MatIconModule,
		MatToolbarModule,
		MatButtonModule,
		MatInputModule,
		FormsModule,
		MatButtonToggleModule
	],
	providers: [],
	bootstrap: [AppComponent]
})
export class AppModule {
}
