import React from 'react';
import {connect} from 'react-redux';
import {userActions} from './../actions/index';


class Settings extends React.Component {

	constructor(props) {
		super(props);

		this.state = {
			username:"",
			email:"",
			password:"",
			submitted:false,
			newPassword:""
		};

		this.handleChange = this.handleChange.bind(this);
		this.handleSubmit = this.handleSubmit.bind(this);
	}

	handleChange(e) {
		const {name,value} = e.target;

		this.setState({
			[name]:value
		});
	}

	handleSubmit(e) {

		e.preventDefault();

		const {username,newPassword, password} = this.state;
		const email = JSON.parse(localStorage.getItem("user")).email;
		const {dispatch,settings}  = this.props;

		this.setState({
			submitted:true
		});

		const user = {
			username:username,
			email:email,
			password:password,
			newPassword:newPassword
		};
	
		if(user.username && user.email && user.password && user.newPassword) {
    		dispatch(userActions.updateSettings(user.username,user.email,user.password,user.newPassword));
		}
	}

	render() {

		const {username,email,password,submitted } = this.state;

		return (
			<div className="register-body"> 
				<p className="text-center"> <strong >Update Settings </strong> </p>
				<span></span>
				<form onSubmit={this.handleSubmit}>
				  <div className="form-group">
				    <label htmlFor="inputUsername">New User Name</label>
				    <input type="text" className="form-control" name="username" id="inputUsername" aria-describedby="usernameHelp" placeholder="Enter username" onChange={this.handleChange}/>
				  	{ submitted && !username && 
				  		<div className="alert-block">Username is required</div>
				  	}
				  </div>
				  <div className="form-group">
				    <label htmlFor="inputPassword">Password</label>
				    <input type="password" className="form-control" name="password" id="inputPassword" placeholder="Password" onChange={this.handleChange}/>
				  	{ submitted && !password && 
				  		<div className="alert-block"> Password is required </div>
				  	}
				  </div>
				   <div className="form-group">
				    <label htmlFor="newInputPassword">New Password</label>
				    <input type="password" className="form-control" name="newPassword" id="newInputPassword" placeholder="Password" onChange={this.handleChange}/>
				  	{ submitted && !password && 
				  		<div className="alert-block"> Password is required </div>
				  	}
				  </div>

				  <button type="submit" className="btn btn-primary">Submit</button>
				</form>
			</div>
		);
	}
}
function mapStateToProps(state) {

	const {settings,alert}   =  state;

	return {
		settings,
		alert,
	}
}

Settings = connect(mapStateToProps)(Settings);

export default Settings;