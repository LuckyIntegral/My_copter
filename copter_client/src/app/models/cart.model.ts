import {ProductOrderModel} from "./product-order.model";

export interface CartModel {
	created: Date;
	price: string;
	drones: ProductOrderModel[]
}
