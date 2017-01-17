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
	cidade: [{type: Schema.Types.ObjectId, ref: 'Cidade'}],
	livros: [{type: Schema.Types.ObjectId, ref: 'Livro'}]
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