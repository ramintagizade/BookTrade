import React from 'react';

class TradeRequest extends React.Component {

	constructor(props) {
		super(props);
	}

	render () {
		
		return (
			<div> 
				<div >  
					<p className="treqForu"> Trade Requests for you </p>
				</div>
				<div > 
					<p className="treqFromu">Trade Requests from you </p>
				</div>
			</div>
		);
	}
}

export default TradeRequest;