import {Component} from '@angular/core';
import {Shoe} from "../ShoeInterface";
import {ProductService} from "../services/product.service";
import {Sizing} from "../Sizing";

@Component({
	selector: 'app-inventory-page',
	templateUrl: './inventory-page.component.html',
	styleUrls: ['./inventory-page.component.scss']
})
export class InventoryPageComponent {
	shoes: Shoe[] = [];
	newShoe: Shoe = new Shoe();
	mens: Sizing = Sizing.MENS;
	womens: Sizing = Sizing.WOMENS;
	kids: Sizing = Sizing.KIDS;
	sizingOptions = Object.values(Sizing);
	selectedShoe: Shoe = new Shoe;
	selectedShoeIndex = -1;
	isAddDropDownVisible = false;
	isEditDropDownVisible = false;

	constructor(private productService: ProductService) {
	}

	trackByIndex(index: number, obj: any): any {
		return index;
	}

	addToggleDropDown() {
		this.isAddDropDownVisible = true;
	}

	editToggleDropDown() {
		this.isEditDropDownVisible = true;
	}

	onAddSubmit() {
		this.createShoe(this.newShoe.id, this.newShoe.brand, this.newShoe.style,
			this.newShoe.sizing, this.newShoe.size, this.newShoe.price, this.newShoe.material, this.newShoe.color);
		this.isAddDropDownVisible = !this.isAddDropDownVisible;
		this.shoes.sort((shoeA, shoeB) => shoeA.id - shoeB.id);
	}

	onEditSubmit() {
		this.isEditDropDownVisible = !this.isEditDropDownVisible;
		this.shoes.sort((shoeA, shoeB) => shoeA.id - shoeB.id);
	}

	ngOnInit(): void {
		this.getAllShoes();
		this.selectedShoe.id = this.selectedShoe.id || 0;
	}

	getAllShoes(): void {
		console.log(this.shoes)
		this.productService.getAllShoes().subscribe(shoes => this.shoes = shoes);
	}

	createShoe(id: number, brand: string, style: string, sizing: string, size: number, price: number, material: string, color: string): void {
		brand = brand.trim();
		style = style.trim();
		material = material.trim();
		color = color.trim();
		if (!brand || !style || !material || !color) {
			return;
		}
		this.productService.addShoe({id, brand, style, sizing, size, price, material, color} as Shoe)
			.subscribe(shoe => {
				this.shoes.push(this.newShoe);
				this.newShoe = new class implements Shoe {
					brand: string = '';
					color: string = '';
					id: number = 0;
					material: string = '';
					price: number = 0;
					size: number = 0;
					sizing: Sizing = Sizing.WOMENS || Sizing.MENS || Sizing.KIDS;
					style: string = '';
				};
			});
	}

	updateShoe() {
		const selectedShoeId = this.selectedShoe.id;
		const selectedShoe = this.shoes.find(shoe => shoe.id === selectedShoeId);
		if (selectedShoe) {
			this.selectedShoe.brand = selectedShoe.brand;
			this.selectedShoe.style = selectedShoe.style;
			this.selectedShoe.sizing = selectedShoe.sizing;
			this.selectedShoe.color = selectedShoe.color;
			this.selectedShoe.size = selectedShoe.size;
			this.selectedShoe.material = selectedShoe.material;
			this.selectedShoe.price = selectedShoe.price;
		}
		this.productService.updateShoe(this.selectedShoe)
			.subscribe(() => {
				console.log('Shoe updated');
			});
	}

	deleteShoe(shoe: Shoe): void {
		this.shoes = this.shoes.filter(s => s !== shoe);
		this.productService.deleteShoe(shoe.id).subscribe();
	}

	save(): void {
		this.productService.save(this.shoes)
			.subscribe(result => {
				if (result) {
					console.log('Shoes saved successfully');
				} else {
					console.log('Error saving shoes');
				}
			});
		this.selectedShoeIndex = -1;
	}

	selectIndex(index: number) {
		this.selectedShoeIndex = index;
	}
}
