var mongoose = require('mongoose');
mongoose.Promise = global.Promise;

// Mongoose Schema definition
var Schema = mongoose.Schema;

var UserSchema = new Schema({
    first_name: String,
    last_name: String,
    email: String
}, {
	versionKey: false // You should be aware of the outcome after set to false
});

var CidadeSchema = new Schema({
    nome: String,
    estado: String, /* Cadastrado pela Sigla */
    codigo: Number
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

var BibliotecaSchema = new Schema({
	nome: String,
	endereco: String,
	cidade: String
}, {
	versionKey: false // You should be aware of the outcome after set to false
});

var SchemaBibliotecaLivre = new Schema({
	codigo_cidade: Number,
	nome_cidade: String,
	sigla_estado: String,
	bibliotecas: [{
		nome_biblioteca: String,
		endereco_biblioteca: String,
		livros: [{
			nome_livro: String,
			ano_livro: Number,
			ISBN_livro: String,
			autor_livro: String,
			categoria_livro: String
		}]
	}]
}, {
	versionKey: false // You should be aware of the outcome after set to false
});

// Mongoose Model definition
mongoose.model('User', UserSchema);
mongoose.model('Cidade', CidadeSchema);
mongoose.model('Livro', LivroSchema);
mongoose.model('Biblioteca', BibliotecaSchema);

// Base connection
mongoose.connect('mongodb://localhost/BibliotecaLivre');