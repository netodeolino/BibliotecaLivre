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
		res.redirect('/');
	});
}
