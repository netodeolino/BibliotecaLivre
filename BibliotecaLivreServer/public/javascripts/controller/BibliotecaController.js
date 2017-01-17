var mongoose = require('mongoose');
var Biblioteca = mongoose.model('Biblioteca');

/* Test */
exports.allBibliotecas = function (req, res, next) {
	// body...
	Biblioteca.find({}, function (err, docs) {
		if (err) {
			res.json({result: false, data: null});
		}
        res.json({result: true, data: docs});
    });
}

exports.allBibliotecasByEstado = function (req, res, next) {
	// body...
	var est = req.params.estado;

	Biblioteca.find({estado: est}, function (err, docs) {
		if (err) {
			res.json({result: false, data: null});
		}
        res.json({result: true, data: docs});
    });
}

/* Test */
exports.saveBiblioteca = function (req, res, next) {
	new Biblioteca({
		nome: req.body.nome,
		endereco: req.body.endereco,
    	cidade: req.body.cidade,
    	livros: req.body.livros
	}).save(function(err, biblioteca, count){
		if (err) {
			res.json({result: false, data: null});
		}
		res.json({result: true, data: biblioteca});
	});
}

/* Test */
exports.findBibliotecaByName = function (req, res, next) {
	// body...
	var nome = req.params.nome;

	Biblioteca.find({nome: nome}, function (err, cidade) {
		if (err) {
			res.json({result: false, data: null});
		}
        res.json({result: true, data: cidade});
    });
}

/* Test */
exports.removeBibliotecaByName = function (req, res, next) {
	// body...
	var nome = req.params.nome;

	Biblioteca.remove({nome: nome}, function (err, cidade) {
		if (err) {
			res.json({result: false, data: null});
		}
		res.json({result: true, data: cidade});
	});
};

/* Test */
exports.updateBibliotecaByName = function (req, res, next) {
	// body...
	var cod = req.body.codigo;

	Biblioteca.update({codigo: cod}, {$set: {nome: req.body.nome,
		estado: req.body.estado}}, function (err, cidade, count) {
		if (err) {
			res.json({result: false, data: null});
		}
		res.json({result: true, data: cidade});
	});
}
