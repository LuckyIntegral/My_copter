import {inject, Inject, Injectable, InjectionToken, OnDestroy} from "@angular/core";
import {DOCUMENT} from '@angular/common';
import {BehaviorSubject, map, Observable} from "rxjs";

export const WINDOW = new InjectionToken<Window>(
	'An abstraction over global window object',
	{
		factory: () => {
			const {defaultView} = inject(DOCUMENT);
			if (!defaultView)
				throw new Error('Window is not available');
			return defaultView;
		},
	},
);

export const LOCAL_STORAGE = new InjectionToken<Storage>('Local storage', {
	factory: () => inject(WINDOW).localStorage
});

@Injectable({
	providedIn: "root"
})
export class SessionService extends Observable<Map<string, string>> implements OnDestroy {

	private readonly _entireStorage$: BehaviorSubject<Map<string, string>> = new BehaviorSubject(this._allStorage());

	constructor(@Inject(LOCAL_STORAGE) private _localStorage: Storage) {
		super(subscriber => {
			this._entireStorage$.subscribe(subscriber);
		})
	}

	ngOnDestroy() {
		this._entireStorage$.complete();
	}

	addToStorage(itName: string, itValue: string): void {
		itName && itValue && this._localStorage.setItem(itName, itValue);
		this._entireStorage$.next(this._allStorage());
	}

	removeFromStorage(itName: string) {
		itName && this._localStorage.removeItem(itName);
		this._entireStorage$.next(this._allStorage());
	}

	fromStorage(key: string): Observable<string | undefined> {
		return this._entireStorage$
			.pipe(
				map(map1 => map1.get(key)));
	}

	private _allStorage(): Map<string, string> {
		let values = new Map(),
			keys = Object.keys(this._localStorage),
			i = keys.length;
		while (i--) {
			values.set(keys[i], this._localStorage.getItem(keys[i]));
		}
		return values;
	}
}
