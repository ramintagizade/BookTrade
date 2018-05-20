import React from 'react';

class Home extends React.Component {

	constructor(props) {
		super(props);

	}

	render() {
		
		return (
			<div className="home"> 
				<div className="main">
					<p className="text-center">
						<strong >Book Trade Club </strong>
						
					</p>
				</div>
				<div className="features">
					<div >
						<strong> Features: </strong>
						
					</div>
					<div className="features-list">
						Catalogue your books 
					</div>
					<div className="features-list">
						See all of other users' books
					</div>
					<div className="features-list">
						Request to trade books 
					</div>
					<div className="features-list">
						Manage your books and requests
					</div>
				</div>
			</div> 
		);
	}
}

export default Home;