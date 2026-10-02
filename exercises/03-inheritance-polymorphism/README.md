# 03 — Constructor Chaining, Inheritance and Polymorphism

**Concepts:** constructor delegation with `this(...)` and `super(...)`,
inheritance with `extends`, method overriding, calling the overridden parent
method with `super`, and dynamic dispatch.

This exercise has three entry points:

- **`PersonApp`** — `Person`'s constructors now chain into each other
  (`Person()` → `Person(String)` → `Person(String, String)`) instead of
  duplicating assignment logic.
- **`ShapeApp`** — `Rectangle` overrides `Shape.getCorner()`, and
  `getParentCorner()` uses `super.getCorner()` to reach the parent's version.
- **`ManagerApp`** — `VicePresident extends Manager`; both override
  `sayHello`, and the version that runs is chosen by the object's runtime type.

**Authorship:** template extended — the `Manager`/`VicePresident` and
`Shape`/`Rectangle` examples follow the *Programmer Zaman Now* Java OOP
tutorial (the tell-tale `super(...)` structure and the `Eko`/`Budi`/`Joko`
sample names). See `docs/evaluation.md`.

## Compile and run

From the repository root:

```bash
javac -d build/03 exercises/03-inheritance-polymorphism/src/*.java
java  -cp build/03 PersonApp
java  -cp build/03 ShapeApp
java  -cp build/03 ManagerApp
```

## Expected output

`PersonApp`:

```
Rahmat
Surabaya
Indonesia
Hello Budi, My name is Rahmat
Budi
Hello Budi, My name is Joko
```

`ShapeApp`:

```
0
4
0
```

`ManagerApp`:

```
Hi Budi, My name is Manager Eko
Hi Budi, My name is ViceP Joko
```

(`0 4 0` is the point of `ShapeApp`: the parent returns `0`, the override
returns `4`, and `super` still reaches the parent's `0`.)

## Fixes applied

- `Manager`'s constructor contained a self-assignment bug
  (`this.company = company;` where `company` was the field, so the parameter
  was never stored and the field stayed `null`); it now takes a real `company`
  parameter, with a delegating one-argument constructor that keeps the original
  `new Manager("Eko")` call working;
- `Manager`/`VicePresident` fields encapsulated and the `Name` field renamed
  `name`;
- `Shape` and `Rectangle` split into one public class per file;
- the local variables `Shape`/`Rectangle` in `ShapeApp` (which shadowed the
  type names) renamed `shape`/`rectangle`.
