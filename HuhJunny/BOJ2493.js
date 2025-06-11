const input = require("fs").readFileSync(process.platform === "linux" ? "/dev/stdin" : "./input.txt").toString.trim().split("\n").map((el) => el.split(" ").map(Number));

const stackTower = [];

for (let i = 0; i < input[1].length; i++) {
    let me = input[1][i]
    const stackLen = stackTower.length;
    for (let j = stackLen - 1; j > -2; j--) {
        if (stackTower.length == 0) {
            process.stdout.write("0 ");
            break;
        } else if (me < stackTower[j][0]) {
            process.stdout.write((stackTower[j][1] + 1) + " ");
            break;
        } else {
            stackTower.pop();
            continue;
        }
    }
    stackTower.push([input[1][i], i]);
}