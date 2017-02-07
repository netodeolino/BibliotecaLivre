'use strict';
const nodemailer = require('nodemailer');
var randomstring = Math.random().toString(36).slice(-8);

exports.sendEmail = function (req, res, next) {

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
        to: email,
        //to: 'netodeolino@outlook.com', // list of receivers
        subject: 'Nova senha Biblioteca Livre', // Subject line
        text: 'Sua nova senha é: ' + randomstring, // plain text body
        html: 'Sua nova senha é: ' + randomstring // html body
    };

    // send mail with defined transport object
    transporter.sendMail(mailOptions, (error, info) => {
        if (error) {
            return console.log(error);
        }
        console.log('Message %s sent: %s', info.messageId, info.response);
    });
}