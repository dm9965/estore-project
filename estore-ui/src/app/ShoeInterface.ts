import {Sizing} from './Sizing';

export class Shoe {
	id: number = 0;
	style: string = '';
	sizing: Sizing = Sizing.MENS || Sizing.WOMENS || Sizing.KIDS;
	size: number = 0;
	price: number = 0;
	brand: string = '';
	material: string = '';
	color: string = '';

}
