package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Rule;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.beans.LoginForm;
import views.html.index;
import views.html.login;

public class Application extends Controller {

	@Security.Authenticated(POCAuthenticator.class)
	public static Result index() {
		List<Rule> rules = new ArrayList<Rule>();
		Rule ruleOne = new Rule();
		ruleOne.name = "One";

		Rule ruleTwo = new Rule();
		ruleTwo.name = "Two";

		rules.add(ruleOne);
		rules.add(ruleTwo);

		return ok(index.render("Welcome", User.findByEmail(session("email")), rules));
	}

	public static Result login() {
		Form<LoginForm> loginForm = Form.form(LoginForm.class);
		return ok(login.render(loginForm));
	}

	public static Result authenticate() {
		Form<LoginForm> loginForm = Form.form(LoginForm.class)
				.bindFromRequest();

		if (loginForm.hasErrors()) {
			return badRequest(login.render(loginForm));
		} else {
			session().clear();
			session("email", loginForm.get().email);
		}

		return redirect(routes.Application.index());
	}

	public static Result logout() {
		session().clear();
		return redirect(routes.Application.login());
	}
}
