export const bookService = {
	getMyBooks,
	getAllBooks
};

var token =  localStorage.getItem("user") && JSON.parse(localStorage.getItem("user")).token || "";

function getMyBooks(email) {

	const opts = {
		method:"POST",
		headers: new Headers({
	     'Authorization': token, 
	     'Content-Type': 'application/json'
	   	}),
		body:JSON.stringify({email})
	};

	return fetch('http://localhost:8080/books/myBooks', opts).then(res => {
		if(!res.ok) {
			return Promise.reject(res.statusText)
		}
		return res.json(); 
	}).then(book => {
		if(book) {
			console.log("myBooks " + JSON.stringify(book));
		} 
		return book;
	})
}

function getAllBooks(email) {

	const opts = {
		method:"POST",
		headers: new Headers({
	     'Authorization': token, 
	     'Content-Type': 'application/json'
	   	}),
		body:JSON.stringify({})
	};

	return fetch('http://localhost:8080/books/allBooks', opts).then(res => {
		if(!res.ok) {
			return Promise.reject(res.statusText)
		}
		return res.json(); 
	}).then( books => {
		if(books) {
			console.log("books " + JSON.stringify(books));
		} 
		return books;
	})
}
