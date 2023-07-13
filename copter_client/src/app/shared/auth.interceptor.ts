import { HttpInterceptorFn } from "@angular/common/http";
import { tap } from "rxjs";
import { appSettings } from "../app.const";

export const authInterceptor: HttpInterceptorFn = (req, next) => {

    if (req.url.startsWith(appSettings.apiCustomer) ||
        req.url.startsWith(appSettings.apiManager) ||
        req.url.startsWith(appSettings.apiAdmin)) {
        const token = localStorage.getItem('token');
        if (token) {
            const accessToken = JSON.parse(token).access_token;
            const headers = req.headers.set('Authorization', 'Bearer ' + accessToken);
            req = req.clone({headers});
        }
    }

    return next(req).pipe(
        tap(resp => console.log('response', resp))
    );
}
