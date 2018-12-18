import React, { Component } from 'react';
import fetch from 'isomorphic-fetch'
import './App.css';
import Table from './Components/Table/Table.js';
import Tabs from './Components/Tabs/Tabs';
import ZooManager from './Components/ZooManager/ZooManager.js';
import Weather from './Components/Utility/Weather.js';

class App extends Component {
	constructor() {
		super();

		this.state = {
			zoo: null,
			isFetchingWeather: false,
			weather: null,
		}

		var tabContent = document.createElement("tabContent");
		tabContent.setAttribute("id", "TabContent");
		tabContent.appendChild(document.createTextNode(""));
		document.body.appendChild(tabContent);

		this.getWeather = this.getWeather.bind(this);
		this.replaceZoo = this.replaceZoo.bind(this);
	}

	componentDidMount() {
		this.setState({ zoo: this.props.zoo });
		this.getWeather();
	}

	getWeather() {
		if (!this.state.isFetchingWeather) {
			this.setState({ isFetchingWeather: true });

			fetch('http://localhost:8080/weather').then((response) => {
				if (response.status === 200) {
					response.text().then(weather => {
						this.setState({ isFetchingWeather: false, weather });
					});
				}
			}).catch((e) => {
				console.log(e);
			});
		}
	}

	getListOfPensWithoutAZookeeper = () => {
		var penListWithNoZookeeper = [];
		if (this.state.zoo) {
			Object.entries(this.state.zoo.penListById).forEach(
				([penId, penAttributes]) => {
					if (penAttributes.zookeeper === null) {
						penListWithNoZookeeper.push(penId);
					}
				}
			);
		}
		var stringList = this.convertListToString(penListWithNoZookeeper);
		return this.noneIfStringIsBlank(stringList);
	}

	getListOfAnimalsNotAssignedAPen = () => {
		var animalListWithNoPen = [];
		if (this.state.zoo) {
			Object.entries(this.state.zoo.animalListByName).forEach(
				([animalName, animalAttributes]) => {
					if (animalAttributes.currentPen === null) {
						animalListWithNoPen.push(animalName);
					}
				}
			);
		}
		var stringList = this.convertListToString(animalListWithNoPen);
		
		return this.noneIfStringIsBlank(stringList);
	}

	getListOfCompatibleSpecies = () => {
		var stringList = this.convertListToString(this.state.zoo.compatibleSpecies);
		return this.noneIfStringIsBlank(stringList);
	}

	convertListToString = (list) => {
		var string = "";
		for (var i = 0; i < list.length; i++) {
			string += list[i];
			if (i + 1 < list.length) {
				string += ", ";
			}
		}
		return string;
	}

	noneIfStringIsBlank = (string) =>{
		return (string === "") ? "None" : string;
	}

	replaceZoo = (newZoo) => {
		this.setState({ zoo: newZoo }, () => console.log(this.state));
		console.log(newZoo);
	}

	render() {
		const penListWithNoZookeeper = this.getListOfPensWithoutAZookeeper();
		const animalListWithNoPen = this.getListOfAnimalsNotAssignedAPen();

		const { zoo, weather } = this.state;
		return (
			<div className="App">
				{ weather && <Weather weather={weather} getWeather={this.getWeather}/> }
				{ zoo && 
					<div>
						<p style={{borderTop: "3px solid #000"}}/>
						<h4> Information </h4>
						<p style={{ color: "red" }}> List of Pens not assigned a Zookeeper: {penListWithNoZookeeper} </p>
						<p style={{ color: "orange" }}> List of Animals not assigned a Pen: {animalListWithNoPen} </p>
						<p style={{ color: "green" }}> List of compatible species: {this.getListOfCompatibleSpecies()} </p>
						<p style={{borderBottom: "3px solid #000"}}/>
						<Tabs>
							<div label="Zoo Manager">
								<ZooManager zoo={zoo} newZoo={this.replaceZoo}/>
							</div>
							<div label="Animal List">
								<Table list={zoo.animalListByName} value="Animal List"/>
							</div>
							<div label="Species List">
								<Table list={zoo.speciesListByName} value="Species List"/>
							</div>
							<div label="Pen List">
								<Table list={zoo.penListById} value="Pen List"/>
							</div>
							<div label="Zookeeper List">
								<Table list={zoo.zookeeperListByName} value="Zookeeper List"/>
							</div>
						</Tabs>
					</div>
				}
			</div>
		);
	}
}

export default App;
