import React from 'react';
import {connect} from 'react-redux';
import {userActions} from './../actions/index';

class Login extends React.Component {
	
	constructor(props) {
		super(props);
		
		this.state = {
			submitted:false,
			email:"",
			password:"",
			alert:""
		}

		this.handleChange = this.handleChange.bind(this);
		this.handleSubmit = this.handleSubmit.bind(this);

	}

	handleChange(e) {
		const {name, value} = e.target;
		this.setState({
			[name]:value
		});
	}

	handleSubmit(e) {
		e.preventDefault();
		const {email,password} = this.state;
		const {dispatch,auth}  = this.props;
		this.setState({
			submitted:true
		});
		const user = {
			email:email,
			password:password
		};
		
		//if(email && password)
    		//dispatch(userActions.login(user.email,user.password));
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

function mapStateToProps(state) {
	const {auth,alert}   =  state;
	return {
		auth,
		alert
	}
}

Login = connect(mapStateToProps)(Login);

export default Login;
