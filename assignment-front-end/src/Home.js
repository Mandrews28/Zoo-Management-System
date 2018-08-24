import React, { Component } from 'react';
import './Home.css';
import ZooVersionDropdown from './Components/ZooVersionDropdown/ZooVersionDropdown.js';
import Header from './Components/Header/Header.js';
import App from './App';

class Home extends Component {
	constructor() {
		super();

		this.state = {
			zooVersionOptions: ["Get zoo from saved file",
			"Get basic zoo with some animals/pens created (overwrite saved file)",
			"Get zoo template (overwrite saved file)"],

			selectedZooVersion: null,
			isFetchingZoo: false,
			zoo: null,
		}

		this.getSelectedZooVersion = this.getSelectedZooVersion.bind(this);
	}

	getZoo = async () => {
		this.setState({ isFetchingZoo: true, zoo: null });
		const payload = {
			selectedZooVersion: this.state.selectedZooVersion
		};
		const response = await fetch('http://localhost:8080/zoo', {
			method: 'post',
			body: JSON.stringify(payload),
			headers: {
				'content-type': 'application/json',
			}
		}).catch((e) => {
			console.log(e);
		});
		if (await response.status === 200) {
			response.json().then(zoo => {
				console.log(zoo);
				if (zoo.penListById) {
					this.setState({ isFetchingZoo: false, zoo });
					document.getElementById("Select Zoo Version").style.display = "none";
				} else {
					alert(zoo.result);
					this.setState({ isFetchingZoo: false, selectedZooVersion: null });
				}
			});
		}
	}

	getSelectedZooVersion = (option) => {
		const selectedZooVersion = this.state.zooVersionOptions.find(filteredOption => {
			return filteredOption === option.value;
		});
		this.setState({ selectedZooVersion }, () => {
			this.getZoo();
			console.log(selectedZooVersion);
		});
	}

	render() {
		return (
			<div className="Home">
				<Header/>
				<div id="Select Zoo Version">
					<h2> What version of the zoo would you like to load? </h2>
					<ZooVersionDropdown options={this.state.zooVersionOptions} selectedOption={this.state.selectedZooVersion} onSelectedOption={this.getSelectedZooVersion}/>
				</div>
				{ this.state.zoo &&
					<App zoo={this.state.zoo}/>
				}
			</div>
		);
	}
}

export default Home;
