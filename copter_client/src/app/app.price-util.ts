export const AppPriceUtil = {
	formatNumber(number: number): string {
		return (number / 100).toLocaleString('en-US', { minimumFractionDigits: 2, maximumFractionDigits: 2 })
			.replaceAll(',', '');
	}
}