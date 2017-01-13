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
    	categoria: req.body.categoria
	}).save(function(err, livro, count){
		//res.redirect('/');
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

exports.updateLivroByName = function (req, res, next) {
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

/*
// Não testado
exports.updateLivroByName = function (req, res, next) {
	// body...
	var isbn = req.body.ISBN;

	Livro.find({ISBN: isbn}, function (err, livro) {
		livro.nome = req.body.nome;
		livro.ano = req.body.ano;
    	livro.ISBN = req.body.ISBN;
    	livro.autor = req.body.autor;
    	livro.categoria = req.body.categoria;
    	livro.save(function (err, livro, count) {
    		if (err) {
    			res.json({result: false, data: null});
    		}
    		res.json({result: true, data: livro});
    	});
	});
}
*/


/*
router.get('/users/:email', function (req, res) {
    if (req.params.email) {
        User.find({ email: req.params.email }, function (err, docs) {
            res.json(docs);
        });
    }
});
*/

/* TO EDIT OF THE MY PROJECT
// query db for all todo items
exports.index = function ( req, res ){
  Todo.find( function ( err, todos, count ){
    res.render( 'index', {
      title : 'Express Todo Example',
      todos : todos
    });
  });
};

// remove todo item by its id
exports.destroy = function ( req, res ){
  Todo.findById( req.params.id, function ( err, todo ){
    todo.remove( function ( err, todo ){
      res.redirect( '/' );
    });
  });
};

exports.edit = function ( req, res ){
  Todo.find( function ( err, todos ){
    res.render( 'edit', {
        title   : 'Express Todo Example',
        todos   : todos,
        current : req.params.id
    });
  });
};


// redirect to index when finish
exports.update = function ( req, res ){
  Todo.findById( req.params.id, function ( err, todo ){
    todo.content    = req.body.content;
    todo.updated_at = Date.now();
    todo.save( function ( err, todo, count ){
      res.redirect( '/' );
    });
  });
};

exports.index = function ( req, res ){
  Todo.
    find().
    sort( '-updated_at' ).
    exec( function ( err, todos ){
      res.render( 'index', {
          title : 'Express Todo Example',
          todos : todos
      });
    });
};
 
exports.edit = function ( req, res ){
  Todo.
    find().
    sort( '-updated_at' ).
    exec( function ( err, todos ){
      res.render( 'edit', {
          title   : 'Express Todo Example',
          todos   : todos,
          current : req.params.id
      });
    });
};

*/