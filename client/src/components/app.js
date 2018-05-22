import React from 'react';
require("../../styles/index.scss");
import { BrowserRouter as HashRouter, Route, Link } from 'react-router-dom';
import { Switch, Redirect,browserHistory } from 'react-router';
import Login from './login';
import Register from './register';
import Logout from './logout';
import Home from './home';
import Settings from './settings';
import AllBooks from './allBooks';
import MyBooks from './myBooks';

class TradeBook extends React.Component {
	constructor(props) {
		super(props);
		
		this.state = {
			signed:false
		};
	}

	render() {
		const signed = localStorage.getItem("user");

		return (
			<div>
			 <div className="navar-menu">
			  <nav className="navbar  navbar-dark bg-primary">
 				 <a className="navbar-brand" href="/">
    				Book Trade Club
  				</a>
  				
  				 <div className="navbar-auth">
  				 	{	!signed &&
  				 		<a className="navbar-right btn btn-success" href="/register"> Sign Up</a>
  				 	}
  				 	{	!signed && 
  				 		<a className="navbar-right btn btn-default" href="/login"> Sign In </a>
  				 	}
  				 	{	signed && 
  				 		<a className="navbar-right navbar- btn btn-default" href="/logout"> Sign out </a>
  				 	}
  				 	{  	signed && 
  				 		<a href="#" className="navbar-right btn btn-info btn-md" href="/settings">
          					<span className="glyphicon glyphicon-cog"></span> 
        				</a>
  				 	}
  				 	{	signed && 
  				 		<a className="navbar-right btn btn-default" href="/allBooks"> All Books </a>
  				 	}{	signed && 
  				 		<a className="navbar-right btn btn-default" href="/myBooks"> My Books </a>
  				 	}
  				 	
				</div>
			  </nav>
			 </div>
			  <HashRouter >
		    	<div>
		        	<Switch>		
		        		<Route exact path="/" component={Home} />
		          		<Route exact path="/login" component={ Login} />
		          		<Route exact path="/register" component={ Register} />
		          		<Route exact path="/logout" component={Logout} />
		          		<Route exact path="/settings" component={Settings} />
		          		<Route exact path="/allBooks" component={AllBooks} />	
		          		<Route exact path="/myBooks" component={MyBooks} />				
		        	</Switch>
		    	</div>
		  	</HashRouter>
			  
			</div>
		);
	}
}

export default TradeBook;