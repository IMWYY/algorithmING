if [ -d "build" ]; then
  rm -rf build
fi
mkdir build
cd build
cmake ../
make $1 -j
# echo -e "\n"
/$1