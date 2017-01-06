var mongoose = require('mongoose');

// Mongoose Schema definition
var Schema = mongoose.Schema;

var UserSchema = new Schema({
    first_name: String,
    last_name: String,
    email: String
});

// Mongoose Model definition
mongoose.model('User', UserSchema);
mongoose.connect('mongodb://localhost/BibliotecaLivre');