var mongoose = require('mongoose');
var Cidade = mongoose.model('Cidade');

/* Test */
exports.allCidades = function (req, res, next) {
	// body...
	Cidade.find({}, function (err, docs) {
		if (err) {
			res.json({result: false, data: null});
		}
        res.json({result: true, data: docs});
    });
}

/* Test */
exports.allCidadesByEstado = function (req, res, next) {
	// body...
	var est = req.params.estado;

	Cidade.find({estado: est}, function (err, docs) {
		if (err) {
			res.json({result: false, data: null});
		}
        res.json({result: true, data: docs});
    });
}

/* Test */
exports.saveCidade = function (req, res, next) {
	new Cidade({
		nome: req.body.nome,
    	estado: req.body.estado,
    	codigo: req.body.codigo
	}).save(function(err, cidade, count){
		//res.redirect('/');
		if (err) {
			res.json({result: false, data: null});
		}
		res.json({result: true, data: cidade});
	});
}

/* Test */
exports.findCidadeByCodigo = function (req, res, next) {
	// body...
	var cod = req.params.codigo;

	Cidade.find({codigo: cod}, function (err, cidade) {
		if (err) {
			res.json({result: false, data: null});
		}
        res.json({result: true, data: cidade});
    });
}

/* Test */
exports.findCidadeByName = function (req, res, next) {
	// body...
	var nome = req.params.nome;

	Cidade.find({nome: nome}, function (err, cidade) {
		if (err) {
			res.json({result: false, data: null});
		}
        res.json({result: true, data: cidade});
    });
}

/* Test */
exports.removeCidadeByName = function (req, res, next) {
	// body...
	var nome = req.params.nome;

	Cidade.remove({nome: nome}, function (err, cidade) {
		if (err) {
			res.json({result: false, data: null});
		}
		res.json({result: true, data: cidade});
	});
};

/* Test */
exports.updateCidadeByCodigo = function (req, res, next) {
	// body...
	var cod = req.body.codigo;

	Cidade.update({codigo: cod}, {$set: {nome: req.body.nome,
		estado: req.body.estado}}, function (err, cidade, count) {
		if (err) {
			res.json({result: false, data: null});
		}
		res.json({result: true, data: cidade});
	});
}
