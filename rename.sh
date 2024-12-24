#!/bin/bash

#Script for renaming assets files (cutting necessary prefix)

directory="assets/textures/mole/normal"

for file in "$directory"/_*; do
    [ -e "$file" ] || continue

    base_dir=$(dirname "$file")
    new_name=$(basename "$file" | cut -c2-)

    mv "$file" "$base_dir/$new_name"
    echo "Renamed: $(basename "$file") -> $new_name"
done
