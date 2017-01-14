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
		first_name: req.body.first_name,
    	last_name: req.body.last_name,
    	email: req.body.email
	}).save(function(err, user, count){
		res.redirect('/');
	});
}
