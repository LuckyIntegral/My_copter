import { bootstrapApplication } from "@angular/platform-browser";
import { provideHttpClient, withInterceptors } from "@angular/common/http";
import { provideRouter } from "@angular/router";

import { AppComponent } from "./app/app.component";
import { APP_ROUTES } from "./app/app.routes";
import { authInterceptor } from "./app/shared/auth.interceptor";

bootstrapApplication(AppComponent, {
	providers: [
		provideHttpClient(
			withInterceptors([authInterceptor])
		),
		provideRouter(APP_ROUTES)
	]
});