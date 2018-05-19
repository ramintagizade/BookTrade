let user = localStorage.getItem("user");
const iniState = user ? {loggedIn:true,user} : {};

export function auth (state = iniState, action ) {
	switch(action.type) {
		case 'LOGIN_REQUEST' : 
			return {
				loggingIn:true,
				user:action.user
			};
		case 'LOGIN_SUCCESS' : 
			return {
				loggedIn:true,
				user:action.user
			};
		case 'LOGIN_FAILURE' : 
			return {};
		case 'LOGOUT' :
			return {};
		default :
			return state;
	}
}

export function register (state = {}, action ) {
	switch(action.type) {
		case 'REGISTER_REQUEST' : 
			return {
				registeringIn:true,
				user:action.user
			};
		case 'REGISTER_SUCCESS' : 
			return {
				registeredIn:true,
				user:action.user
			};
		case 'REGISTER_FAILURE' : 
			return {};
		default :
			return state;
	}
}

export function alert( state = {} , action ) {
	switch(action.type) {
		case 'ALERT.SUCCESS' :
			return {
				type:'alert-success',
				message:action.message
			};
		case 'ALERT.ERROR' :
			return {
				type:'alert-error',
				message:action.message
			}; 
		default:
			return state;
	}
}