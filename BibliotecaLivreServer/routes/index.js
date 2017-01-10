var express = require('express');
var router = express.Router();

var UserController = require('../public/javascripts/controller/UserController');

/* GET home page. */
router.get('/', function(req, res, next) {
	res.render('index', { title: 'Biblioteca Livre' });
});

router.get('/users', UserController.showUsers);
router.post('/saveuser', UserController.saveUser);

router.get('/teste/:first_name', UserController.teste);

//router.get('/teste', function(req, res, next) {
//	res.render('index', { title: 'Teste Passou' });
//});

/*
router.get('/users/:email', function (req, res) {
    if (req.params.email) {
        User.find({ email: req.params.email }, function (err, docs) {
            res.json(docs);
        });
    }
});
*/

module.exports = router;