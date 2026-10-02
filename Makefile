# Java OOP coursework — plain javac/java build. No Maven or Gradle required.
#
#   make verify   compile, run and check the stdout of every exercise
#   make build    compile everything into build/ (no runs)
#   make clean    delete build/

SHELL := /bin/bash
JAVAC ?= javac
JAVA  ?= java

.PHONY: all build verify clean

all: verify

## Compile every exercise into build/. No tests are run.
build:
	@bash verify/run-all.sh --compile

## Compile, run and check the stdout of every exercise against verify/expected/.
verify:
	@bash verify/run-all.sh

## Remove all build artefacts.
clean:
	@rm -rf build
	@echo "cleaned build/"
