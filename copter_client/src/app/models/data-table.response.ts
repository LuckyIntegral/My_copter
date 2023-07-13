
export interface DataTableResponse<E> {
	"page": number,
	"size": number,
	"totalPages": number,
	"totalElements": number,
	"first": boolean,
	"last": boolean,
	"next": boolean,
	"previous": boolean,
	"items": E[]
}