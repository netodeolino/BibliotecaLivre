var mongoose = require('mongoose');

// Mongoose Schema definition
var Schema = mongoose.Schema;

var UserSchema = new Schema({
    first_name: String,
    last_name: String,
    email: String
}, {
	versionKey: false // You should be aware of the outcome after set to false
});

var LocalizacaoSchema = new Schema({
    nome: String,
    estado: String,
    pais: String
}, {
	versionKey: false // You should be aware of the outcome after set to false
});

var LivroSchema = new Schema({
    nome: String,
    ano: Number,
    ISBN: String,
    autor: String,
    categoria: String
}, {
	versionKey: false // You should be aware of the outcome after set to false
});

// Mongoose Model definition
mongoose.model('User', UserSchema);
mongoose.model('Localizacao', LocalizacaoSchema);
mongoose.model('Livro', LivroSchema);

// Base connection
mongoose.connect('mongodb://localhost/BibliotecaLivre');