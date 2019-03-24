cmake_minimum_required(VERSION 3.11)
project(algorithmING LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 14)

#aux_source_directory(${CMAKE_CURRENT_LIST_DIR}/cpp algorithm_src)
#add_executable(${PROJECT_NAME} ${algorithm_src})
add_executable(Problem1000 leetCode/dynamicProgramming/Problem1000.cpp)
add_executable(Problem172 leetCode/math/Problem172.cpp)
add_executable(Problem401 leetCode/math/Problem400.cpp)
add_executable(Problem1005 leetCode/heap/Problem1005.cpp)
add_executable(Problem1007 leetCode/array/Problem1007.cpp)
add_executable(Problem1021 leetCode/array/Problem1021.cpp)
