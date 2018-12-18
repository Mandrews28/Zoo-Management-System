import React, { Component } from 'react';
import './ZooManagerDropdown.css';
import Dropdown from 'react-dropdown';
import 'react-dropdown/style.css';

class ZooManagerDropdown extends Component {

	render() {
		const { options } = this.props;
		const { selectedOption } = this.props;

		return (
			<div id="ZooManagerDropdown" style={{ width: 350, marginLeft: "auto", marginRight: "auto" }}>
				<Dropdown options={options} onChange={this.props.onSelectedOption} value={selectedOption} placeholder="Select an option" />
			</div>
		)
	}
}

export default ZooManagerDropdown;