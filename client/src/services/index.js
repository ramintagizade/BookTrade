
export const userService = {
	login,
	logout,
	register,
	updateSettings,
	getMyBooks
};


var token =  localStorage.getItem("user") && JSON.parse(localStorage.getItem("user")).token || "";

function login(email, password) {
	const opts = {
		method:"POST",
		headers: new Headers({
	     'Content-Type': 'application/json'
	   	}),
		body:JSON.stringify({email,password})
	}; 

	return fetch('http://localhost:8080/auth/login', opts).then(res => {
		if(!res.ok) {
			return Promise.reject(res.statusText)
		}
		return res.json(); 
	}).then( user => {
		if(user && user.token) {
			localStorage.setItem('user', JSON.stringify({token:user.token,email:user.email}));
		}
		return user;
	})
}

function register(username, email, password) {
	const opts = {
		method:"POST",
		headers: {"Content-Type": "application/json"},
		body:JSON.stringify({username, email, password})
	};

	return fetch('http://localhost:8080/auth/register', opts).then(res => {
		if(!res.ok) {
			return Promise.reject(res.statusText)
		}
		console.log("user " +JSON.stringify(res));
		return res.json(); 
	}).then( user => {
		console.log("user " + JSON.stringify(user));
		if(user && user.token) {
			localStorage.setItem('user', JSON.stringify({token:user.token,email:user.email}));
		}
		return user;
	})
}

function updateSettings(username, email, password, newPassword) {
	const opts = {
		method:"POST",
		headers: {"Content-Type": "application/json"},
		body:JSON.stringify({username, email, password,newPassword})
	};

	return fetch('http://localhost:8080/auth/settings', opts).then(res => {
		if(!res.ok) {
			return Promise.reject(res.statusText)
		}
		console.log("user " +JSON.stringify(res));
		return res.json(); 
	}).then( user => {
		console.log("user " + JSON.stringify(user));
		if(user && user.token) {
			localStorage.setItem('user', JSON.stringify({token:user.token,email:user.email}));
		}
		return user;
	})
}


function logout() {
	localStorage.removeItem("user");
	window.location.href="/";
}

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
	}).then( user => {
		if(user) {
			console.log("user " + JSON.stringify(user));
		} 
		return user;
	})
}
