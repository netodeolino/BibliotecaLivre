var mongoose = require('mongoose');
var User = mongoose.model('User');


exports.realizarLogin = function (req, res, next) {
	// body...
	var input = JSON.parse(JSON.stringify(req.body));

	var login = input.Login;
	var pass = input.Senha;

	User.find({email: login, senha: pass}, function (err, user) {
		if (err) {
			console.log(err);
			//res.json({result: false, data: null});
		}
		req.session.emailUser = user[0].email;
		res.redirect('/web');
		//res.json({result: true, data: user});
	});
}

exports.contaSettings = function (req, res, next) {
	// body...
	var mail = req.params.email;

	User.find({email: mail}, function (err, user) {
		if (err) {
			console.log(err);
		}
		res.render('settings', {title: 'Conta', dados: user});
	});
}