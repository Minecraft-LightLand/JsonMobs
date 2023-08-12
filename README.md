# JsonMobs

## Changes

- YAML support writing any characters without replacing with `<&xx>`
- Inline lists supports `-` and `,` as separator, and there aren't any space count limitations.
- YAML support writing escape characters such as `\t`, `\u0000`, `\\`, `\"`, `\'`
- Range and Random values can use `#-#`, `#to#`, or `#~#` to represent
    - When using `#-#`, the first number can be negative
    - When using `#to#` and `#~#`, you can use scientific notations such as `-1.11e-1`
- Condition composition supports `!`, `&&`, `||`, `^`, `==` operators.
  - Composite conditions always need to be wrapped by parenthesis `()`

## Features Implemented
- Conditions
- in-line conditions
- ranged numbers
- random numbers
- expression as number or boolean or string

## Features not Implemented
- Variables
- Multi-Part Health Modifier
- Variables in Condition

## Mechanics
- Multiple condition actions
  - When a condition terminates the main skill (cancelled, or cast another skill instead), later conditions will not execute even if they would potentially add new skills. However, it would not remove already added "instead" skills.
  - If you want to add `Cast` actions that could be removed by termination, add it after termination action
