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

// Mongoose Model definition
mongoose.model('User', UserSchema);
mongoose.connect('mongodb://localhost/BibliotecaLivre');