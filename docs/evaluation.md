# Evaluation: authorship, provenance and changes

This document records how the repository was assembled from the original
archive dumps, what authorship evidence supports each classification, which
material was excluded and why, and the before/after of every code change.

The pre-fix sources are preserved under `docs/originals/` so every diff below
can be re-derived. To regenerate them:

```bash
bash docs/make-diffs.sh
```

---

## 1. Source inventory

The originals arrived as IntelliJ projects zipped into `.rar` archives, each
containing sources **plus** build output (`out/`, `*.class`), IDE metadata
(`.idea/`, `*.iml`), and in one case a bundled library JAR and a Word lock file.

| Archive folder | Package | Distinct `src/` snapshot |
|----------------|---------|--------------------------|
| `Class & Object/Class & Object` | (default) | empty `person` + `PersonApp` (earliest) |
| `OOP2/OOP2`, `OOP3/OOP2`, `OOP5/OOP2`, `OOP6/OOP2` | (default) | `person` with fields + constructors (all four **byte-identical**) |
| `OOP 4/Class & Object`, `OOP 7/Class & Object` | (default) | constructor chaining + `Manager`/`VicePresident` + `Shape`/`Rectangle` (the two are **byte-identical**) |
| `UAS/UAS` | `Peradaban` | final-exam model: `Peradaban`, `Islam`, `Yunani`, `RunPeradaban` |
| `PBO Praktikum/PBO Praktikum` | `com.dicoding.*` | **third-party course material — excluded** |

Duplicates were confirmed with `diff -rq` (no output = identical):

```
$ diff -rq "OOP2/OOP2/src" "OOP3/OOP2/src"      # -> (no differences)
$ diff -rq "OOP2/OOP2/src" "OOP6/OOP2/src"      # -> (no differences)
$ diff -rq "OOP 4/Class & Object/src" "OOP 7/Class & Object/src"   # -> (no differences)
```

The two distinct `person` snapshots and the two distinct inheritance snapshots
map onto exercises 01–03; the exam maps onto exercise 04.

---

## 2. Authorship classification

### 01-class-and-object — **own work**

- The original archive contains the student's own submitted report,
  `Tugas Java OOP.pdf`, which defines the class/object concepts and ends with
  the sentence *"untuk output sendiri masih berbentuk unique code karena object
  person belum terdeklarasi dari object lain"* ("the output is still a unique
  code because the person object has not been declared from another object").
  That is exactly what `PersonApp` prints: `person@<hashcode>`.
- The code is the *minimal* deliverable for that brief — an empty class and
  three instantiations. There are no tutorial-specific extras.
- Caveat: the exercise is trivially small, so "own work" here means *the
  student's own submission*, not *original design*.

### 02-person-constructors — **template extended**

- The domain is a match for the widely-circulated **Programmer Zaman Now**
  (author *khannedy* / Eko Kurniawan Khannedy, GitHub org
  `ProgrammerZamanNow`) Java OOP tutorial: a class named lowercase `person`
  with `String name`, `String address`, `final String country = "Indonesia"`,
  and a `void sayHello(String)` method.
- The class name is lowercase `person`, which contradicts the assignment's own
  instruction to use CamelCase — consistent with code typed from a tutorial
  rather than designed for the brief.
- The sample names `Eko`, `Budi`, `Joko` used elsewhere in this lineage are the
  tutorial author's sample names ("Eko" is the tutorial author's first name).
- The student transcribed and adapted the tutorial for the assignment, so this
  is coursework following a public tutorial → **template extended**, not
  original work.

### 03-inheritance-polymorphism — **template extended**

- Same lineage. `Manager`/`VicePresident` (a vice-president "is-a" manager) and
  `Shape`/`Rectangle` (override `getCorner`, call `super.getCorner`) are the
  tutorial's inheritance and method-overriding examples.
- `ManagerApp` uses the tutorial's sample names verbatim: `new Manager("Eko")`,
  `new VicePresident("Joko")`, `sayHello("Budi")`.
- Classification follows 02: **template extended**.

### 04-peradaban-uas — **own work**

- Original domain and content that appears in no tutorial: the Islamic Golden
  Age (`750 M sampai 1258 M`), ancient Greece, and the specific artefacts
  `"Konsep Robot"`, `"Jam Air"`, `"Istana Emas Nero"`.
- Indonesian method names of the student's own invention (`tampilkandata`,
  `inputdata`) and a package named `Peradaban`, rather than the tutorial's
  English identifiers.
- Archive timestamps support the reading: the tutorial lineage
  (`Class & Object`, `OOP2`…`OOP7`) was created `2021-01-06`, while `UAS` was
  created `2021-03-18` — the end-of-term exam window.
- This is the **final exam (`UAS`)**, i.e. unaided assessment work.

---

## 3. Excluded material

### `PBO Praktikum` — verbatim third-party course material (excluded)

