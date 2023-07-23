import {ControlButton} from "./ControlButton.jsx";
import React from "react";
import '../styles/Controls.css'

export const Controls = ({
                             handleResetButtonClick,
                             handleRandomButtonClick,
                             handleNextGenerationButtonClick,
                             running,
                             handleStopButtonClick,
                             handleStartButtonClick,
                             speed,
                             handleSpeedChange
                         }) => {
    return (
        <div className="controls">
            <ControlButton onClick={handleResetButtonClick} label={"Reset"}>Reset</ControlButton>
            <ControlButton onClick={handleRandomButtonClick} label={"Random"}/>
            <ControlButton onClick={handleNextGenerationButtonClick} label={"Next generation"}/>
            {running ? (
                <ControlButton onClick={handleStopButtonClick} label="Stop"/>
            ) : (
                <ControlButton onClick={handleStartButtonClick} label="Start"/>
            )}
            <label htmlFor="slider">Speed</label>
            <input type="range"
                   id="slider"
                   min="1"
                   max="10"
                   className="slider"
                   value={speed}
                   onChange={handleSpeedChange}
            ></input>
        </div>
    );
};