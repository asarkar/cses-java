The [CSES Problem Set](https://cses.fi/problemset/) contains a collection of algorithm programming practice problems.
See the list of [contributors](https://cses.fi/about) here. The problem set is licensed under [CC BY-NC-SA 4.0](https://creativecommons.org/licenses/by-nc-sa/4.0/).

[![](https://github.com/asarkar/cses-java/workflows/CI/badge.svg)](https://github.com/asarkar/cses-java/actions)

## Table of Contents

### Sorting and Searching
* [Distinct Numbers](https://cses.fi/problemset/task/1621) - [Solution](sorting&searching/src/main/java/P1621.java)
* [Apartments](https://cses.fi/problemset/task/1084) - [Solution](sorting&searching/src/main/java/P1084.java)

## Where are the solutions to the other problems?
I moved on after attempting a few problems, as it became clear that solving the problem set wasn't
the best use of my time.

1. **Inconsistent Execution Times**:
   Despite identical algorithm, memory and execution constraints, runtime behavior is inconsistent.
   Code that executes in milliseconds locally can take over a second on the site, leading to 
   frustrating timeouts.

2. **Promotion of Suboptimal Coding Practices**

   * **Single-Function Solutions** – The site effectively discourages clean, modular design by 
     rewarding monolithic, single-function code over well-structured, reusable components. 
     For example, compare [my solution](sorting&searching/src/main/java/P1084.java) 
     with this [random online solution](https://github.com/Dev-eloperr/CSES/blob/master/CSES%20Apartments/src/Main.java).
     Which would you rather see in a real-world codebase? Fun fact: Mine times out, while the other passes.

   * **Manual Input Handling** – Tedious, low-level input parsing feels outdated and 
     cumbersome, as if we're coding for punch cards in 1969.

   * **Java Package Restrictions** – Every Java program must reside in the default package.

3. **Language Bias**:
   The fastest solutions are typically in Assembly, C++, or Rust, putting those using other 
   languages at a disadvantage. It would be more meaningful to compare performance within 
   each language group, rather than across all of them.

4. **Outdated Language Runtimes**:
   The site uses [outdated language runtimes](https://cses.fi/howto/) incompatible with modern tooling, 
   making it difficult to practice locally without significant friction.

Ultimately, I realized that my time was better spent on elsewhere.

## License

[Creative Commons BY-NC-SA 4.0](https://creativecommons.org/licenses/by-nc-sa/4.0/).
