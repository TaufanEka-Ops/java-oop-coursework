# 01 — Class and Object

**Concepts:** class vs. object, the `new` operator, object identity, the
default `Object.toString()`.

`Person` is an empty class — no fields, no methods — and `PersonApp` creates
three instances of it using three different declaration styles (`var`
inference, an explicit type, and separate declaration-then-assignment), then
prints each one. Because `Person` does not override `toString()`, the output is
the default `ClassName@hexIdentityHashCode`, so the three lines differ from
each other and change on every run. This is the assignment's minimal
deliverable: it shows that a class is a blueprint and that each `new` produces a
distinct object.

**Authorship:** own work — this matches the student's submitted report
(`Tugas Java OOP`) exactly.

## Compile and run

From the repository root:

```bash
javac -d build/01 exercises/01-class-and-object/src/*.java
java  -cp build/01 PersonApp
```

## Expected output

Three lines of the form (the hex suffix varies per run):

```
Person@1dbd16a6
Person@7ad041f3
Person@251a69d7
```

Because the hash codes are non-deterministic, `verify/run-all.sh` checks this
exercise against the pattern `^Person@[0-9a-f]{1,8}$` rather than a fixed file.
