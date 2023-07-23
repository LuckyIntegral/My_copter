import {DroneOrderModel} from "./drone-order.model";

export interface PurchaseModel {
	username: string,
	contact: string,
	address: string,
	active: boolean,
	price: number,
	cartId: number,
	id: number,
	drones: DroneOrderModel[]
}