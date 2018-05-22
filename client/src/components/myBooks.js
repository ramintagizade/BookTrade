import React from 'react';
import TradeRequest from './tradeRequest';
import {connect} from 'react-redux';
import {userActions} from './../actions/index'; 


class MyBooks extends React.Component {

	constructor(props) {
		super(props);
	}

	componentDidMount() {
		const {dispatch}  = this.props;
		const email = JSON.parse(localStorage.getItem("user")).email;
		dispatch(userActions.getMyBooks(email));
	}

	render() {
		return (
			<div > 
			<TradeRequest/>

			My Books 

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

MyBooks = connect(mapStateToProps)(MyBooks);

export default MyBooks;