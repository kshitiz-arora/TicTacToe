// HTML Elements
const statusDiv = document.querySelector('.status');
const resetDiv = document.querySelector('.reset');
const cellDivs = document.querySelectorAll('.game-cell');
const typeDiv = document.querySelector('.type');
const changeDiv = document.querySelector('.change-type');

// game variables
let gameIsLive = true;
let xIsNext = true;
let boxesFilled = 0;
let is2P = true; // if true, 1v1 else player-vs-computer
let isWaiting = false; // wait time for computer to make move

// game functions
const getValue = (symbol) => {
    val = 0;
    if (symbol === 'x') {
        val = 1;
    }
    else if (symbol === 'o') {
        val = 10;
    }
    return val;
}

const getRowScore = (rowNumber) => {
    return (getValue(cellDivs[3 * rowNumber + 0].classList[2]) +
        getValue(cellDivs[3 * rowNumber + 1].classList[2]) +
        getValue(cellDivs[3 * rowNumber + 2].classList[2]))
}

const getColumnScore = (columnNumber) => {
    return (getValue(cellDivs[columnNumber + 0].classList[2]) +
        getValue(cellDivs[columnNumber + 3].classList[2]) +
        getValue(cellDivs[columnNumber + 6].classList[2]))
}

const getDiagonal1Score = () => {
    return (getValue(cellDivs[0].classList[2]) +
        getValue(cellDivs[4].classList[2]) +
        getValue(cellDivs[8].classList[2]))
}

const getDiagonal2Score = () => {
    return (getValue(cellDivs[2].classList[2]) +
        getValue(cellDivs[4].classList[2]) +
        getValue(cellDivs[6].classList[2]))
}

// check if blocking opponent is required or winning chance exists
const winOrBlockMove = () => {

    for (let type = 0; type < 2; type++) {

        let check = (type === 0) ? 20 : 2;

        for (let i = 0; i < 3; i++) {
            if (getRowScore(i) === check) {
                for (let j = 3 * i; j < 3 * (i + 1); j++) {
                    if (getValue(cellDivs[j].classList[2]) === 0) {
                        return j;
                    }
                }
            }
        }

        for (let i = 0; i < 3; i++) {
            if (getColumnScore(i) === check) {
                for (let j = i; j < 9; j += 3) {
                    if (getValue(cellDivs[j].classList[2]) === 0) {
                        return j;
                    }
                }
            }
        }

        if (getDiagonal1Score() === check) {
            for (let i = 0; i <= 8; i += 4) {
                if (getValue(cellDivs[i].classList[2]) === 0) {
                    return i;
                }
            }
        }

        if (getDiagonal2Score() === check) {
            for (let i = 2; i <= 6; i += 2) {
                if (getValue(cellDivs[i].classList[2]) === 0) {
                    return i;
                }
            }
        }
    }

    return -1;
}

