cmake_minimum_required(VERSION 3.11)
project(algorithmING LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 14)

file(GLOB algorithm_src
    ${CMAKE_CURRENT_SOURCE_DIR}/leetCode/array/*.cpp
    ${CMAKE_CURRENT_SOURCE_DIR}/leetCode/backTrack/*.cpp
    ${CMAKE_CURRENT_SOURCE_DIR}/leetCode/binarySearch/*.cpp
    ${CMAKE_CURRENT_SOURCE_DIR}/leetCode/bit/*.cpp
)

#aux_source_directory(${CMAKE_CURRENT_SOURCE_DIR}/leetCode/array/*.cpp algorithm_src)
#aux_source_directory(./leetCode algorithm_src)

add_executable(${PROJECT_NAME} ${algorithm_src})
add_executable(Problem1000 leetCode/dynamicProgramming/Problem1000.cpp)
add_executable(Problem172 leetCode/math/Problem172.cpp)
add_executable(Problem401 leetCode/math/Problem400.cpp)
add_executable(Problem1005 leetCode/heap/Problem1005.cpp)
add_executable(Problem1007 leetCode/array/Problem1007.cpp)
add_executable(Problem1021 leetCode/array/Problem1021.cpp)
add_executable(Problem1008 leetCode/tree/Problem1008.cpp)
