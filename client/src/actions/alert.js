export const alert = {
	success,
	error
};

function success(message) {
	return {
		type:'ALERT.SUCCESS',
		message
	};
}

function error(message) {
	return {
		type:'ALERT.ERROR',
		message
	}
}