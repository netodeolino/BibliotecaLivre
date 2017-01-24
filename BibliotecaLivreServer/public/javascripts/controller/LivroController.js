var mongoose = require('mongoose');
var Livro = mongoose.model('Livro');

exports.allLivros = function (req, res, next) {
	// body...
	Livro.find({}, function (err, docs) {
		if (err) {
			res.json({result: false, data: null});
		}
        res.json({result: true, data: docs});
    });
}

exports.saveLivro = function (req, res, next) {
	new Livro({
		nome: req.body.nome,
    	ano: req.body.ano,
    	ISBN: req.body.ISBN,
    	autor: req.body.autor,
    	categoria: req.body.categoria,
    	bibliotecacod: req.body.biblioteca
	}).save(function(err, livro, count){
		if (err) {
			res.json({result: false, data: null});
		}
		res.json({result: true, data: livro});
	});
}

/* Test */
exports.findLivroByISBN = function (req, res, next) {
	// body...
	var isbn = req.params.ISBN;

	Livro.find({ISBN: isbn}, function (err, livro) {
		if (err) {
			res.json({result: false, data: null});
		}
        res.json({result: true, data: livro});
    });
}

exports.findLivroByName = function (req, res, next) {
	// body...
	var nome = req.params.nome;

	Livro.find({nome: nome}, function (err, livro) {
		if (err) {
			res.json({result: false, data: null});
		}
        res.json({result: true, data: livro});
    });
}

exports.findLivroByBiblioteca = function (req, res, next) {
	// body...
	var cod = req.params.bibliotecacod;

	Livro.find({bibliotecacod: cod}, function (err, livro) {
		if (err) {
			res.json({result: false, data: null});
		}
        res.json({result: true, data: livro});
    });
}

exports.removeLivroByName = function (req, res, next) {
	// body...
	var nome = req.params.nome;

	Livro.remove({nome: nome}, function (err, livro) {
		if (err) {
			res.json({result: false, data: null});
		}
		res.json({result: true, data: livro});
	});
};

exports.updateLivroByISBN = function (req, res, next) {
	// body...
	var isbn = req.body.ISBN;

	Livro.update({ISBN: isbn}, {$set: {nome: req.body.nome, ano: req.body.ano, 
		autor: req.body.autor, categoria: req.body.categoria}}, function (err, livro, count) {
		if (err) {
			res.json({result: false, data: null});
		}
		res.json({result: true, data: livro});
	});
}
