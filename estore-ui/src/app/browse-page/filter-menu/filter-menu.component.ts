import {Component} from '@angular/core';

@Component({
	selector: 'app-filter-menu',
	templateUrl: './filter-menu.component.html',
	styleUrls: ['./filter-menu.component.scss']
})
export class FilterMenuComponent {
	showGenderOptions: boolean = false;
	showBrandOptions: boolean = false;
	showSizingOptions: boolean = false;
	showColorOptions: boolean = false;
	showPriceOptions: boolean = false;

	toggleGenderList() {
		this.showGenderOptions = !this.showGenderOptions;
	}
	toggleBrandList() {
		this.showBrandOptions = !this.showBrandOptions;
	}
	toggleSizingList() {
		this.showSizingOptions = !this.showSizingOptions;
	}
	toggleColorList() {
		this.showColorOptions = !this.showColorOptions;
	}
	togglePriceList() {
		this.showPriceOptions = !this.showPriceOptions;
	}
}
