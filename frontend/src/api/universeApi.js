import axios from "axios";


export const getRandomCells = async (width, height) => {
    try {
        return (await axios.post(
            `${import.meta.env.VITE_API_BASE_URL}/api/v1/universe/random`,
            {width, height}
        )).data;
    } catch (e) {
        console.log(e);
    }
}

export const getNextGeneration = async (width, height, cells) => {
    const dimensions = {width, height};
    try {
        return (await axios.post(
            `${import.meta.env.VITE_API_BASE_URL}/api/v1/universe/next`,
            {dimensions, cells})).data;
    } catch (e) {
        console.log(e);
    }
}
