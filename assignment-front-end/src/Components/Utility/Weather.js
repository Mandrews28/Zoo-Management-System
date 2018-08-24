import React from 'react';
import './Weather.css';

const Weather = ({ weather, getWeather }) => {
	return (
		<div className="WeatherDiv">
			<h4 className="WeatherShow">{weather}</h4>
			<button className="WeatherButton" onClick={getWeather}>
				<i className="fas fa-sync-alt"></i>
			</button>
		</div>
	)
}



export default Weather;