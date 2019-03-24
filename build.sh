if [ -d "output" ]; then
  rm -rf output
fi
mkdir output
cd output
cmake ../
make $1 -j
# echo -e "\n"
./$1