// get optimal move for computer to make
const getOptimalBox = () => {

    requiredMove = winOrBlockMove();
    if (requiredMove != -1) {
        return requiredMove;
    }

    // cases for 2nd move
    if (boxesFilled === 1) {
        if (getValue(cellDivs[4].classList[2]) === 0) {
            return 4;
        }
        else {
            return 0;
        }
    }

    // cases for 4th move
    else if (boxesFilled === 3) {
        if (getDiagonal1Score() === 12 || getDiagonal2Score() === 12) {
            return 1;
        }

        if (getColumnScore(1) === 12 || getRowScore(1) === 12) {
            return 0;
        }

        if (getValue(cellDivs[4].classList[2]) === 10) {
            if (getValue(cellDivs[1].classList[2]) === 1 && getValue(cellDivs[3].classList[2]) === 1) {
                return 0;
            }
            if (getValue(cellDivs[1].classList[2]) === 1 && getValue(cellDivs[5].classList[2]) === 1) {
                return 2;
            }
            if (getValue(cellDivs[3].classList[2]) === 1 && getValue(cellDivs[7].classList[2]) === 1) {
                return 6;
            }
            if (getValue(cellDivs[5].classList[2]) === 1 && getValue(cellDivs[7].classList[2]) === 1) {
                return 8;
            }

            if (getValue(cellDivs[1].classList[2]) === 1) {
                if (getValue(cellDivs[6].classList[2]) === 1) {
                    return 0;
                }
                if (getValue(cellDivs[8].classList[2]) === 1) {
                    return 2;
                }
            }
            if (getValue(cellDivs[3].classList[2]) === 1) {
                if (getValue(cellDivs[2].classList[2]) === 1) {
                    return 0;
                }
                if (getValue(cellDivs[8].classList[2]) === 1) {
                    return 6;
                }
            }
            if (getValue(cellDivs[5].classList[2]) === 1) {
                if (getValue(cellDivs[0].classList[2]) === 1) {
                    return 2;
                }
                if (getValue(cellDivs[6].classList[2]) === 1) {
                    return 8;
                }
            }
            if (getValue(cellDivs[7].classList[2]) === 1) {
                if (getValue(cellDivs[0].classList[2]) === 1) {
                    return 6;
                }
                if (getValue(cellDivs[2].classList[2]) === 1) {
                    return 8;
                }
            }
        }

        if (getValue(cellDivs[4].classList[2]) === 1) {
            if (getDiagonal1Score() === 12) {
                return 2;
            }
            if (getDiagonal2Score() === 12) {
                return 0;
            }
        }
    }

    // cases for 6th move
    else if (boxesFilled === 5) {
        if (getColumnScore(1) === 21 && (getRowScore(0) === 12 || getRowScore(2) === 12)) {
            return 3;
        }
        if (getRowScore(1) === 21 && (getColumnScore(0) === 12 || getColumnScore(2) === 12)) {
            return 1;
        }

        if (getDiagonal1Score() === 21) {
            if (getValue(cellDivs[1].classList[2]) === 1 && getValue(cellDivs[3].classList[2]) === 1) {
                return 2;
            }
            if (getValue(cellDivs[5].classList[2]) === 1 && getValue(cellDivs[7].classList[2]) === 1) {
                return 2;
            }
        }
        if (getDiagonal2Score() === 21) {
            if (getValue(cellDivs[1].classList[2]) === 1 && getValue(cellDivs[5].classList[2]) === 1) {
                return 0;
            }
            if (getValue(cellDivs[3].classList[2]) === 1 && getValue(cellDivs[7].classList[2]) === 1) {
                return 0;
            }
        }

        if (getDiagonal1Score() === 12) {
            if (getColumnScore(1) === 12) {
                if (getValue(cellDivs[0].classList[2]) === 1) {
                    return 6;
                }
                else {
                    return 2;
                }
            }
            if (getRowScore(1) === 12) {
                if (getValue(cellDivs[0].classList[2]) === 1) {
                    return 2;
                }
                else {
                    return 6;
                }
            }
        }
        if (getDiagonal2Score() === 12) {
            if (getColumnScore(1) === 12) {
                if (getValue(cellDivs[2].classList[2]) === 1) {
                    return 8;
                }
                else {
                    return 0;
                }
            }
            if (getRowScore(1) === 12) {
                if (getValue(cellDivs[2].classList[2]) === 1) {
                    return 0;
                }
                else {
                    return 8;
                }
            }
        }
    }

    // cases for 8th move
    if (boxesFilled === 7) {
        for (let i = 0; i < 9; i++) {
            if (getValue(cellDivs[i].classList[2]) === 0) {
                return i;
            }
        }
    }

    return -1;

}

