# 02 — Fields, Constructors and Encapsulation

**Concepts:** instance fields, a constant field, constructor overloading,
instance methods, encapsulation (`private` fields with public accessors).

`Person` has two mutable fields (`name`, `address`) and one constant
(`country = "Indonesia"`), plus three overloaded constructors: one taking both
values, one taking just a name, and a no-argument constructor. `PersonApp`
exercises all three: it constructs a fully-populated person and prints its
fields, calls `sayHello`, constructs a name-only person, and constructs an
empty person and sets its name through a setter.

This is the exercise where mutable state first appears, so it is also where
encapsulation matters — see the fixes below.

**Authorship:** template extended — the domain (`Person` with `name`,
`address`, `country = "Indonesia"` and a `sayHello` method) follows the
*Programmer Zaman Now* Java OOP tutorial; the student transcribed and adapted it
for the assignment. See `docs/evaluation.md`.

## Compile and run

From the repository root:

```bash
javac -d build/02 exercises/02-person-constructors/src/*.java
java  -cp build/02 PersonApp
```

## Expected output

```
Rahmat
Surabaya
Indonesia
Hello Budi, My name is Rahmat
Budi
Hello Budi, My name is Joko
```

## Fixes applied

- fields made `private` and exposed through getters/setters (the original had
  package-private mutable fields that `PersonApp` wrote to directly);
- the `sayHello` parameter renamed `paraname` → `otherName`, and the message
  spelling `Myname` → `My name`;
- the one-argument constructor's result is now actually observed (the original
  created `person2` and never used it).
