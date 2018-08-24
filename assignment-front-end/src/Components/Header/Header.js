import React, { Component } from 'react';
import logo from './logo.svg';
import './Header.css';

class Header extends Component {
	render() {
		return (
			<header className="Header">
				<img src={logo} className="Header-logo" alt="logo" />
				<h1 className="Header-title">Interactive Zoo Management System</h1>
			</header>
		);
	}
}

export default Header;
