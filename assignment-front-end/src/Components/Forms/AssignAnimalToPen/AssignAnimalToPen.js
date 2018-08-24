import React, { Component } from 'react';
import './AssignAnimalToPen.css';
import Dropdown from 'react-dropdown';
import 'react-dropdown/style.css';

class AssignAnimalToPen extends Component {
	constructor() {
		super();
		
		this.state = {
			animalName: "",
			penId: "",
			animalNames: [],
			penIds: []
		}
	}

	updatePenId = (event) => {
		const inputField = event.currentTarget;
		const penId = inputField.value;
		this.setState({ penId });
	}

	updateAnimalName = (event) => {
		const inputField = event.currentTarget;
		const animalName = inputField.value;
		this.setState({ animalName });
	}

	componentDidMount() {
		
	}

	submit = async () => {
		if (this.state.animalName === "") {
			alert("Please select an animal");
		} else if (this.state.penId === "") {
			alert("Please select a pen")
		} else {
			const payload = {
				animalName: this.state.animalName.value,
				penId: this.state.penId.value,
			};
			const response = await fetch('http://localhost:8080/add-animal-to-pen', {
				method: 'post',
				body: JSON.stringify(payload),
				headers: {
					'content-type': 'application/json',
				}
			});
			if (await response.status === 200) {
				response.json().then(res => {
					this.setState({animalName: "", penId: ""});
					if (res.penListById) {
						this.props.newZoo(res);
					} else {
						alert(res.result);
					}
				});
			}
		}
	}

	static getDerivedStateFromProps(props, state) {
		const { penList, animalList } = props;
		let penListArray = [], animalListArray = [];

		Object.entries(penList).forEach(
			([penId]) => {
				penListArray.push(penId);
			}
		);
		Object.entries(animalList).forEach(
			([animalName]) => {
				animalListArray.push(animalName);
			}
		);

		return {
			penIds: penListArray,
			animalNames: animalListArray
		};
	}

	getSelectedAnimal = (selectedAnimal) => {
		this.setState({ animalName: selectedAnimal });
	}

	getSelectedPen = (selectedPen) => {
		this.setState({ penId: selectedPen });
	}

	render() {

		return (
			<div className="assignAnimalToPen" id="Assign Animal to Pen" style={{display: "none"}}>
				<div style={{ width: 300, marginLeft: "auto", marginRight: "auto", marginBottom: "15px" }}>
					<Dropdown options={this.state.animalNames} onChange={this.getSelectedAnimal} value={this.state.animalName} placeholder="Select an animal" />
				</div>
				<div style={{ width: 300, marginLeft: "auto", marginRight: "auto" }}>
					<Dropdown options={this.state.penIds} onChange={this.getSelectedPen} value={this.state.penId} placeholder="Select a pen" />
				</div>
				<div>
					<button onClick={this.submit} id="submit">Assign Animal to Pen</button>
				</div>
			</div>
		)
	}
}

export default AssignAnimalToPen;