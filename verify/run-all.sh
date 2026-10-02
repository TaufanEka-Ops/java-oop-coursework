#!/usr/bin/env bash
#
# verify/run-all.sh — compile and run every exercise from the command line and
# check the program's stdout against the expected output.
#
# Usage:
#   bash verify/run-all.sh            # compile, run, and check stdout
#   bash verify/run-all.sh --compile  # compile only (no run, no check)
#
# Exit status: 0 if everything compiled and matched, non-zero otherwise.
#
# Expected outputs live in verify/expected/. Exercise 01 cannot be compared
# byte-for-byte because it deliberately prints Object.toString() identity hash
# codes, which differ on every run; it is matched against a regular expression
# instead.

set -u

REPO_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
BUILD_DIR="$REPO_DIR/build"
EXP_DIR="$REPO_DIR/verify/expected"
JAVAC="${JAVAC:-javac}"
JAVA="${JAVA:-java}"

MODE="verify"
if [ "${1:-}" = "--compile" ] || [ "${1:-}" = "--compile-only" ]; then
  MODE="compile"
fi

pass=0
fail=0
failed_names=""

green() { printf '\033[32m%s\033[0m\n' "$1"; }
red()   { printf '\033[31m%s\033[0m\n' "$1"; }
bold()  { printf '\033[1m%s\033[0m\n' "$1"; }

# compile_exercise <name> <src-root>
compile_exercise() {
  local name="$1" srcroot="$2"
  rm -rf "$BUILD_DIR/$name"
  mkdir -p "$BUILD_DIR/$name"
  local files
  files=$(find "$srcroot" -name '*.java' | sort)
  if [ -z "$files" ]; then
    red "  no .java sources under $srcroot"
    return 1
  fi
  if ! "$JAVAC" -encoding UTF-8 -d "$BUILD_DIR/$name" $files 2>"$BUILD_DIR/$name.compile.log"; then
    red "  COMPILE FAILED: $name"
    sed 's/^/    /' "$BUILD_DIR/$name.compile.log"
    return 1
  fi
  return 0
}

# record <name> <ok:0|1>
record() {
  local name="$1" ok="$2"
  if [ "$ok" -eq 0 ]; then
    green "  PASS  $name"
    pass=$((pass + 1))
  else
    red   "  FAIL  $name"
    fail=$((fail + 1))
    failed_names="$failed_names $name"
  fi
}

# check_exact <name> <expected-file> <actual-file>
check_exact() {
  local name="$1" exp="$2" act="$3"
  if diff -u "$exp" "$act" >"$BUILD_DIR/$name.diff" 2>&1; then
    return 0
  fi
  red "  output mismatch for $name (expected vs actual):"
  sed 's/^/    /' "$BUILD_DIR/$name.diff"
  return 1
}

# check_pattern <name> <regex-file> <actual-file>
check_pattern() {
  local name="$1" patfile="$2" act="$3"
  local regex line lineno=0 bad=0
  regex="$(cat "$patfile")"
  while IFS= read -r line || [ -n "$line" ]; do
    lineno=$((lineno + 1))
    if ! printf '%s' "$line" | grep -Eq "$regex"; then
      bad=$((bad + 1))
      red "    line $lineno does not match /$regex/: $line"
    fi
  done <"$act"
  if [ "$lineno" -ne 3 ]; then
    red "    expected 3 lines of output, got $lineno"
    bad=$((bad + 1))
  fi
  [ "$bad" -eq 0 ]
}

echo "=============================================================="
bold "Java OOP coursework — build & verification"
echo "javac: $("$JAVAC" -version 2>&1)"
echo "java : $("$JAVA" -version 2>&1 | head -1)"
echo "repo : $REPO_DIR"
echo "mode : $MODE"
echo "=============================================================="

if [ "$MODE" = "verify" ]; then
  rm -rf "$BUILD_DIR"
fi
mkdir -p "$BUILD_DIR"

# ---------------------------------------------------------------- exercise 01
bold "[01] class-and-object"
if compile_exercise "01-class-and-object" "$REPO_DIR/exercises/01-class-and-object/src"; then
  if [ "$MODE" = "verify" ]; then
    "$JAVA" -cp "$BUILD_DIR/01-class-and-object" PersonApp >"$BUILD_DIR/01-class-and-object.out" 2>&1
    if check_pattern "01-class-and-object" "$EXP_DIR/01-class-and-object.pattern" "$BUILD_DIR/01-class-and-object.out"; then
      record "01-class-and-object" 0
    else
      record "01-class-and-object" 1
    fi
  fi
fi

# ---------------------------------------------------------------- exercise 02
bold "[02] person-constructors"
if compile_exercise "02-person-constructors" "$REPO_DIR/exercises/02-person-constructors/src"; then
  if [ "$MODE" = "verify" ]; then
    "$JAVA" -cp "$BUILD_DIR/02-person-constructors" PersonApp >"$BUILD_DIR/02-person-constructors.out" 2>&1
    if check_exact "02-person-constructors" "$EXP_DIR/02-person-constructors.txt" "$BUILD_DIR/02-person-constructors.out"; then
      record "02-person-constructors" 0
    else
      record "02-person-constructors" 1
    fi
  fi
fi

# ---------------------------------------------------------------- exercise 03
bold "[03] inheritance-polymorphism (3 entry points)"
if compile_exercise "03-inheritance-polymorphism" "$REPO_DIR/exercises/03-inheritance-polymorphism/src"; then
  if [ "$MODE" = "verify" ]; then
    : >"$BUILD_DIR/03-inheritance-polymorphism.out"
    for main in PersonApp ShapeApp ManagerApp; do
      echo "--- $main ---" >>"$BUILD_DIR/03-inheritance-polymorphism.out"
      "$JAVA" -cp "$BUILD_DIR/03-inheritance-polymorphism" "$main" >>"$BUILD_DIR/03-inheritance-polymorphism.out" 2>&1
    done
    if check_exact "03-inheritance-polymorphism" "$EXP_DIR/03-inheritance-polymorphism.txt" "$BUILD_DIR/03-inheritance-polymorphism.out"; then
      record "03-inheritance-polymorphism" 0
    else
      record "03-inheritance-polymorphism" 1
    fi
  fi
fi

# ---------------------------------------------------------------- exercise 04
bold "[04] peradaban-uas"
if compile_exercise "04-peradaban-uas" "$REPO_DIR/exercises/04-peradaban-uas/src"; then
  if [ "$MODE" = "verify" ]; then
    "$JAVA" -cp "$BUILD_DIR/04-peradaban-uas" peradaban.RunPeradaban >"$BUILD_DIR/04-peradaban-uas.out" 2>&1
    if check_exact "04-peradaban-uas" "$EXP_DIR/04-peradaban-uas.txt" "$BUILD_DIR/04-peradaban-uas.out"; then
      record "04-peradaban-uas" 0
    else
      record "04-peradaban-uas" 1
    fi
  fi
fi

echo "=============================================================="
if [ "$MODE" = "compile" ]; then
  bold "Compile-only run finished."
else
  bold "Result: $pass passed, $fail failed"
  if [ "$fail" -ne 0 ]; then
    red "Failed:$failed_names"
  fi
fi
echo "=============================================================="

[ "$fail" -eq 0 ]
