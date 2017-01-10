var express = require('express');
var router = express.Router();

var UserController = require('../public/javascripts/controller/UserController');
var LivroController = require('../public/javascripts/controller/LivroController');

/* GET home page. */
router.get('/', function(req, res, next) {
	res.render('index', { title: 'Biblioteca Livre' });
});

router.get('/users', UserController.allUsers);
router.post('/saveuser', UserController.saveUser);

router.get('/teste/:first_name', UserController.teste);

router.post('/savelivro', LivroController.saveLivro);

module.exports = router;