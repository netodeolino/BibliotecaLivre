var mongoose = require('mongoose');
var Livro = mongoose.model('Livro');

exports.allLivros = function (req, res, next) {
	// body...
	Livro.find({}, function (err, docs) {
        res.json(docs);
    });
}

exports.teste = function (req, res, next) {
	// body...

	var name = req.params.first_name;

	Livro.find({first_name: name}, function (err, docs) {
        res.json(docs);
    });
}

exports.saveUser = function (req, res, next) {
	new Livro({
		first_name: req.body.first_name,
    	last_name: req.body.last_name,
    	email: req.body.email
	}).save(function(err, user, count){
		res.redirect('/');
	});
}


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