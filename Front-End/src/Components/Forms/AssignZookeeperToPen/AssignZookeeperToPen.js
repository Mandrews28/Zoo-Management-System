import React, { Component } from 'react';
import './AssignZookeeperToPen.css';
import Dropdown from 'react-dropdown';
import 'react-dropdown/style.css';

class AssignZookeeperToPen extends Component {
	constructor() {
		super();
		
		this.state = {
			zookeeperName: "",
			penId: "",
			zookeeperNames: [],
			penIds: []
		}
	}

	updatePenId = (event) => {
		const inputField = event.currentTarget;
		const penId = inputField.value;
		this.setState({ penId });
	}

	updateZookeeperName = (event) => {
		const inputField = event.currentTarget;
		const zookeeperName = inputField.value;
		this.setState({ zookeeperName });
	}

	componentDidMount() {
		const { penList, zookeeperList } = this.props;
		Object.entries(penList).forEach(
			([penId]) => {
				this.state.penIds.push(penId);
			}
		);
		Object.entries(zookeeperList).forEach(
			([zookeeperName]) => {
				this.state.zookeeperNames.push(zookeeperName);
			}
		);
	}

	submit = async () => {
		if (this.state.zookeeperName === "") {
			alert("Please select a zookeeper");
		} else if (this.state.penId === "") {
			alert("Please select a pen")
		} else {
			const payload = {
				zookeeperName: this.state.zookeeperName.value,
				penId: this.state.penId.value,
			};
			const response = await fetch('http://localhost:8080/add-zookeeper-to-pen', {
				method: 'post',
				body: JSON.stringify(payload),
				headers: {
					'content-type': 'application/json',
				}
			});
			if (await response.status === 200) {
				response.json().then(res => {
					this.setState({zookeeperName: "", penId: ""});
					if (res.penListById) {
						this.props.newZoo(res);
					} else {
						alert(res.result);
					}
				});
			}
		}
	}

	getSelectedZookeeper = (selectedZookeeper) => {
		this.setState({ zookeeperName: selectedZookeeper });
	}

	getSelectedPen = (selectedPen) => {
		this.setState({ penId: selectedPen });
	}

	render() {

		return (
			<div className="assignZookeeperToPen" id="Assign Zookeeper to Pen" style={{display: "none"}}>
				<div style={{ width: 300, marginLeft: "auto", marginRight: "auto", marginBottom: "15px" }}>
					<Dropdown options={this.state.zookeeperNames} onChange={this.getSelectedZookeeper} value={this.state.zookeeperName} placeholder="Select a zookeeper" />
				</div>
				<div style={{ width: 300, marginLeft: "auto", marginRight: "auto" }}>
					<Dropdown options={this.state.penIds} onChange={this.getSelectedPen} value={this.state.penId} placeholder="Select a pen" />
				</div>
				<div>
					<button onClick={this.submit} id="submit">Assign Zookeeper to Pen</button>
				</div>
			</div>
		)
	}
}

export default AssignZookeeperToPen;