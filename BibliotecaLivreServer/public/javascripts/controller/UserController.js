var mongoose = require('mongoose');
var User = mongoose.model('User');

exports.showUsers = function (req, res, next) {
	// body...
	User.find({}, function (err, docs) {
        res.json(docs);
    });
}
