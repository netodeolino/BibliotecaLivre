var express = require('express');
var router = express.Router();

var RedirectPage = require('./RedirectPage');
var UserController = require('../public/javascripts/controller/UserController');

/* GET */
router.get('/', function(req, res, next) {
	res.render('home', { title: 'Biblioteca Livre' });
});

router.get('/about', RedirectPage.aboutPage);
router.get('/contact', RedirectPage.contactPage);


/* POST */
router.post('/login', UserController.realizarLoginWeb);

module.exports = router;
