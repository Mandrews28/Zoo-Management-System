import React, { Component } from 'react';
import './Table.css';

class Table extends Component {
	getColumnHeaders = () => {
		var col;
		switch (this.props.value) {
			case "Animal List":
				col = ["Name", "Species", "Pen Id", "Pen Type"];
				break;
			case "Species List":
				col = ["Name", "Animal Type", "Pen Types", "Air Req (m³)", "Land Req (m²)", "Water Req (m³)", "Num of Animals"];
				break;
			case "Pen List":
				col = ["Id", "Pen Type", "Species List", "Zookeeper", "Num of Animals", "Remaining Area (m²)" ,"Remaining Volume (m³)", "Temperature (°C)"];
				break;
			case "Zookeeper List":
				col = ["Name", "Pen Types", "Num of Pens Attending"];
				break;
			default:
				col = [];
		}
		return col;
	}

	getElementValues = (name, value) => {
		var values;
		switch (this.props.value) {
			case "Animal List":
				values = [name, value.species.name];
				break;
			case "Species List":
				if (!value.airRequired) value.airRequired = "-";
				if (!value.landRequired) value.landRequired = "-";
				if (!value.waterRequired) value.waterRequired = "-";
				values = [name, value.animalType, value.suitablePenTypes, value.airRequired, value.landRequired, value.waterRequired, value.currentNumOfAnimals];
				break;
			case "Pen List":
				if (!value.remainingArea) value.remainingArea = "-";
				if (!value.remainingVolume) value.remainingVolume = "-";
				const listOfSpecies = (value.listOfSpeciesNames.length !== 0) ? value.listOfSpeciesNames : "-";
				const zookeeper = (value.zookeeper) ? value.zookeeper.name : "-";
				values = [name, value.penType, listOfSpecies, zookeeper, value.numOfAnimals, value.remainingArea ,value.remainingVolume, value.temperature];
				break;
			case "Zookeeper List":
				values = [name, value.penTypes, value.numPensAttending];
				break;
			default:
				values = [];
		}
		return values;
	}

	getDependentColumnValues = (elementValue) => {
		var values;
		switch (this.props.value) {
			case "Animal List":
				if (elementValue.currentPen) {
					values = [elementValue.currentPen.penId, elementValue.currentPen.penType];
				} else {
					values = [null, null];
				}
				break;
			default:
				values = [];
		}
		return values;
	}


	createTableFromJSON = () => {
		var list = this.props.list;
		var col = this.getColumnHeaders();
		

		// CREATE DYNAMIC TABLE.
		var table = document.createElement("table");

		// CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.
		var tr = table.insertRow(-1);		// TABLE ROW.
		for (var j = 0; j < col.length; j++) {
			var th = tr.insertCell();		// TABLE HEADER.
			th.appendChild(document.createTextNode(col[j]));
		}

		// ADD JSON DATA TO THE TABLE AS ROWS.
		Object.entries(list).forEach(
			([elementName, elementValue]) => {
				var values = this.getElementValues(elementName, elementValue);
				tr = table.insertRow(-1);
				for (var value of values) {
					tr.insertCell(-1).appendChild(document.createTextNode(value));
				}
				
				var depValues = this.getDependentColumnValues(elementValue);
				for (var depValue of depValues) {
					tr.insertCell(-1).appendChild(document.createTextNode(depValue));
				}
			});
		var tabContent = document.getElementById("TabContent");
		tabContent.replaceChild(table, tabContent.childNodes[0]);
	}

	render() {
		this.createTableFromJSON();
		return (
			<div/>
		);
	}
}

export default Table;