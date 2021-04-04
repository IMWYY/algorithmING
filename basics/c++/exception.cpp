#include <assert.h>

#include <iostream>
#include <stdexcept>
#include <exception>
#include <string>

// Here we specify the exceptions that this function
// throws.
void fun(int *ptr,
         int x) throw(int *, int, char, std::string,
                      const char *, std::exception)  // Dynamic Exception specification
{
  if (ptr == NULL) throw ptr;
  if (x == 0) throw x;
  if (x > 100) throw 'E';
  if (x < -100) throw std::string("smaller than -100");
  if (x < -50) throw "smaller than -50";
  if (x > 50) throw std::runtime_error("larger than 50");
  std::cout << "call fun successfully!" << std::endl;
  /* Some functionality */
}

std::string handle_exception(int *ptr, int x) {
  try {
    fun(ptr, x);
    return "";
  } catch (int *p) {
    std::cout << "Caught exception int* from fun(): " << p << std::endl;
    return "int*";
  } catch (int x) {
    std::cout << "Caught exception int from fun(): " << x << std::endl;
    return "int";
  } catch (char c) {
    std::cout << "Caught exception char from fun(): " << c << std::endl;
    return "char";
  } catch (std::string s) {
    std::cout << "Caught exception string from fun(): " << s << std::endl;
    return "string";
  } catch (const char *s) {
    std::cout << "Caught exception char* from fun(): " << s << std::endl;
    return "char*";
  } catch (std::runtime_error e) {
    std::cout << "Caught exception runtime_error from fun(): " << e.what() << std::endl;
    return "runtime";
  } catch (...) {
    std::cout << "Caught other exception from fun()" << std::endl;
    return "other";
  }
}

int main() {
  int *p = new int(9);
  std::string res = handle_exception(p, 3);  //
  assert(res.empty());
  res = handle_exception(p, 0);
  assert(res == "int");
  res = handle_exception(nullptr, 3);
  assert(res == "int*");
  res = handle_exception(p, 101);
  assert(res == "char");
  res = handle_exception(p, -101);
  assert(res == "string");
  res = handle_exception(p, -51);
  assert(res == "char*");
  res = handle_exception(p, 51);
  assert(res == "runtime");
  res = handle_exception(nullptr, 0);
  assert(res == "int*");
  return 0;
}