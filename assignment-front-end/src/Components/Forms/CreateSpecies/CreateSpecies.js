import React, { Component } from 'react';
import './CreateSpecies.css';
import Dropdown from 'react-dropdown';
import 'react-dropdown/style.css';

class CreateSpecies extends Component {
	constructor() {
		super();
		
		this.state = {
			speciesName: "",
			animalType: "",
			animalTypes: ["Air", "Amphibian", "Land", "Water"],
			airRequirement: 0,
			landRequirement: 0,
			waterRequirement: 0,
			isPettable: "",
			pettableOptions: ["True", "False"]
		}
	}
	
	updateSpeciesName = (event) => {
		const inputField = event.currentTarget;
		const speciesName = inputField.value;
		this.setState({ speciesName });
	}

	checkValidInput = (requirement) => {
		if (requirement < 0) {
			alert("You cannot enter a negative requirement!");
			return 0;
		} else {
			return requirement;
		}
	}

	updateAirRequirement = (event) => {
		const inputField = event.currentTarget;
		const airRequirement = this.checkValidInput(inputField.value);
		this.setState({ airRequirement });
	}

	updateLandRequirement = (event) => {
		const inputField = event.currentTarget;
		const landRequirement = this.checkValidInput(inputField.value);
		this.setState({ landRequirement });
	}

	updateWaterRequirement = (event) => {
		const inputField = event.currentTarget;
		const waterRequirement = this.checkValidInput(inputField.value);
		this.setState({ waterRequirement });
	}

	updatePettableBoolean = (event) => {
		const isPettable = event.value;
		this.setState({ isPettable });
	}
	
	submit = async () => {
		if (this.state.speciesName === "") {
			alert("Please specify a name for this species")
		} else if (this.state.animalType === "") {
			alert("Please specify an animal type for this species")
		} else {
			const isPettableBool = (this.state.isPettable === "True") ? true : false;
			const payload = {
				name: this.state.speciesName,
				animalType: this.state.animalType.value,
				airRequirement: this.state.airRequirement,
				landRequirement: this.state.landRequirement,
				waterRequirement: this.state.waterRequirement,
				isPettable: isPettableBool,
			};
			const response = await fetch('http://localhost:8080/create-species', {
				method: 'post',
				body: JSON.stringify(payload),
				headers: {
					'content-type': 'application/json',
				}
			});
			if (await response.status === 200) {
				response.json().then(newZoo => {
					document.getElementById("AirRequirement").style.display = "none";
					document.getElementById("LandRequirement").style.display = "none";
					document.getElementById("WaterRequirement").style.display = "none";
					document.getElementById("PettableBoolean").style.display = "none";
					this.setState({speciesName: "", animalType: "", airRequirement: 0, landRequirement: 0, waterRequirement: 0, isPettable: "False"});
					this.props.newZoo(newZoo);
				});
			}
		}
	}

	getSelectedAnimalType = (animalType) => {
		if (this.state.animalType !== "") {
			document.getElementById("AirRequirement").style.display = "none";
			document.getElementById("LandRequirement").style.display = "none";
			document.getElementById("WaterRequirement").style.display = "none";
			document.getElementById("PettableBoolean").style.display = "none";
		}
		this.setState({ animalType }, () => {
			if (animalType.value === "Air") {
				document.getElementById("AirRequirement").style.display = "";
				this.setState({ landRequirement: 0, waterRequirement: 0, isPettable: "" });
			} else if (animalType.value === "Amphibian") {
				document.getElementById("LandRequirement").style.display = "";
				document.getElementById("WaterRequirement").style.display = "";
				this.setState({ airRequirement: 0, isPettable: "" });
			} else if (animalType.value === "Land") {
				document.getElementById("LandRequirement").style.display = "";
				document.getElementById("PettableBoolean").style.display = "";
				this.setState({ airRequirement: 0, waterRequirement: 0 });
			} else if (animalType.value === "Water") {
				document.getElementById("WaterRequirement").style.display = "";
				this.setState({ airRequirement: 0, landRequirement: 0, isPettable: "" });
			}
		});
	}

	render() {

		return (
			<div className="createSpecies" id="Create Species" style={{display: "none"}}>
				<div id="SpeciesName">
					Species Name: 
					<input value={this.state.speciesName} onChange={this.updateSpeciesName} id="inputField"/>
				</div>
				<div style={{ width: 300, marginLeft: "auto", marginRight: "auto" }}>
					<Dropdown options={this.state.animalTypes} onChange={this.getSelectedAnimalType} value={this.state.animalType} placeholder="Select an animal type" />
				</div>
				<div id="AirRequirement" style={{display: "none"}}>
					Air Requirement (m³): 
					<input type="number" min="0" value={this.state.airRequirement} onChange={this.updateAirRequirement} id="inputField"/>
				</div>
				<div id="LandRequirement" style={{display: "none"}}>
					Land Requirement (m²): 
					<input type="number" min="0" value={this.state.landRequirement} onChange={this.updateLandRequirement} id="inputField"/>
				</div>
				<div id="WaterRequirement" style={{display: "none"}}>
					Water Requirement (m³): 
					<input type="number" min="0" value={this.state.waterRequirement} onChange={this.updateWaterRequirement} id="inputField"/>
				</div>
				<div id="PettableBoolean" style={{ width: 300, marginLeft: "auto", marginRight: "auto", display: "none" }}>
					<Dropdown options={this.state.pettableOptions} onChange={this.updatePettableBoolean} value={this.state.isPettable} placeholder="Can you pet the animal?" />
				</div>
				<div>
					<button onClick={this.submit} id="submit">Create Species</button>
				</div>
			</div>
		)
	}
}

export default CreateSpecies;