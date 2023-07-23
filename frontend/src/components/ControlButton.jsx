import React from 'react';
import '../styles/ControlButton.css'

export const ControlButton = ({onClick, label}) => {
    return (
        <button type={"button"} className="control-button" onClick={onClick}>{label} </button>
    );
};