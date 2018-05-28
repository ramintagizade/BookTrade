
export function getMyBooks (state = {}, action ) {
	switch(action.type) {
		case 'GET_MY_BOOKS_REQUEST' : 
			return {
				gettingMyBooks:true,
				user:action.user
			};
		case 'GET_MY_BOOKS_SUCCESS' : 
			return {
				gotMyBooks:true,
				user:action.user
			};
		case 'GET_MY_BOOKS_FAILURE' : 
			return {};
		default :
			return state;
	}
}

export function getAllBooks (state = {}, action ) {
	switch(action.type) {
		case 'GET_ALL_BOOKS_REQUEST' : 
			return {
				gettingAllBooks:true,
				book:action.user
			};
		case 'GET_ALL_BOOKS_SUCCESS' : 
			return {
				gotAllBooks:true,
				book:action.book
			};
		case 'GET_ALL_BOOKS_FAILURE' : 
			return {};
		default :
			return state;
	}
}