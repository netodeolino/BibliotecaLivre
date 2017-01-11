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

router.get('/livros', LivroController.allLivros);
router.post('/savelivro', LivroController.saveLivro);
router.get('/findlivrobyname/:nome', LivroController.findLivroByName);
router.get('/removelivrobyname/:nome', LivroController.removeLivroByName);

module.exports = router;