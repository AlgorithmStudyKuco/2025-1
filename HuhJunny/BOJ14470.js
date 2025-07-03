const input = require('fs').readFileSync('/dev/stdin', 'utf8').trim().split('\n').map(Number);

let A = input[0];
const B = input[1];
const C = input[2];
const D = input[3];
const E = input[4];

let time = 0;
let frozen = true;

while (A < B) {
    if (A < 0) {
        A++;
        time += C;
    } else if (A === 0 && frozen) {
        frozen = false;
        time += D;
    } else {
        A++;
        time += E;
    }
}

console.log(time);