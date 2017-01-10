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


module.exports = router;