import React, { Component } from 'react';

class AutoAllocateAnimalsToPens extends Component {
	constructor() {
		super();
		
		this.state = {
			animalList: [],
			penList: []
		}
	}
	
	componentDidMount() {
		const { animalList, penList } = this.props;
		this.setState({ animalList, penList });
	}

	submit = async () => {
		fetch('http://localhost:8080/auto-allocate-animals').then((res) => {
			if (res.status === 200) {
				res.json().then(response => {
					if (response.penListById) {
						const numPensWithoutAnAnimal = this.getNumPensWithoutAnAnimal(response);
						if (numPensWithoutAnAnimal !== 0) {
							alert("Some animals (" + numPensWithoutAnAnimal + ") could not be assigned a pen")
						}
						this.props.newZoo(response);
					} else {
						alert(response.result);
					}
				});
			}
		}).catch((e) => {
			console.log(e);
		});
		
	}

	getNumPensWithoutAnAnimal = (zoo) => {
		var numPensWithoutAnAnimal = 0;
		Object.entries(zoo.animalListByName).forEach(
			([animalName, animalAttributes]) => {
				if (animalAttributes.currentPen === null) {
					numPensWithoutAnAnimal++;
				}
			}
		);
		return numPensWithoutAnAnimal;
	}

	render() {

		return (
			<div className="autoAllocateAnimalsToPens" id="Auto Allocate Animals to Pens" style={{display: 'none'}}>
				<div>
					<button onClick={this.submit} id="submit" style={{ fontSize: "14px" }} >Auto Allocate Animals to Pens</button>
				</div>
			</div>
		)
	}
}

export default AutoAllocateAnimalsToPens;