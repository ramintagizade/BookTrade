import React from 'react';
import ReactDOM from 'react-dom';
import TradeBook from './components/app';
import store from './helpers/store';
import {Provider} from 'react-redux';


ReactDOM.render(
	<Provider store= {store}>
		<TradeBook/>
	</Provider>,
	document.getElementById("root")
);