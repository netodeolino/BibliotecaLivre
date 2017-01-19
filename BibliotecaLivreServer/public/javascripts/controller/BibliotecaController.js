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

exports.saveBiblioteca = function (req, res, next) {
	new Biblioteca({
		nome: req.body.nome,
		endereco: req.body.endereco,
		cidade: req.body.cidade
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

	Biblioteca.find({nome: nome}, function (err, biblioteca) {
		if (err) {
			res.json({result: false, data: null});
		}
        res.json({result: true, data: biblioteca});
    });
}

/* Test */
exports.findBibliotecaByCidade = function (req, res, next) {
	// body...
	var cid = req.params.cidade;

	Biblioteca.find({cidade: cid}, function (err, biblioteca) {
		if (err) {
			res.json({result: false, data: null});
		}
        res.json({result: true, data: biblioteca});
    });
}

/* Test */
exports.removeBibliotecaByName = function (req, res, next) {
	// body...
	var nome = req.params.nome;

	Biblioteca.remove({nome: nome}, function (err, biblioteca) {
		if (err) {
			res.json({result: false, data: null});
		}
		res.json({result: true, data: biblioteca});
	});
};

/* Test */
exports.updateBibliotecaByName = function (req, res, next) {
	// body...
	var cod = req.body.codigo;

	Biblioteca.update({codigo: cod}, {$set: {nome: req.body.nome,
		estado: req.body.estado}}, function (err, biblioteca, count) {
		if (err) {
			res.json({result: false, data: null});
		}
		res.json({result: true, data: biblioteca});
	});
}