This folder is **not the student's work** and has been left out of the repo:

- its packages are `com.dicoding.javafundamental.*` / `com.dicoding.javaFundamental.*`
  — the namespace of Dicoding's public "Java Fundamental" online course;
- the comments are written *to a learner* by the course author, e.g.
  `// kucing adalah Kucing, pastinya :)` and
  `// akan tetap memanggil constructor dari parent Class`;
- `Inheritance/Main.java` deliberately ends with an intentional runtime crash —
  `Kucing anggora = (Kucing) harimau;` annotated
  *"compile OK tetapi runtime error ClassCastException"* — which is a teaching
  demonstration, not a student's own program;
- one sub-project bundles a third-party JAR (`lib/commons-lang3-3.9.jar`) and a
  pre-built artifact descriptor, i.e. a course scaffold.

Small honesty note: if these had been included, the repository would look
larger and busier, and a reviewer who recognised the Dicoding namespace would
read that as padding. Excluding them is the correct call.

### Other excluded files

- `out/**`, `**/*.class` — compiled output from the IDE build.
- `.idea/**`, `*.iml` — IntelliJ project metadata.
- `~$gas Java OOP.docx` — a zero-value Word lock file.
- `Tugas Java OOP.pdf`, `class1.PNG`, `object1.PNG`, the `UAS/Asset/*.PNG` —
  the assignment brief and IDE screenshots. The PDF carries the student's name
  and ID and the images are instructor/working material, so none are committed.

---

## 4. Changes: before → after

Every change below is a genuine defect or a documented convention fix. The
teaching intent of each exercise is preserved; nothing was rewritten beyond
recognition.

### 4.1 Class renamed `person` → `Person` (all exercises)

Java class names are CamelCase, and the assignment brief itself states
*"dalam penamaan class menggunakan format Camel Case"*. The submitted code used
lowercase `person` throughout.

Before → after: `class person {` → `class Person {` (and file `person.java` →
`Person.java`), with all references updated.

### 4.2 Public mutable fields encapsulated (exercises 02, 03)

Before (`02-person-constructors`, original `person.java`):

```java
class person {
 String name;
 String address;
 final String country = "Indonesia";
 ...
```

After (`exercises/02-person-constructors/src/Person.java`):

```java
public class Person {
    private String name;
    private String address;
    private final String country = "Indonesia";
    ...
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getCountry() { return country; }
}
```

`PersonApp` was updated to read and write through the accessors
(`person1.name` → `person1.getName()`, etc.). This is the classic "public
mutable field" flaw: any caller could put the object into an invalid state.

### 4.3 `Manager` self-assignment bug (exercise 03)

This is a real bug, not a style nit.

Before:

```java
class Manager {
    String Name;
    String company;
    Manager(String Name){
        this.Name = Name;
        this.company = company;   // assigns the field to itself
    }
    void sayHello(String Name){
        System.out.println("Hi " + Name + ", Myname Is Manager " + this.Name);
    }
}
```

There is no `company` parameter, so `this.company = company;` assigns the field
to itself. `company` can never hold anything but `null`. After the fix the
constructor takes a real parameter, with a delegating one-argument constructor
so the original `new Manager("Eko")` call keeps working:

```java
public class Manager {
    private final String name;
    private final String company;

    public Manager(String name, String company) {
        this.name = name;
        this.company = company;
    }

    public Manager(String name) {
        this(name, null);
    }
    ...
}
```

Real diff (`docs/originals/03-inheritance-polymorphism/Manager.java` →
`exercises/03-inheritance-polymorphism/src/Manager.java`):

```diff
-class Manager {
-    String Name;
-    String company;
-    Manager(String Name){
-        this.Name = Name;
+public class Manager {
+    private final String name;
+    private final String company;
+    public Manager(String name, String company) {
+        this.name = name;
         this.company = company;
     }
+    public Manager(String name) {
+        this(name, null);
+    }
```

### 4.4 One public class per file (exercise 03)

`Shape.java` originally declared both `class Shape` and `class Rectangle`. They
were split into `Shape.java` and `Rectangle.java`.

### 4.5 Variables shadowing type names (exercise 03)

Before (`ShapeApp.java`):

```java
var Shape = new Shape();
var Rectangle = new Rectangle();
```

Local variables named exactly like the classes they instantiate are legal but
confusing. After: `var shape = new Shape();`, `var rectangle = new Rectangle();`.

### 4.6 Exam subclasses ignored their own inherited state (exercise 04)

This is the most interesting defect. `Islam` and `Yunani` inherit from
`Peradaban`, but their `tampilkan()` methods built a *separate* parent object
instead of using the state they inherited:

Before (`Islam.java`):

```java
public class Islam extends Peradaban {
    public void tampilkan() {
        String masa = "750 M sampai 1258 M";

        Peradaban t = new Peradaban();      // a throwaway parent object

        t.inputdata("Konsep Robot", "Matematika", "Masjid");
        t.tampilkandata();

        System.out.println("masa Keemasan peradaban Islam dari" + masa);
    }
}
```

