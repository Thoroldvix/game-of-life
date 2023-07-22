import React from "react";
import '../styles/Cell.css'

export const Cell = React.memo(({ index, isAlive, onClickCell }) => {
  const cellClass = isAlive ? 'alive' : 'dead';
  return (
    <div
      key={index}
      className={`cell ${cellClass}`}
      onClick={() => onClickCell(index)}
    ></div>
  );
});