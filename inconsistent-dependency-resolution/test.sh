echo test.sh starting
echo test.sh cleaning generated files
cd work && export OUT_DIR=out && rm out .gradle buildSrc/.gradle leanback/leanback-paging/build docs-fake/build ../doclava/.gradle -rf
echo test.sh resetting modification dates of all files
find -type f | xargs touch || true && rm -f log
./run.sh || true

if grep -C 20 "publicDocsTask.*to be not UP-TO-DATE" log; then
  echo reproduced the error
else
  echo did not reproduce the error
  tail -n 20 log
  exit 1
fi