`extends Peradaban` buys nothing here — the instance's own `teknologi`, `ilmu`
and `peninggalan` fields are never touched, so the subclass could be deleted and
the output would not change. After:

```java
public class Islam extends Peradaban {
    public void tampilkan() {
        String masa = "750 M sampai 1258 M";

        inputdata("Konsep Robot", "Matematika", "Masjid");   // sets *this*
        tampilkandata();                                     // prints *this*

        System.out.println("Masa Keemasan peradaban Islam dari " + masa);
    }
}
```

### 4.7 Missing space in string concatenation (exercise 04)

`"... dari" + masa` produced `daridelapan SM sampai empat SM` and
`dari750 M sampai 1258 M` in the original run. Now `" dari " + masa`, giving
`dari delapan SM...` / `dari 750 M...`.

### 4.8 Package renamed `Peradaban` → `peradaban` (exercise 04)

Java packages are lowercase by convention; the exam used a capitalised package
name. Source files now live under `src/peradaban/` and are run as
`peradaban.RunPeradaban`.

### 4.9 Cosmetic typo fixes

- `sayHello(String paraname)` → `sayHello(String otherName)`.
- Message spelling `"Myname"` → `"My name"`.

### Deliberate non-changes

- **No `equals`/`hashCode` added.** The `person`/`Manager`/`Shape` exercises
  never compare objects for value equality, so adding them would be invention
  rather than a fix. (The one place value equality mattered — the `Kucing`
  class — belongs to the excluded Dicoding material.)
- **No god classes to split.** Every class here is small; the "one public class
  per file" split in 4.4 was about file layout, not size.
- **Original output wording otherwise preserved** so the exercises still read as
  the student's coursework.

---

## 5. Personal data

- The assignment PDF (`Tugas Java OOP.pdf`) contains the author's full name and
  student ID (`40201961…`). It is **not** committed.
- The student ID does **not** appear in any `.java` file — verified with
  `grep -rInE '[0-9]{8,}'` and a targeted search for the ID: no matches in
  sources.
- No print statement contains a name, e-mail address or ID.
- The copyright holder in `LICENSE` is the course's author; a copyright line is
  required for the MIT licence to be valid. If a fully anonymous repository is
  preferred, change the holder in `LICENSE`.

---

## 6. Verification

Environment (real, from this machine):

```
$ java -version
openjdk version "25.0.4.1" 2026-08-18
OpenJDK Runtime Environment (Red_Hat-25.0.4.1.1-1) (build 25.0.4.1+1)

$ javac -version
javac 25.0.4.1

$ command -v mvn   || echo "no maven"     # -> no maven
$ command -v gradle|| echo "no gradle"    # -> no gradle
$ command -v make                         # -> /usr/bin/make
```

No Maven or Gradle is installed, so the build is plain `javac`/`java` driven by
a `Makefile` and `verify/run-all.sh`. This is stated plainly rather than naming
a build tool that was never run.

Full run:

```
$ bash verify/run-all.sh
==============================================================
Java OOP coursework — build & verification
javac: javac 25.0.4.1
java : openjdk version "25.0.4.1" 2026-08-18
repo : /home/heisenberg/portfolio-rework/repos/java-oop-coursework
mode : verify
==============================================================
[01] class-and-object
  PASS  01-class-and-object
[02] person-constructors
  PASS  02-person-constructors
[03] inheritance-polymorphism (3 entry points)
  PASS  03-inheritance-polymorphism
[04] peradaban-uas
  PASS  04-peradaban-uas
==============================================================
Result: 4 passed, 0 failed
==============================================================
```

The harness is not a no-op. Corrupting one expected file on purpose makes it
fail and exit non-zero:

```
$ sed -i 's/Jam Air/Jam Angin/' verify/expected/04-peradaban-uas.txt
$ bash verify/run-all.sh ; echo EXIT=$?
  output mismatch for 04-peradaban-uas (expected vs actual):
  ...
  FAIL  04-peradaban-uas
Result: 3 passed, 1 failed
EXIT=1
```

(The file was restored afterwards; the final state passes 4/4.)

### Baseline: what the originals did

Before any changes, the unmodified sources were compiled and run to record
their real behaviour:

```
--- 01 (original) ---
person@1dbd16a6
person@7ad041f3
person@251a69d7

--- 02 (original) ---
Rahmat
Surabaya
Indonesia
Hello Budi, Myname is Rahmat
Hello Budi, Myname is Joko

--- 04 (original) ---
...
masa peradaban Yunani daridelapan SM sampai empat SM
...
masa Keemasan peradaban Islam dari750 M sampai 1258 M
```

All four originals compiled and ran cleanly under JDK 25; nothing had to be
dropped, and no exercise was abandoned.