// check if game is won
const isGameWon = (boxNumber) => {

    value = xIsNext ? 1 : 10;

    rowNumber = Math.floor(boxNumber / 3);
    rowScore = getRowScore(rowNumber);
    if (rowScore === 3 * value) {
        cellDivs[3 * rowNumber + 0].classList.add('won');
        cellDivs[3 * rowNumber + 1].classList.add('won');
        cellDivs[3 * rowNumber + 2].classList.add('won');
        return true;
    }

    columnNumber = boxNumber % 3;
    columnScore = getColumnScore(columnNumber);
    if (columnScore === 3 * value) {
        cellDivs[columnNumber + 0].classList.add('won');
        cellDivs[columnNumber + 3].classList.add('won');
        cellDivs[columnNumber + 6].classList.add('won');
        return true;
    }

    if (boxNumber % 4 === 0) {
        diagonal1Score = getDiagonal1Score();

        if (diagonal1Score === 3 * value) {
            cellDivs[0].classList.add('won');
            cellDivs[4].classList.add('won');
            cellDivs[8].classList.add('won');
            return true;
        }
    }

    if (boxNumber % 2 === 0) {
        diagonal2Score = getDiagonal2Score();

        if (diagonal2Score === 3 * value) {
            cellDivs[2].classList.add('won');
            cellDivs[4].classList.add('won');
            cellDivs[6].classList.add('won');
            return true;
        }
    }

    return false;
}

// get number corresponding to cell
const getBoxNumber = (location) => {
    switch (location) {
        case "top-left":
            return 0
        case "top-middle":
            return 1
        case "top-right":
            return 2
        case "middle-left":
            return 3
        case "middle-middle":
            return 4
        case "middle-right":
            return 5
        case "bottom-left":
            return 6
        case "bottom-middle":
            return 7
        case "bottom-right":
            return 8
        default:
            return -1;
    }
}

// event handlers
const getSymbol = () => xIsNext ? '×' : '○';

const handleReset = () => {
    xIsNext = true;
    boxesFilled = 0;
    gameIsLive = true;
    for (const cellDiv of cellDivs) {
        cellDiv.classList.remove('x');
        cellDiv.classList.remove('o');
        cellDiv.classList.remove('won');
    }
    if (is2P) {
        statusDiv.innerHTML = `${getSymbol()} is next`;
        typeDiv.innerHTML = `Player vs Player`;
    } else {
        statusDiv.innerHTML = `Player's turn`;
        typeDiv.innerHTML = `Player vs Computer`;
    }
};

const handleCellClick = (e) => {

    if (!gameIsLive) {
        return;
    }

    const classList = e.target.classList;

    const location = classList[1];

    if (classList[2] === 'x' || classList[2] === 'o') {
        return;
    }

    if (xIsNext) {
        classList.add('x');
    } else {
        classList.add('o');
    }

    if (isGameWon(getBoxNumber(location))) {
        gameIsLive = false;
        if (is2P) {
            statusDiv.innerHTML =
            `<span>
                ${getSymbol()} has won!
            </span>`;
        } else {
            statusDiv.innerHTML = 
            `<span>
                ${(getSymbol() === 'x') ? 'Player':'Computer'} has won!
            </span>`;
        }
    }

    boxesFilled++;

    if (boxesFilled === 9 && gameIsLive) {
        statusDiv.innerHTML = `<span>game is drawn!</span>`;
        gameIsLive = false;
    }

    xIsNext = !xIsNext;

    if (gameIsLive) {
        if (is2P) {
            statusDiv.innerHTML = `${getSymbol()} is next`;
        } else {
            statusDiv.innerHTML = `Player's turn`;
        }
    }

    if (!is2P && !xIsNext && gameIsLive) {
        statusDiv.innerHTML = `Computer is thinking`;
        // setTimeout(cellDivs[getOptimalBox()].click(), 2000); -------- add a timer before computer executes its move
        cellDivs[getOptimalBox()].click();
    }

};

const handleTypeChange = (e) => {
    newGameType = is2P ? 'Player vs Computer' : 'Player vs Player';
    if (confirm(`Are you sure you want to change game type to ${newGameType}? ${(boxesFilled===0)?'':'The current progress will be lost.'}`)) {
        is2P = !is2P;
        handleReset();
    }
}

// event listeners
resetDiv.addEventListener('click', handleReset);

for (const cellDiv of cellDivs) {
    cellDiv.addEventListener('click', handleCellClick);
}

changeDiv.addEventListener('click', handleTypeChange);

