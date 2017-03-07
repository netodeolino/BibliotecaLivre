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
		}
		req.session.emailUser = user[0].email;
		res.redirect('/web');
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

exports.realizarLogout = function (req, res, next) {
	// body...
	req.session.emailUser = null;
	res.redirect('/web');
}

exports.realizarCadastro = function (req, res, next) {
	// body...
	res.render('cadastro', {title: 'Cadastro'});
}

function cancelarCadastro () {
	// body...
	window.location.href = '/web';
}

exports.saveUser = function (req, res, next) {
	// body...
	var input = JSON.parse(JSON.stringify(req.body));
	
	new User({
		nome: input.InputName,
    	email: input.InputEmail,
    	senha: input.InputSenha
	}).save(function(err, user, count){
		if (err) {
			console.log(err);
		}
		res.render('settings', {title: 'Conta', dados: user});
	});
}

exports.deleteUser = function (req, res, next) {
	// body...
	var mail = req.params.email;

	User.remove({email: mail}, function (err, user) {
		if (err) {
			console.log(err);
		}
		res.redirect('/web');
	});
};