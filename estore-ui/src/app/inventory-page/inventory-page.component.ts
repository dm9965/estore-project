import {Component} from '@angular/core';
import {Shoe} from "../ShoeInterface";
import {ProductService} from "../product.service";
import {Sizing} from "../Sizing";

@Component({
	selector: 'app-inventory-page',
	templateUrl: './inventory-page.component.html',
	styleUrls: ['./inventory-page.component.scss']
})
export class InventoryPageComponent {
	shoes: Shoe[] = [];
	public newShoe: Shoe = new Shoe();
	sizing: Sizing = Sizing.WOMENS || Sizing.MENS || Sizing.KIDS;
	sizingOptions = Object.values(Sizing);
	selectedElement: any;
	updatedElement: any;
	isInputShown = true;
	isDropDownVisible = false;

	constructor(private productService: ProductService) {
	}

	toggleDropDown() {
		this.isDropDownVisible = true;
	}

	onSubmit() {
		this.createShoe(this.newShoe.id, this.newShoe.brand, this.newShoe.style,
			this.newShoe.sizing, this.newShoe.size, this.newShoe.price, this.newShoe.material, this.newShoe.color);
		this.isDropDownVisible = !this.isDropDownVisible;
		this.shoes.sort((shoeA, shoeB) => shoeA.id - shoeB.id);
	}

	ngOnInit(): void {
		this.getAllShoes();
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
					sizing: Sizing = Sizing.MENS || Sizing.KIDS || Sizing.WOMENS;
					style: string = '';
				};
			});
	}

	updateShoe(selectedShoe: Shoe, selectedAttribute: any, updatedValue: any) {
		switch (selectedAttribute) {
			case 'id':
				selectedShoe.id = updatedValue;
				break;
			case 'brand':
				selectedShoe.brand = updatedValue;
				break;
			case 'style':
				selectedShoe.style = updatedValue;
				break;
			case 'sizing':
				selectedShoe.sizing = updatedValue;
				break;
			case 'color':
				selectedShoe.color = updatedValue;
				break;
			case 'size':
				selectedShoe.size = updatedValue;
				break;
			case 'material':
				selectedShoe.material = updatedValue;
				break;
			case 'price':
				selectedShoe.price = updatedValue;
				break;
			default:
				break;
		}
		this.productService.updateShoe(selectedShoe)
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
		this.isInputShown = false;
	}

}
