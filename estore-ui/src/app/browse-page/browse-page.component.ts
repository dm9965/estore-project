import {Component, OnInit} from '@angular/core';
import {ProductService} from "../services/product.service";
import {SearchbarComponent} from "../navbar/searchbar/searchbar.component";
import {Observable, of} from "rxjs";
import {Shoe} from "../ShoeInterface";
import {FormControl} from "@angular/forms";

@Component({
	selector: 'app-browse-page',
	templateUrl: './browse-page.component.html',
	styleUrls: ['./browse-page.component.scss']
})
export class BrowsePageComponent implements OnInit {
	constructor(private productService: ProductService) {}
	private searchTerm: FormControl = new FormControl();
	shoe: Shoe = new Shoe();
	shoes$: Observable<Shoe[]> | undefined;
	ngOnInit(): void {
		this.shoes$ = this.productService.getAllShoes();
		this.searchTerm.valueChanges.subscribe(term => {
			this.shoes$ = this.productService.searchShoes(term);
		});
	}



}
