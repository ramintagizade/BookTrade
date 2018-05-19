import React from 'react';

class Register extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
			username:"",
			email:"",
			password:"",
			submitted:""
		};
	}
	
	render() {

		const {username,email,password,submitted } = this.state;
		const {alert} = this.props;
		
		return (
			<div className="register-body"> 
				<form onSubmit={this.handleSubmit}>
				  <div className="form-group">
				    <label htmlFor="inputUsername">User name</label>
				    <input type="text" className="form-control" name="username" id="inputUsername" aria-describedby="usernameHelp" placeholder="Enter username" onChange={this.handleChange}/>
				  	{ submitted && !username && 
				  		<div className="alert-block">Username is required</div>
				  	}
				  </div>
				  <div className="form-group">
				    <label htmlFor="inputEmail">Email address</label>
				    <input type="email" className="form-control" name="email" id="inputEmail" aria-describedby="emailHelp" placeholder="Enter email" onChange={this.handleChange}/>
				  	{ submitted && !email && 
				  		<div className="alert-block">Email is required </div>
				  	}
				  </div>
				  <div className="form-group">
				    <label htmlFor="inputPassword">Password</label>
				    <input type="password" className="form-control" name="password" id="inputPassword" placeholder="Password" onChange={this.handleChange}/>
				  	{ submitted && !password && 
				  		<div className="alert-block"> Password is required </div>
				  	}
				  </div>
				   {
				  	submitted && alert.type==="alert-error" && 
				  	<div className="alert-block"> {alert.message.message} </div>
				  	}
				  <button type="submit" className="btn btn-primary">Submit</button>
				</form>
			</div>
		);
	}
}

export default Register;