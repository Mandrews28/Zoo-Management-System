import React, { Component } from 'react';
import './ZooVersionDropdown.css';
import Dropdown from 'react-dropdown';
import 'react-dropdown/style.css';

class ZooVersionDropdown extends Component {

	render() {
		const { options } = this.props;
		const { selectedOption } = this.props;

		return (
			<div id="ZooVersionDropdown" style={{ width: 350, marginLeft: "auto", marginRight: "auto", display: "" }}>
				<Dropdown options={options} onChange={this.props.onSelectedOption} value={selectedOption} placeholder="Select an option" />
			</div>
		)
	}
}

export default ZooVersionDropdown;