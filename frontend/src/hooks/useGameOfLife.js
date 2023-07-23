import {useCallback, useEffect, useState} from "react";
import {connect, disconnect, getNextGeneration, getRandomCells} from "../api/universeApi.js";

export const useGameOfLife = ({initialWidth, initialHeight, initialSpeed}) => {
    const [width, setWidth] = useState(initialWidth);
    const [height, setHeight] = useState(initialHeight);
    const [aliveCells, setAliveCells] = useState([]);
    const [running, setRunning] = useState(false);
    const [speed, setSpeed] = useState(initialSpeed);
    const [generation, setGeneration] = useState(0);


    useEffect(() => {
        connect();
    }, []);


    const fetchRandomCellIndices = async () => {
        const randomAliveCellIndices = await getRandomCells(width, height);
        setAliveCells(randomAliveCellIndices);
    };

    const fetchNextGeneration = useCallback(async () => {
        const cells = await getNextGeneration(width, height, aliveCells);
        setAliveCells(cells);
    }, [width, height, aliveCells, setGeneration]);

    const handleCellClick = (cellIndex) => {
        setAliveCells((prevAliveCells) => {
            if (prevAliveCells.includes(cellIndex)) {
                return prevAliveCells.filter((cell) => cell !== cellIndex);
            } else {
                return [...prevAliveCells, cellIndex];
            }
        });
    };

    const handleStartButtonClick = () => {
        if (aliveCells.length === 0) {
            return;
        }
        setRunning(true);
    };

    const handleStopButtonClick = () => {
        setRunning(false);
    };

    const resetGrid = () => {
        setAliveCells([]);
        setGeneration(0);
        setRunning(false);
    }

    const handleResetButtonClick = () => {
        resetGrid();
    }

    const handleRandomButtonClick = () => {
        resetGrid();
        fetchRandomCellIndices();
    };

    const handleNextGenerationButtonClick = () => {
        if (aliveCells.length === 0 || running) {
            return;
        }
        fetchNextGeneration();
    }

    const handleSpeedChange = (event) => {
        setSpeed(Number(event.target.value))
    }


    return {
        width, height, aliveCells, running, speed, generation,
        handleCellClick, handleStartButtonClick, handleStopButtonClick,
        handleResetButtonClick, handleRandomButtonClick, handleNextGenerationButtonClick,
        handleSpeedChange, resetGrid, fetchRandomCellIndices, fetchNextGeneration

    };
};

export const useLifecycle = (running, speed, fetchNextGeneration, isEmpty, resetGrid) => {
    useEffect(() => {
        if (running) {
            if (isEmpty === 0) {
                resetGrid();
                return;
            }
            const interval = setInterval(() => {
                fetchNextGeneration();
            }, 1000 / speed);
            return () => clearInterval(interval);
        }
    }, [running, speed, fetchNextGeneration]);
};