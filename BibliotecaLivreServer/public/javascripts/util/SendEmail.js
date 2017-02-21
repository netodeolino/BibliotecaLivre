'use strict';
const nodemailer = require('nodemailer');
var UserController = require('../../../public/javascripts/controller/app/UserController');
var randomstring = Math.random().toString(36).slice(-8);


exports.recoverPassword = function (req, res, next) {
	var email = req.params.email;

    // create reusable transporter object using the default SMTP transport
    let transporter = nodemailer.createTransport({
        service: 'gmail',
        auth: {
            user: 'netofalso2@gmail.com',
            pass: 'falsiane123'
        }
    });

    // setup email data with unicode symbols
    let mailOptions = {
        from: '"Biblioteca Livre" <netofalso2@gmail.com>', // sender address
        to: email, // list of receivers
        subject: 'Nova senha Biblioteca Livre', // Subject line
        text: 'Sua nova senha é: ' + randomstring, // plain text body
        html: 'Sua nova senha é: ' + randomstring // html body
    };

    UserController.updateUserByEmail(email, randomstring, function(callback) {
        if (callback) {
            // send mail with defined transport object
            transporter.sendMail(mailOptions, (error, info) => {
                if (error) {
                    res.json({result: false, data: null});
                }
                //console.log('Message %s sent: %s', info.messageId, info.response);
                res.json({result: true, data: info});
            });
        } else {
            console.log("Erro na atualização da senha!");
        }
    });
}