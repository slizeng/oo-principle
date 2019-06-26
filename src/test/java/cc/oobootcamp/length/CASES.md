- GIVEN two lengths with diff values,
- AND one is longer (e.g. = 3), another one is shorter (e.g. = 2).
- WHEN the longer one compares to the shorter one.
- THEN should return `true` from `isLongerThan` method,
- AND return `false` from `isEqualTo` & `isShorterThan` methods.


- GIVEN two lengths with diff values,
- AND one is longer (e.g. = 3), another one is shorter (e.g. = 2).
- WHEN the shorter one compares to the longer one.
- THEN should return `true` from `isShorterThan` method,
- AND return `false` from `isLongerThan` & `isEqualTo` methods.


- GIVEN two lengths with same value,
- AND both of two lengths are 2.
- WHEN compare them.
- THEN should return `true` from `isEqualTo` method,
- AND return `false` from `isLongerThan` & `isShorterThan` methods.
