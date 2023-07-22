import React from "react";
import {Cell} from "./Cell.jsx";

export const Row = React.memo(({ rowIndex, width, aliveCellsSet, onClickCell }) => {
  const row = [];
  for (let x = 0; x < width; x++) {
    const index = rowIndex * width + x;
    const isAlive = aliveCellsSet.has(index);
    row.push(
      <Cell
        key={index}
        index={index}
        isAlive={isAlive}
        onClickCell={onClickCell}
      />
    );
  }
  return <div key={rowIndex} className="row">{row}</div>;
});