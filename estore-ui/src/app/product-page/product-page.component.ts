import {Component} from '@angular/core';

@Component({
	selector: 'app-product-page',
	templateUrl: './product-page.component.html',
	styleUrls: ['./product-page.component.scss']
})
export class ProductPageComponent {
	/*@ViewChild('productInfoTemplate', {static: true})
	productInfoTemplate!: TemplateRef<any>;
	shoeData: Shoe[] = [];
	private shoeURL = 'http://localhost:8080/shoes';
	constructor(private http: HttpClient){}

	ngOnInit() {
		this.http.get<Shoe[]>(this.shoeURL).subscribe(data => {
			this.shoeData = data;
		});
	}*/
}
