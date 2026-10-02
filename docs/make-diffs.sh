#!/usr/bin/env bash
#
# Regenerate the before/after diffs used in docs/evaluation.md.
# Run from anywhere; paths are resolved relative to this script.

set -u
REPO_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
O="$REPO_DIR/docs/originals"
E="$REPO_DIR/exercises"

pair() {
  local label="$1" before="$2" after="$3"
  echo "===================== $label ====================="
  if [ -f "$before" ] && [ -f "$after" ]; then
    diff -u "$before" "$after" | sed '1,2d'
  else
    echo "(missing: $before or $after)"
  fi
  echo
}

pair "01: person.java -> Person.java" \
     "$O/01-class-and-object/person.java" "$E/01-class-and-object/src/Person.java"
pair "01: PersonApp.java" \
     "$O/01-class-and-object/PersonApp.java" "$E/01-class-and-object/src/PersonApp.java"

pair "02: person.java -> Person.java" \
     "$O/02-person-constructors/person.java" "$E/02-person-constructors/src/Person.java"
pair "02: PersonApp.java" \
     "$O/02-person-constructors/PersonApp.java" "$E/02-person-constructors/src/PersonApp.java"

pair "03: person.java -> Person.java" \
     "$O/03-inheritance-polymorphism/person.java" "$E/03-inheritance-polymorphism/src/Person.java"
pair "03: Manager.java" \
     "$O/03-inheritance-polymorphism/Manager.java" "$E/03-inheritance-polymorphism/src/Manager.java"
pair "03: VicePresident.java" \
     "$O/03-inheritance-polymorphism/VicePresident.java" "$E/03-inheritance-polymorphism/src/VicePresident.java"
pair "03: Shape.java (split: Rectangle moved out)" \
     "$O/03-inheritance-polymorphism/Shape.java" "$E/03-inheritance-polymorphism/src/Shape.java"
pair "03: ShapeApp.java" \
     "$O/03-inheritance-polymorphism/ShapeApp.java" "$E/03-inheritance-polymorphism/src/ShapeApp.java"
pair "03: ManagerApp.java" \
     "$O/03-inheritance-polymorphism/ManagerApp.java" "$E/03-inheritance-polymorphism/src/ManagerApp.java"

pair "04: Peradaban.java" \
     "$O/04-peradaban-uas/Peradaban.java" "$E/04-peradaban-uas/src/peradaban/Peradaban.java"
pair "04: Islam.java" \
     "$O/04-peradaban-uas/Islam.java" "$E/04-peradaban-uas/src/peradaban/Islam.java"
pair "04: Yunani.java" \
     "$O/04-peradaban-uas/Yunani.java" "$E/04-peradaban-uas/src/peradaban/Yunani.java"
pair "04: RunPeradaban.java" \
     "$O/04-peradaban-uas/RunPeradaban.java" "$E/04-peradaban-uas/src/peradaban/RunPeradaban.java"
