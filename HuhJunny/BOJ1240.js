const readline = require('readline');
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

let input = [];
rl.on('line', function (line) {
    input.push(line.trim());
}).on('close', function () {
    const [N, M] = input[0].split(' ').map(Number);
    const graph = Array.from({ length: N + 1 }, () => []);
    const distanceData = Array.from({ length: N + 1 }, () => []);

    for (let i = 1; i < N; i++) {
        const [a, b, d] = input[i].split(' ').map(Number);
        graph[a].push(b);
        distanceData[a].push(d);
        graph[b].push(a);
        distanceData[b].push(d);
    }

    let result = [];

    for (let j = 0; j < M; j++) {
        const [start, target] = input[N + j].split(' ').map(Number);
        const visited = Array(N + 1).fill(false);
        const queue = [[start, 0]];
        visited[start] = true;
        let found = false;
        let answer = 0;

        while (queue.length > 0) {
            if (found) break;
            const [node, dist] = queue.shift();
            for (let i = 0; i < graph[node].length; i++) {
                const neighbor = graph[node][i];
                const edgeDist = distanceData[node][i];
                if (neighbor === target) {
                    answer = dist + edgeDist;
                    found = true;
                    break;
                }
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.push([neighbor, dist + edgeDist]);
                }
            }
        }

        result.push(answer);
    }

    console.log(result.join('\n'));
});