import {bookService} from '../services/book';

export const bookActions = {
	getMyBooks,
	getAllBooks
}

function getMyBooks( email ) {

	return dispatch => {
		dispatch(request(email));
		bookService.getMyBooks(email).then(
			user =>  {
				if(user) {
					dispatch(success(user));
				}
				else {
					dispatch(failure(user));
				}
			}
		);
	};

	function request(user) {
		return {
			type:"GET_MY_BOOKS_REQUEST",
			user
		};
	}
	function success(user) {
		return {
			type : "GET_MY_BOOKS_SUCCESS",
			user
		};
	}
	function failure(error) {
		return {
			type: "GET_MY_BOOKS_FAILURE",
			error
		};
	}
}
function getAllBooks() {

	return dispatch => {
		dispatch(request());
		bookService.getAllBooks().then(
			books =>  {
				if(books) {
					dispatch(success(books));
				}
				else {
					dispatch(failure(books));
				}
			}
		);
	};

	function request(book) {
		return {
			type:"GET_ALL_BOOKS_REQUEST",
			book
		};
	}
	function success(book) {
		return {
			type : "GET_ALL_BOOKS_SUCCESS",
			book
		};
	}
	function failure(error) {
		return {
			type: "GET_ALL_BOOKS_FAILURE",
			error
		};
	}
}