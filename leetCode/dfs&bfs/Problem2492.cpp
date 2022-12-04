#include <vector>

void dfs(int s, const std::vector<std::vector<std::pair<int, int>>>& g, 
        std::vector<bool>& v, int& res) {
    v[s] = true;
    for (auto [n, w] : g[s]) {
        res = std::min(res, w);
        if (v[n]) continue;
        dfs(n, g, v, res);
    }
}

int minScore(int n, std::vector<std::vector<int>>& roads) {
    std::vector<std::vector<std::pair<int, int>>> g(n + 1);
    for (auto & r : roads) {
        g[r[0]].push_back({r[1], r[2]});
        g[r[1]].push_back({r[0], r[2]});
    }
    std::vector<bool> v(n, false);
    int res = INT_MAX;
    dfs(1, g, v, res);
    return res;
}
