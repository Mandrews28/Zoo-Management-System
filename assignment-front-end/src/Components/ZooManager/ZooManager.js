import React, { Component } from 'react';
import ZooManagerDropdown from '../ZooManagerDropdown/ZooManagerDropdown.js';
import CreateAnimal from '../Forms/CreateAnimal/CreateAnimal.js';
import CreateSpecies from '../Forms/CreateSpecies/CreateSpecies.js';
import CreatePen from '../Forms/CreatePen/CreatePen.js';
import AssignAnimalToPen from '../Forms/AssignAnimalToPen/AssignAnimalToPen.js';
import AssignZookeeperToPen from '../Forms/AssignZookeeperToPen/AssignZookeeperToPen.js';
import AddCompatibleSpecies from '../Forms/AddCompatibleSpecies/AddCompatibleSpecies.js';
import AutoAllocateAnimalsToPens from '../Forms/AutoAllocateAnimalsToPens/AutoAllocateAnimalsToPens.js';
import AutoAllocateZookeepersToPens from '../Forms/AutoAllocateZookeepersToPens/AutoAllocateZookeepersToPens.js';

class ZooManager extends Component {
	constructor() {
		super();

		var tabContent = document.getElementById("TabContent");
		tabContent.replaceChild(document.createTextNode(""), tabContent.childNodes[0]);

		this.state = {
			options: ["Create Animal",
			"Create Species",
			"Create Pen",
			"Assign Animal to Pen",
			"Assign Zookeeper to Pen",
			"Add Compatible Species",
			"Auto Allocate Animals to Pens",
			"Auto Allocate Zookeepers to Pens"],
			
			selectedOption: "",
			animalName: "",
		}

		this.getSelectedOption = this.getSelectedOption.bind(this);
	}


	updateName = (event) => {
		const textarea = event.currentTarget;
		const newAnimalName = textarea.value;
		this.setState({animalName: newAnimalName});
	}

	getSelectedOption(option) {
		if (this.state.selectedOption !== "") {
			document.getElementById(this.state.selectedOption).style.display = "none";
		}

		const selectedOption = this.state.options.find(filteredOption => {
			return filteredOption === option.value;
		});

		this.setState({ selectedOption }, () => {
			document.getElementById(selectedOption).style.display = "";
		});
	}

	render() {
		const { zoo } = this.props;

		return (
			<div className="ZooManagerOption">
				<ZooManagerDropdown options={this.state.options} selectedOption={this.state.selectedOption} onSelectedOption={this.getSelectedOption}/>
				<CreateAnimal speciesList={zoo.speciesListByName} newZoo={this.props.newZoo}/>
				<CreateSpecies newZoo={this.props.newZoo}/>
				<CreatePen newZoo={this.props.newZoo}/>
				<AssignAnimalToPen animalList={zoo.animalListByName} penList={zoo.penListById} newZoo={this.props.newZoo}/>
				<AssignZookeeperToPen zookeeperList={zoo.zookeeperListByName} penList={zoo.penListById} newZoo={this.props.newZoo}/>
				<AddCompatibleSpecies speciesList={zoo.speciesListByName} newZoo={this.props.newZoo}/>
				<AutoAllocateAnimalsToPens animalList={zoo.animalListByName} penList={zoo.penListById} newZoo={this.props.newZoo}/>
				<AutoAllocateZookeepersToPens zookeeperList={zoo.zookeeperListByName} penList={zoo.penListById} newZoo={this.props.newZoo}/>
			</div>
		)
	}
}

export default ZooManager;