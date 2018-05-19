
export const userService = {
	login,
	logout,
	register,
};

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
		return res.json(); 
	}).then( user => {
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

