cmake_minimum_required(VERSION 3.13)
project(algorithmING LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 14)

#aux_source_directory(${CMAKE_CURRENT_LIST_DIR}/cpp algorithm_src)
#add_executable(${PROJECT_NAME} ${algorithm_src})
add_executable(Problem051 cpp/Problem051.cpp)
add_executable(Problem998 cpp/Problem998.cpp)
add_executable(Problem396 cpp/Problem396.cpp)