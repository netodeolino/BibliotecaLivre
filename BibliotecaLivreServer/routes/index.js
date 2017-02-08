var express = require('express');
var router = express.Router();

var UserController = require('../public/javascripts/controller/UserController');
var LivroController = require('../public/javascripts/controller/LivroController');
var CidadeController = require('../public/javascripts/controller/CidadeController');
var BibliotecaController = require('../public/javascripts/controller/BibliotecaController');
var EmailUtil = require('../public/javascripts/util/SendEmail');


/* GET home page. */
router.get('/', function(req, res, next) {
	res.render('index', { title: 'Biblioteca Livre' });
});

/* User POST */
router.post('/saveuser', UserController.saveUser);
router.post('/loginuser', UserController.loginUser);

/* User GET */
router.get('/users', UserController.allUsers);


/* Livro POST */
router.post('/savelivro', LivroController.saveLivro); // Update Web
router.post('/updatelivrobyisbn', LivroController.updateLivroByISBN);

/* Livro GET */
router.get('/findalllivros', LivroController.allLivros); // Test
router.get('/findlivrobyname/:nome', LivroController.findLivroByName); // Test
router.get('/findlivrobyisbn/:ISBN', LivroController.findLivroByISBN);
router.get('/findlivrobybiblioteca/:bibliotecacod', LivroController.findLivroByBiblioteca);
router.get('/removelivrobyname/:nome', LivroController.removeLivroByName);


/* Cidade POST */
router.post('/savecidade', CidadeController.saveCidade);
router.post('/updatecidadebycodigo', CidadeController.updateCidadeByCodigo); // Test

/* Cidade GET */
router.get('/findallcidades', CidadeController.allCidades); // Test
router.get('/findallcidadesbyestado/:estado', CidadeController.allCidadesByEstado);
router.get('/findcidadebycodigo/:codigo', CidadeController.findCidadeByCodigo); // Test
router.get('/findcidadebyname/:nome', CidadeController.findCidadeByName); // Test
router.get('/removecidadebyname/:nome', CidadeController.removeCidadeByName); // Test
router.get('/removecidadebycodigo/:codigo', CidadeController.updateCidadeByCodigo); // Test


/* Biblioteca POST*/
router.post('/savebiblioteca', BibliotecaController.saveBiblioteca);

/* Biblioteca GET */
router.get('/findbibliotecabycidade/:cidadecod', BibliotecaController.findBibliotecaByCidade);


/* Email GET */
router.get('/email/:email', EmailUtil.recoverPassword);

module.exports = router;