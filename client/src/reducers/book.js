
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