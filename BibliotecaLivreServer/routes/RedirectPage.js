exports.homePage = function (req , res , next) {
	res.render('home', {title: "Biblioteca Livre"});
};

exports.aboutPage = function (req , res , next) {
	res.render('about', {title: "Sobre"});
};

exports.contactPage = function (req , res , next){
	res.render('contact', {title: "Contato"});
};
