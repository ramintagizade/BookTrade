import React from 'react';
require("../../styles/index.scss");

class TradeBook extends React.Component {
	constructor(props) {
		super(props);
		
		this.state = {
			signed:false
		};
	}

	render() {
		const signed = this.state.signed;

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
  				 		<a className="navbar-right btn btn-default" href="/logout"> Sign out </a>
  				 	}
				</div>
			  </nav>
			 </div>
			 
			</div>
		);
	}
}

export default TradeBook;