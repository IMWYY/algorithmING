#include <algorithm>
#include <bitset>
#include <climits>
#include <cmath>
#include <iostream>
#include <queue>
#include <string>
#include <vector>

/**
 *
 * You are given an array points, an integer angle, and your location, where
 * location = [posx, posy] and points[i] = [xi, yi] both denote integral
 * coordinates on the X-Y plane.
 *
 * Initially, you are facing directly east from your position. You cannot move
from
 * your position, but you can rotate. In other words, posx and posy cannot be
 * changed. Your field of view in degrees is represented by angle, determining
how
 * wide you can see from any given view direction. Let d be the amount in
degrees
 * that you rotate counterclockwise. Then, your field of view is the inclusive
 * range of angles [d - angle/2, d + angle/2].
 *
 * You can see some set of points if, for each point, the angle formed by the
 * point, your position, and the immediate east direction from your position is
in
 * your field of view.
 *
 * There can be multiple points at one coordinate. There may be points at your
 * location, and you can always see these points regardless of your rotation.
 * Points do not obstruct your vision to other points.
 *
 * Return the maximum number of points you can see.
 *
 */

int visiblePoints(std::vector<std::vector<int>>& points, int angle,
                  std::vector<int>& location) {
  int answer = 0;
  double pi = acos(-1.0);
  std::vector<double> pointAngles;
  for (auto p : points) {
    if (p[0] == location[0] && p[1] == location[1]) {
      // these are points with same location as yours
      answer += 1;
    } else {
      int dx = p[0] - location[0];
      int dy = p[1] - location[1];
      double angle =
          atan2(dy, dx);  // gives inverse tangent in radians for a coordinate
      // convert to degrees
      double angle1 = angle * (180) / pi;
      pointAngles.push_back(angle1);
    }
  }
  sort(pointAngles.begin(), pointAngles.end());
  int n = pointAngles.size();
  for (int i = 0; i < n; ++i) {
    pointAngles.push_back(pointAngles[i] + 360);
    // this step is important!!! push the angles again by adding 360 to handle
    // the case of consecutive points being after a complete cycle. ex - 345 and
    // 14 (if angle >= 29)
  }
  int start = 0;
  int cnt = 0;
  for (int i = 0; i < pointAngles.size(); ++i) {
    if (pointAngles[i] - pointAngles[start] > angle) {
      start++;
    }
    cnt = std::max(cnt, i - start + 1);
  }
  answer += cnt;
  return answer;
}