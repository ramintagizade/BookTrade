import React from 'react';


class Login extends React.Component {
	
	constructor(props) {
		super(props);
		
		this.state = {
			submitted:false,
			email:"",
			password:"",
			alert:""
		}

	}


	render() {
		const {submitted,password,email} = this.state;
		const {alert} = this.props;

		return (
			<div className="login-body"> 
				<form onSubmit={this.handleSubmit}>
				  <div className="form-group">
				    <label htmlFor="inputEmail">Email address</label>
				    <input type="email" className="form-control" name="email" id="inputEmail" aria-describedby="emailHelp" placeholder="Enter email" onChange={this.handleChange}/>
				  	{ submitted && !email && 
				  		<div className="alert-block"> Email is required </div>
				  	}
				  </div>
				  <div className="form-group">
				    <label htmlFor="inputPassword">Password</label>
				    <input type="password" className="form-control" name="password" id="inputPassword" placeholder="Password"
						onChange={this.handleChange}/>
					{ submitted && !password && 
						<div className="alert-block">Password is required</div>
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


export default Login;