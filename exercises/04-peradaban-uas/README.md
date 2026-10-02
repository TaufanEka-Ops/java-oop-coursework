# 04 — Peradaban (Final Exam / UAS)

**Concepts:** encapsulation, inheritance, polymorphism — applied to a small
domain model instead of a toy `Person`.

`Peradaban` ("civilisation") is a base class with three encapsulated pieces of
state — technology, science and relics (`teknologi`, `ilmu`, `peninggalan`) —
plus `inputdata(...)` and `tampilkandata()`. Two subclasses, `Islam` and
`Yunani`, override the behaviour through their own `tampilkan()` method, each
describing a real historical period (the Islamic Golden Age, ancient Greece).
`RunPeradaban` prints both in turn.

This was the **end-of-term exam (`UAS`)**, written under time pressure. Its
domain is original — the specific content ("Konsep Robot", "Jam Air",
"Istana Emas Nero") appears in no tutorial.

**Authorship:** own work.

## Compile and run

From the repository root:

```bash
javac -d build/04 exercises/04-peradaban-uas/src/peradaban/*.java
java  -cp build/04 peradaban.RunPeradaban
```

## Expected output

```
Yunani
Penemuan Teknologi pada masa ini adalah Jam Air
Perkembangan Ilmu pengetahuan pada masa ini adalah Filsafat
Peninggalan dari masa ini adalah Istana Emas Nero
Masa peradaban Yunani dari delapan SM sampai empat SM

Islam
Penemuan Teknologi pada masa ini adalah Konsep Robot
Perkembangan Ilmu pengetahuan pada masa ini adalah Matematika
Peninggalan dari masa ini adalah Masjid
Masa Keemasan peradaban Islam dari 750 M sampai 1258 M
```

## Fixes applied

- the package was named `Peradaban` (capitalised); renamed to lowercase
  `peradaban` per Java package convention;
- `Islam.tampilkan()` and `Yunani.tampilkan()` each created a *separate*
  `Peradaban` object, populated that, and printed it — the subclasses never
  used their own inherited state, which defeats the purpose of inheritance.
  They now call `inputdata(...)` and `tampilkandata()` on `this`;
- a missing space in `"... dari" + masa` (which printed `daridelapan SM` and
  `dari750 M`) was corrected.
