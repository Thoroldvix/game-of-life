import React, {useMemo} from 'react';
import '../styles/Grid.css'
import {Row} from "./Row.jsx";

export const Grid = React.memo(({width, height, aliveCells, onClickCell}) => {
    const aliveCellsSet = useMemo(() => new Set(aliveCells), [aliveCells]);
    const renderGrid = useMemo(() => {
        const grid = [];
        for (let y = 0; y < height; y++) {
            grid.push(
                <Row
                    key={y}
                    rowIndex={y}
                    width={width}
                    aliveCellsSet={aliveCellsSet}
                    onClickCell={onClickCell}
                />
            );
        }
        return grid;
    }, [width, height, aliveCellsSet, onClickCell])

    return (
        <div className="grid" style={{gridTemplateColumns: `repeat(${height}, 20px)`}}>
            {renderGrid}
        </div>
    );
});