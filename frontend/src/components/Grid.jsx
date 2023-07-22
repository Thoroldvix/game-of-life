import React from 'react';
import '../styles/Grid.css'

export const Grid = ({width, height, aliveCells, onClickCell, generation}) => {
    const renderGrid = () => {
        const grid = [];
        for (let y = 0; y < height; y++) {
            const row = [];
            for (let x = 0; x < width; x++) {
                const index = y * width + x;
                const isAlive = aliveCells.includes(index);
                const cellClass = isAlive ? 'alive' : 'dead';
                row.push(
                    <div
                        key={index}
                        className={`cell ${cellClass}`}
                        onClick={() => onClickCell(index)}
                    ></div>
                );
            }
            grid.push(<div key={y} className="row">{row}</div>);
        }
        return grid;
    };

    return (
            <div className="grid" style={{gridTemplateColumns: `repeat(${height}, 20px)`}}>
                {renderGrid()}
            </div>
    );
};