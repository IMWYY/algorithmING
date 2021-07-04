#include <string>

bool isPalindrome(std::string s) {
  for (int i = 0, j = s.size() - 1; i < j; i++, j--) {
    // Move 2 pointers from each end until they collide
    while (!std::isalnum(s[i]) && i < j)
      i++;  // Increment left pointer if not alphanumeric
    while (!std::isalnum(s[j]) && i < j)
      j--;  // Decrement right pointer if no alphanumeric
    if (std::toupper(s[i]) != std::toupper(s[j]))
      return false;  // Exit and return error if not match
  }

  return true;
}