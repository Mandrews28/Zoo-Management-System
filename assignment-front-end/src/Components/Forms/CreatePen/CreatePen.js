import React, { Component } from 'react';
import './CreatePen.css';
import Dropdown from 'react-dropdown';
import 'react-dropdown/style.css';

class CreatePen extends Component {
	constructor() {
		super();
		
		this.state = {
			penId: "",
			penType: "",
			penTypes: ["Aquarium", "Aviary", "Dry", "Part Water Part Dry", "Petting"],
			temperature: 0,
			length: 0,
			width: 0,
			height: 0,
			waterVolume: 0,
		}
	}
	
	updatePenId = (event) => {
		const inputField = event.currentTarget;
		const penId = inputField.value;
		this.setState({ penId });
	}

	updateTemperature = (event) => {
		const inputField = event.currentTarget;
		const temperature = inputField.value;
		if (temperature < -273.15) {
			alert("Temperature cannot be less than absolute zero!");
			this.setState({ temperature: 0 });
		} else {
			this.setState({ temperature });
		}
	}
	
	checkValidInput = (dimension) => {
		if (dimension < 0) {
			alert("You cannot enter a negative size!");
			return 0;
		} else {
			return dimension;
		}
	}

	updateLength = (event) => {
		const inputField = event.currentTarget;
		const length = this.checkValidInput(inputField.value);
		this.setState({ length });
	}

	updateWidth = (event) => {
		const inputField = event.currentTarget;
		const width = this.checkValidInput(inputField.value);
		this.setState({ width });
	}

	updateHeight = (event) => {
		const inputField = event.currentTarget;
		const height = this.checkValidInput(inputField.value);
		this.setState({ height });
	}

	updateWaterVolume = (event) => {
		const inputField = event.currentTarget;
		const waterVolume = this.checkValidInput(inputField.value);
		this.setState({ waterVolume });
	}
	
	submit = async () => {
		if (this.state.penId === "") {
			alert("Please specify an ID for this pen")
		} else if (this.state.penType === "") {
			alert("Please specify a pen type")
		} else {
			const payload = {
				penId: this.state.penId,
				penType: this.state.penType.value,
				temperature: this.state.temperature,
				length: this.state.length,
				width: this.state.width,
				height: this.state.height,
				waterVolume: this.state.waterVolume,
			};
			const response = await fetch('http://localhost:8080/create-pen', {
				method: 'post',
				body: JSON.stringify(payload),
				headers: {
					'content-type': 'application/json',
				}
			});
			if (await response.status === 200) {
				response.json().then(newZoo => {
					document.getElementById("Height").style.display = "none";
					document.getElementById("WaterVolume").style.display = "none";
					this.setState({penId: "", penType: "", temperature: 0, length: 0, width: 0, height: 0, waterVolume: 0});
					this.props.newZoo(newZoo);
				});
			}
		}
	}

	getSelectedPenType = (penType) => {
		if (this.state.penType !== "") {
			document.getElementById("Height").style.display = "none";
			document.getElementById("WaterVolume").style.display = "none";
		}
		this.setState({ penType }, () => {
			if (penType.value === "Aquarium" || penType.value === "Aviary") {
				document.getElementById("Height").style.display = "";
				this.setState({ waterVolume: 0 });
			} else if (penType.value === "Dry" || penType.value === "Petting") {
				this.setState({ height: 0, waterVolume: 0 });
			} else if (penType.value === "Part Water Part Dry") {
				document.getElementById("WaterVolume").style.display = "";
				this.setState({ height: 0 });
			} 
		});
	}

	render() {

		return (
			<div className="createPen" id="Create Pen" style={{display: "none"}}>
				<div id="PenID">
					Pen ID: 
					<input value={this.state.penId} onChange={this.updatePenId} id="inputField"/>
				</div>
				<div style={{ width: 300, marginLeft: "auto", marginRight: "auto" }}>
					<Dropdown options={this.state.penTypes} onChange={this.getSelectedPenType} value={this.state.penType} placeholder="Select a pen type" />
				</div>
				<div id="Temperature">
					Temperature (°C): 
					<input type="number" min="0" value={this.state.temperature} onChange={this.updateTemperature} id="inputField"/>
				</div>
				<div id="Length">
					Length (m): 
					<input type="number" min="0" value={this.state.length} onChange={this.updateLength} id="inputField"/>
				</div>
				<div id="Width">
					Width (m): 
					<input type="number" min="0" value={this.state.width} onChange={this.updateWidth} id="inputField"/>
				</div>
				<div id="Height" style={{display: "none"}}>
					Height (m): 
					<input type="number" min="0" value={this.state.height} onChange={this.updateHeight} id="inputField"/>
				</div>
				<div id="WaterVolume" style={{display: "none"}}>
					WaterVolume (m³): 
					<input type="number" min="0" value={this.state.waterVolume} onChange={this.updateWaterVolume} id="inputField"/>
				</div>
				<div>
					<button onClick={this.submit} id="submit">Create Pen</button>
				</div>
			</div>
		)
	}
}

export default CreatePen;