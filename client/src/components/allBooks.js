import React from 'react';
import TradeRequest from './tradeRequest';
import {connect} from 'react-redux';
import {bookActions} from './../actions/book'; 

class AllBooks extends React.Component {

	constructor(props) {
		super(props);

		this.state = {
			allbooks: []
		};

		this.requestBook = this.requestBook.bind(this);
	}

	componentDidMount() {
		const {dispatch}  = this.props;
		dispatch(bookActions.getAllBooks());
	}

	componentDidUpdate(prevProps,prevState) {
		if(prevProps.getAllBooks!=this.props.getAllBooks && this.props.getAllBooks["gotAllBooks"]) {
			
			this.setState({
				allBooks:this.props.getAllBooks["book"]
			});
		}
	}

	requestBook(book) {
		console.log("request book: " + JSON.stringify(book));
	}

	render() {
		var self = this;

		let allBooks
		if(this.state.allBooks) {

			allBooks = this.state.allBooks.map(function (x,i) {
				console.log("x "  +JSON.stringify(x));
				console.log("i " + i);

				return <div className="allBooks-each" key={i}> 
					<img src={x.url}/>  
					<div className="logo" > 
						<img data = {x} onClick={() => self.requestBook(x)} className="active" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSjuXqngtXmv1rNogLdpCzdtz7cXqnneVmWSQu928dqNIrO7v-F0g"/>
					</div>
					</div>;
			});
		}

		return (
			<div> 
				<TradeRequest />
				<br/>
				<div className="allBooks">	
					{allBooks}
				</div>
			</div>
		);
	}
}
function mapStateToProps(state) {
	const {getAllBooks}   =  state;
	return {
		getAllBooks
	}
}

AllBooks = connect(mapStateToProps)(AllBooks);
export default AllBooks;