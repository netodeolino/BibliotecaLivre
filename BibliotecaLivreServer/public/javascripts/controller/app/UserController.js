var mongoose = require('mongoose');
var User = mongoose.model('User');

exports.allUsers = function (req, res, next) {
	// body...
	User.find({}, function (err, docs) {
        res.json(docs);
    });
}

exports.saveUser = function (req, res, next) {
	new User({
		nome: req.body.nome,
    	email: req.body.email,
    	senha: req.body.senha
	}).save(function(err, user, count){
		if (err) {
			res.json({result: false, data: null});
		}
		res.json({result: true, data: user});
		//res.redirect('/');
	});
}

exports.loginUser = function (req, res, next) {
	// body...
	var mail = req.body.email;
	var pass = req.body.senha;

	User.find({email: mail, senha: pass}, function (err, user) {
		if (err) {
			res.json({result: false, data: null});
		}
		res.json({result: true, data: user});
	});
}

exports.updateUserByEmail = function (email, senha, callback) {
	// body...
	User.update({email: email}, {$set: {senha: senha}}, function (err, user, count) {
		if (err) {
			callback(false);
		}
		callback(true);
	});
}

exports.realizarLoginWeb = function (req, res, next){
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