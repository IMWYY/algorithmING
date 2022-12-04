#include <vector>

int traverse(std::vector<std::vector<int>>& graph, int cur, int prev, int seats, int64_t& res) {
    int pl = 1;
    for (auto next : graph[cur]) {
        if (next == prev) continue;
        int nextPl = traverse(graph, next, cur, seats, res);
        if (prev != -1) pl += nextPl;
    }
    if (prev != -1) {
        // fuel needed for prev->cur
        res += (pl % seats) ? (pl/seats + 1) : pl /seats;    
    }
    return pl;
}

long long minimumFuelCost(std::vector<std::vector<int>>& roads, int seats) {
    std::vector<std::vector<int>> graph(roads.size() + 1, std::vector<int>());
    for (int i=0; i<roads.size(); ++i) {
        graph[roads[i][0]].push_back(roads[i][1]);
        graph[roads[i][1]].push_back(roads[i][0]);
    }      
    int64_t res = 0;
    traverse(graph, 0, -1, seats, res);
    return res;
}
