package controllers;

import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;

public class POCAuthenticator extends Security.Authenticator {
	@Override
	public String getUsername(Context ctx) {
		return ctx.session().get("email");
	}

	@Override
	public Result onUnauthorized(Context arg0) {
		return redirect(routes.Application.login());
	}
}
