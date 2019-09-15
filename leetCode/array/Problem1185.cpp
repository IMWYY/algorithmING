#include <bitset>
#include <string>
#include <unordered_map>
#include <vector>

using namespace std;

/**
 * Given a date, return the corresponding day of the week for that date.
 *
 * The input is given as three integers representing the day, month and year
 * respectively.
 *
 * Return the answer as one of the following values {"Sunday", "Monday",
 * "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}.
 *
 */

std::string weekdays[7] = {"Sunday",   "Monday", "Tuesday", "Wednesday",
                           "Thursday", "Friday", "Saturday"};

int dayOfYear(int day, int month, int year) {
  std::vector<int> days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
  if (isLeapYear(year)) days[1] = 29;
  int result = 0;
  for (int i = 0; i < month - 1; ++i) result += days[i];
  result += day;
  return result;
}

bool isLeapYear(int year) {
  return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
}

// calculate the offset between 2019.8.31, which is Saturday
string dayOfTheWeek(int day, int month, int year) {
  int offset = 0;
  if (year > 2019 || (year == 2019 && month > 8)) {
    for (int i = 2019; i < year; ++i) offset += isLeapYear(i + 1) ? 366 : 365;
    offset += (dayOfYear(day, month, 2019) - dayOfYear(31, 8, 2019));
    return weekdays[(offset + 6) % 7];
  } else {
    for (int i = year; i < 2019; ++i) offset += isLeapYear(i + 1) ? 366 : 365;
    offset += (dayOfYear(31, 8, year) - dayOfYear(day, month, year));
    return weekdays[6 - offset % 7];
  }
}

// Zelle formula
string dayOfTheWeek(int d, int m, int y, int c = 0) {
  if (m < 3) m += 12, y -= 1;
  c = y / 100, y = y % 100;
  int w = (c / 4 - 2 * c + y + y / 4 + 13 * (m + 1) / 5 + d - 1) % 7;
  return weekdays[(w + 7) % 7];
}