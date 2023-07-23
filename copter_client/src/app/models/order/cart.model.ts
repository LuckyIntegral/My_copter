import {ProductOrderModel} from "./product-order.model";

export interface CartModel {
	id: number,
	created: Date;
	price: string;
	drones: ProductOrderModel[]
}
