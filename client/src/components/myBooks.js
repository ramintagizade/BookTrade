import React from 'react';
import TradeRequest from './tradeRequest';
import {connect} from 'react-redux';
import {userActions} from './../actions/index'; 


class MyBooks extends React.Component {

	constructor(props) {
		super(props);

		this.state = {
			mybooks: []
		};
	}

	componentDidMount() {
		const {dispatch}  = this.props;
		const email = JSON.parse(localStorage.getItem("user")).email;
		dispatch(userActions.getMyBooks(email));
	}

	componentDidUpdate(prevProps,prevState) {
		if(prevProps.getMyBooks!=this.props.getMyBooks && this.props.getMyBooks["gotMyBooks"]) {
			
			this.setState({
				myBooks:this.props.getMyBooks["user"]
			});
		}
	}

	render() {
		let myBooks;
		if(this.state.myBooks) {
			myBooks = this.state.myBooks.map(function (x,i) {
				console.log("x "  +JSON.stringify(x));
				console.log("i " + i);
				return <div className="myBooks-each" key={i}> <img src={x.url}/> </div>;
			});
		}

		return (
			<div > 
			<TradeRequest/>
			<br/>
			<div className="myBooks">	
				{myBooks}
			</div>

			</div>
		);
	}
}

function mapStateToProps(state) {
	const {getMyBooks}   =  state;
	return {
		getMyBooks
	}
}

MyBooks = connect(mapStateToProps)(MyBooks);

export default MyBooks;