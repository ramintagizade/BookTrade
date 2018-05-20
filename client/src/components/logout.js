import React from 'react';
import {userActions} from './../actions/index';
import {connect} from 'react-redux';

class Logout extends React.Component {
	constructor(props) {
		super(props);
	}
	componentDidMount() {
		this.props.dispatch(userActions.logout());
	}
	render() {
		return (
			<div> </div>
		);
	}
}

function mapStateToProps(state) {
	const {auth}   =  state;
	return {
		auth,
	}
}

Logout = connect(mapStateToProps)(Logout)
export default Logout;