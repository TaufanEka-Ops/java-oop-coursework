# Java OOP Coursework

Four console exercises from a 2020/2021 **Object-Oriented Programming** course,
rescued from `.rar` archives of IntelliJ projects and consolidated into one
repository that actually compiles, runs, and checks its own output on a current
JDK.

> **Honest framing first.** These are **course exercises, not projects.** They
> are small, assignment-shaped programs written to practise a specific
> language feature each week. There is no application here, no architecture,
> no tests of business logic — just clean, readable demonstrations of Java OOP
> fundamentals. Some of them follow a public tutorial closely (see
> [authorship](#authorship-and-provenance) below); they are marked accordingly and
> nothing has been inflated to look bigger than it is.

---

## What the coursework covered

The course built up object-oriented Java in the usual teaching order:

| # | Exercise | Concepts demonstrated |
|---|----------|-----------------------|
| [01-class-and-object](exercises/01-class-and-object/) | Declaring a class and creating objects | class vs. object, `new`, object identity, default `toString()` |
| [02-person-constructors](exercises/02-person-constructors/) | State and constructors | instance fields, constructor overloading, methods, encapsulation (`private` + accessors) |
| [03-inheritance-polymorphism](exercises/03-inheritance-polymorphism/) | Constructor chaining and inheritance | `this(...)` / `super(...)` delegation, `extends`, method overriding, `super` calls, dynamic dispatch |
| [04-peradaban-uas](exercises/04-peradaban-uas/) | Final exam | encapsulation, inheritance, polymorphism in a small domain model |

The first three share the same deliberately tiny domain (a `Person`) so that the
focus stays on the language feature, not the problem. The fourth is the
end-of-term exam, written around a "civilisations" theme.

## How to run everything

Requires **JDK 17 or newer** (developed and verified on **OpenJDK 25.0.4.1**).
No Maven, no Gradle, no third-party dependencies — just `javac`, `java`, and
`make`.

```bash
make verify     # compile, run and check the stdout of every exercise
```

`make verify` drives `verify/run-all.sh`, which for each exercise:

1. compiles the sources into `build/<exercise>/`,
2. runs the program's entry point,
3. diffs its stdout against the checked-in expected output in `verify/expected/`,
4. prints `PASS`/`FAIL` per exercise and exits non-zero if anything is off.

Example real run:

```
[01] class-and-object
  PASS  01-class-and-object
[02] person-constructors
  PASS  02-person-constructors
[03] inheritance-polymorphism (3 entry points)
  PASS  03-inheritance-polymorphism
[04] peradaban-uas
  PASS  04-peradaban-uas
Result: 4 passed, 0 failed
```

Other targets:

```bash
make build      # compile everything into build/ (no runs, no checks)
make clean      # delete build/
```

To run a single exercise by hand, each exercise's README gives the exact two
commands (compile, then run). For example:

```bash
javac -d build/02 exercises/02-person-constructors/src/*.java
java  -cp build/02 PersonApp
```

There is no JUnit here on purpose: `javac` and `java` are not available to a
Maven/Gradle runner in this environment, so the verification is a plain shell
harness that shells out to the real toolchain. It is a real test — corrupting
an expected file makes it exit `1` (demonstrated in `docs/evaluation.md`).

## Authorship and provenance

This is a portfolio reconstruction, so each exercise carries an explicit
authorship classification. Full evidence is in
[docs/evaluation.md](docs/evaluation.md); the summary is:

| Exercise | Classification | Why |
|----------|----------------|-----|
| 01-class-and-object | **own work** | Matches the student's own submitted report (`Tugas Java OOP`); the assignment's minimal deliverable. |
| 02-person-constructors | **template extended** | Follows the widely-circulated *Programmer Zaman Now* Java OOP tutorial closely (see evidence), transcribed and lightly adapted for the assignment. |
| 03-inheritance-polymorphism | **template extended** | Same tutorial lineage; adds the `this`/`super` and `Manager`/`Shape` examples from that lesson. |
| 04-peradaban-uas | **own work** | Final-exam answer under timed conditions, on an original "civilisations" theme not found in any tutorial. |

**Excluded material.** The original archives also contained a `PBO Praktikum`
folder that is verbatim third-party course material (a Dicoding "Java
Fundamental" course: `com.dicoding.javafundamental.*` packages, tutorial
narration in the comments, a deliberate `ClassCastException` demo). It is **not
the student's work** and has been left out of this repository. Six archive copies
of the `person` exercise (`OOP2`…`OOP7`) turned out to be byte-identical
duplicates of two snapshots; only the two distinct snapshots are represented
here. See `docs/evaluation.md` for the full reasoning.

## Fixes and data hygiene

The exercises are reproduced close to how they were submitted, but a small,
documented set of genuine defects was fixed rather than preserved:

- the class `person` was renamed `Person` (the assignment itself asks for
  CamelCase class names);
- public mutable fields were encapsulated behind accessors;
- a constructor in `Manager` contained a self-assignment bug
  (`this.company = company;`) that left the field permanently `null`;
- the exam's subclasses created a throwaway parent object instead of using
  their own inherited state — the actual point of the exercise;
- a missing space in string concatenation that printed `daridelapan SM`.

Nothing was rewritten beyond recognition; the teaching intent of every exercise
is intact. `docs/evaluation.md` records a before/after for each change, and the
pre-fix sources are kept under `docs/originals/` so the diff is inspectable.

**Personal data.** The original assignment document contained the author's name
and student ID. The ID does **not** appear anywhere in the executable sources;
no print statement contains a name or e-mail address. The ID has been dropped
entirely from this repository, and the assignment PDF itself is not included.
See the data-hygiene section of `docs/evaluation.md`.

## What this shows and what it does not

**What it shows**

- Working, idiomatic Java: classes, encapsulation, constructor overloading and
  chaining, inheritance, method overriding, `super` calls, dynamic dispatch.
- Ability to take messy, scattered, IDE-generated coursework and turn it into a
  clean repository: consistent layout, no build artefacts, a one-command build
  and verification, and honest documentation.
- Discipline about provenance: labelling tutorial-derived work as such instead
  of passing it off as original.

**What it does not show**

- Application-scale engineering. There is no non-trivial domain logic, no
  concurrency, no persistence, no networking, no API design.
- Automated unit testing of behaviour. The verification harness checks
  program output; it is not a JUnit suite, and the exercises were not written
  with tests.
- Modern tooling. No Maven, Gradle, or CI pipeline is present, because the
  environment had none available and the exercises do not need them.
- Original design. Exercises 02 and 03 are tutorial-following coursework, and
  are labelled as such. Only 04 was written unaided.

## Repository layout

```
.
├── exercises/
│   ├── 01-class-and-object/
│   ├── 02-person-constructors/
│   ├── 03-inheritance-polymorphism/
│   └── 04-peradaban-uas/
├── verify/
│   ├── run-all.sh              # compile + run + check every exercise
│   └── expected/               # expected stdout per exercise
├── docs/
│   ├── evaluation.md           # authorship evidence + before/after fixes
│   └── originals/              # pre-fix sources, for diffing
├── Makefile
├── LICENSE
└── README.md
```

## License

MIT — see [LICENSE](LICENSE).
