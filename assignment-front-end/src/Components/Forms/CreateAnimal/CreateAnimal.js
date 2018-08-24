import React, { Component } from 'react';
import './CreateAnimal.css';
import Dropdown from 'react-dropdown';
import 'react-dropdown/style.css';

class CreateAnimal extends Component {
	constructor() {
		super();
		
		this.state = {
			animalName: "",
			selectedSpecies: "",
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
	
	updateAnimalName = (event) => {
		const textarea = event.currentTarget;
		const animalName = textarea.value;
		this.setState({ animalName });
	}

	submit = async () => {
		if (this.state.animalName === "") {
			alert("Please specify a name for this animal")
		} else if (this.state.selectedSpecies === "") {
			alert("Please specify a species for this animal")
		} else {
			const payload = {
				name: this.state.animalName,
				species: this.state.selectedSpecies.value,
			};
			const response = await fetch('http://localhost:8080/create-animal', {
				method: 'post',
				body: JSON.stringify(payload),
				headers: {
					'content-type': 'application/json',
				}
			});
			if (await response.status === 200) {
				response.json().then(newZoo => {
					this.setState({animalName: "", selectedSpecies: ""});
					this.props.newZoo(newZoo);
				});
			}
		}
	}

	getSelectedSpecies = (selectedSpecies) => {
		this.setState({ selectedSpecies });
	}

	render() {

		return (
			<div className="createAnimal" id="Create Animal" style={{display: 'none'}}>
				<div id="AnimalName">
					Animal Name: 
					<input value={this.state.animalName} onChange={this.updateAnimalName} id="AnimalText"/>
				</div>
				<div style={{ width: 300, marginLeft: "auto", marginRight: "auto" }}>
					<Dropdown options={this.state.speciesNames} onChange={this.getSelectedSpecies} value={this.state.selectedSpecies} placeholder="Select a species" />
				</div>
				<div>
					<button onClick={this.submit} id="submit">Create Animal</button>
				</div>
			</div>
		)
	}
}

export default CreateAnimal;