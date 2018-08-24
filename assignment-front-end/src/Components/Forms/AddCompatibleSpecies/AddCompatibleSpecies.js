import React, { Component } from 'react';
import './AddCompatibleSpecies.css';
import Dropdown from 'react-dropdown';
import 'react-dropdown/style.css';

class AddCompatibleSpecies extends Component {
	constructor() {
		super();
		
		this.state = {
			selectedSpecies1: "",
			selectedSpecies2: "",
			speciesNames: []
		}
	}
	
	componentDidMount() {
		const { speciesList } = this.props;
		Object.entries(speciesList).forEach(
			([speciesName, speciesValue]) => {
				this.state.speciesNames.push(speciesName);
			}
		);
	}

	submit = async () => {
		if (this.state.selectedSpecies1 === "" || this.state.selectedSpecies2 === "") {
			alert("Please specify two different species")
		} else {
			const payload = {
				species1: this.state.selectedSpecies1.value,
				species2: this.state.selectedSpecies2.value,
			};
			const response = await fetch('http://localhost:8080/add-compatible-species', {
				method: 'post',
				body: JSON.stringify(payload),
				headers: {
					'content-type': 'application/json',
				}
			});
			if (await response.status === 200) {
				response.json().then(res => {
					this.setState({selectedSpecies1: "", selectedSpecies2: ""});
					if (res.penListById) {
						this.props.newZoo(res);
					} else {
						alert(res.result);
					}
				});
			}
		}
	}

	getSelectedSpecies1 = (selectedSpecies) => {
		const selectedSpecies1 = this.checkSpecies(selectedSpecies, 1);
		this.setState({ selectedSpecies1 });
	}

	getSelectedSpecies2 = (selectedSpecies) => {
		const selectedSpecies2 = this.checkSpecies(selectedSpecies, 2);
		this.setState({ selectedSpecies2 });
	}

	checkSpecies = (thisSpecies, num) => {
		var otherSpecies = "";
		(num === 1) ? otherSpecies = this.state.selectedSpecies2 : otherSpecies = this.state.selectedSpecies1;
		if ((otherSpecies != null) && (JSON.stringify(otherSpecies) === JSON.stringify(thisSpecies))) {
			alert("Species is already compatible with itself!");
			thisSpecies = "";
		}
		return thisSpecies;
	}

	render() {

		return (
			<div className="addCompatibleSpecies" id="Add Compatible Species" style={{display: "none"}}>
				<div style={{ width: 300, marginLeft: "auto", marginRight: "auto", marginBottom: "15px" }}>
					<Dropdown options={this.state.speciesNames} onChange={this.getSelectedSpecies1} value={this.state.selectedSpecies1} placeholder="Select species 1" />
				</div>
				<div style={{ width: 300, marginLeft: "auto", marginRight: "auto" }}>
					<Dropdown options={this.state.speciesNames} onChange={this.getSelectedSpecies2} value={this.state.selectedSpecies2} placeholder="Select species 2" />
				</div>
				<div>
					<button onClick={this.submit} id="submit">Add Compatible Species</button>
				</div>
			</div>
		)
	}
}

export default AddCompatibleSpecies;