import React from "react";
import {Grid} from "./Grid.jsx";
import '../styles/App.css'
import {useGameOfLife, useLifecycle} from "../hooks/useGameOfLife.js";
import {Controls} from "./Controls.jsx";

export const App = () => {
    const {
        width, height, aliveCells, running, speed, generation,
        handleCellClick, handleStartButtonClick, handleStopButtonClick,
        handleResetButtonClick, handleRandomButtonClick, handleNextGenerationButtonClick,
        handleSpeedChange, resetGrid, fetchNextGeneration
    } =
        useGameOfLife({
            initialWidth: 40,
            initialHeight: 60,
            initialSpeed: 5
        });

    useLifecycle(running, speed, fetchNextGeneration, aliveCells.length, resetGrid);


    return (
        <div className="app-container">
            <div className="grid-container">
                <Grid width={width} height={height} aliveCells={aliveCells}
                      onClickCell={handleCellClick}/>
            </div>
                <div className="controls-container">
                    <Controls
                        handleResetButtonClick={handleResetButtonClick}
                        handleRandomButtonClick={handleRandomButtonClick}
                        handleNextGenerationButtonClick={handleNextGenerationButtonClick}
                        running={running}
                        handleStopButtonClick={handleStopButtonClick}
                        handleStartButtonClick={handleStartButtonClick}
                        speed={speed}
                        generation={generation}
                        handleSpeedChange={handleSpeedChange}
                    />
                </div>
        </div>
    );
};


