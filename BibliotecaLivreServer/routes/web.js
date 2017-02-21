var express = require('express');
var router = express.Router();

var RedirectPage = require('./RedirectPage');
var UserController = require('../public/javascripts/controller/web/UserController');

/* GET */
router.get('/', RedirectPage.homePage);
router.get('/about', RedirectPage.aboutPage);
router.get('/contact', RedirectPage.contactPage);
router.get('/conta/:email', UserController.contaSettings);
router.get('/logout', UserController.realizarLogout);


/* POST */
router.post('/login', UserController.realizarLogin);

module.exports = router;
