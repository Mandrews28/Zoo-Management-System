import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter } from 'react-router-dom';
import './index.css';
import Home from './Home';
import registerServiceWorker from './registerServiceWorker';

ReactDOM.render((
	<BrowserRouter>
		<Home />
	</BrowserRouter>
), document.getElementById('root'));

registerServiceWorker();
