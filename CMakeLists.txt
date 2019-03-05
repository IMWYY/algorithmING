cmake_minimum_required(VERSION 3.11)
project(algorithmING LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 14)

#aux_source_directory(${CMAKE_CURRENT_LIST_DIR}/cpp algorithm_src)
#add_executable(${PROJECT_NAME} ${algorithm_src})
add_executable(main leetCode/dynamicProgramming/Problem1000.cpp)