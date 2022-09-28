set -e

cd "$(dirname $0)"

function runCommand() {
  args="$*"
  echo "$args"
  eval "$args"
}

separator="****************************************"
echo "$separator"
rm build .gradle -rf
if runCommand ./gradlew --no-daemon :test -PignoreTestFailures; then
  echo "$separator"
  echo "Notice that the first build passed because we specified -PignoreTestFailures"
else
  echo "$separator"
  echo "Why did the first build fail even though we specified -PignoreTestFailures?"
  exit 1
fi
echo "$separator"
if runCommand ./gradlew --no-daemon :test; then
  echo "$separator"
  echo "Notice that the test passes, reusing the previously cached setting"
else
  echo "$separator"
  echo "The problem is resolved. Yippee!"
  exit 0
fi
echo "$separator"
if runCommand ./gradlew --no-daemon :test --rerun-tasks; then
  echo "$separator"
  echo "It's interesting that --rerun-tasks didn't fix it"
else
  echo "$separator"
  echo "Notice that --rerun-tasks causes the task to fail"
fi
exit 1
