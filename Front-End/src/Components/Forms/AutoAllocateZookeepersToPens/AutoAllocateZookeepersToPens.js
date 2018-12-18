import React, { Component } from 'react';

class AutoAllocateZookeepersToPens extends Component {
	constructor() {
		super();
		
		this.state = {
			zookeeperList: [],
			penList: []
		}
	}
	
	componentDidMount() {
		const { zookeeperList, penList } = this.props;
		this.setState({ zookeeperList, penList });
	}

	submit = async () => {
		fetch('http://localhost:8080/auto-allocate-zookeepers').then((res) => {
			if (res.status === 200) {
				res.json().then(response => {
					if (response.penListById) {
						const numPensWithoutAZookeeper = this.getNumPensWithoutAZookeeper(response);
						if (numPensWithoutAZookeeper !== 0) {
							alert("Some pens (" + numPensWithoutAZookeeper + ") could not be assigned a zookeeper because there was no zookeeper with a compatible pen type")
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

	getNumPensWithoutAZookeeper = (zoo) => {
		var numPensWithoutAZookeeper = 0;
		Object.entries(zoo.penListById).forEach(
			([penName, penAttributes]) => {
				if (penAttributes.zookeeper === null) {
					numPensWithoutAZookeeper++;
				}
			}
		);
		return numPensWithoutAZookeeper;
	}

	render() {

		return (
			<div className="autoAllocateZookeepersToPens" id="Auto Allocate Zookeepers to Pens" style={{display: 'none'}}>
				<div>
					<button onClick={this.submit} id="submit" style={{ fontSize: "14px" }} >Auto Allocate Zookeepers To Pens</button>
				</div>
			</div>
		)
	}
}

export default AutoAllocateZookeepersToPens;