# BasicMetricsEngine
## How to run
1. Clone the repository
    ```bash
    git clone https://github.com/RiadZX/BasicMetricsEngine/tree/master
    ```
2. The current directory to analyze from is hardcoded in the `Main.java` file. You can either change the String directory in there, or pass the directory as a CLI argument.
3. Run the main method in the `Main` class
4. The output will be printed to the console.

## Approach
> During each step where necessary, I wrote tests to make sure my methods were correctly implemented before moving on to other classes.
1. I started with making sure I understood the requirements properly.
2. I split up the project into smaller manageable tasks.
3. I decided to first start with the ComplexityEvaluator, since it is the core of the project.

    I first created a class to get the complexity of the methods in a single file. Once I had the skeleton of the class, I started thinking about the algorithm to  Run the main method in the `Main` class and get the name of the method. 

    The two most important aspects of the algorithm were:
    - Knowing when you are in a method, if, for, while, or not at all.
    - If you know that you are in a method, what is the name of the method.

    I came up with an algorithm to solve these two problems. To know whether you are in a method or some conditional statement I decided to use the stack. The stack indicated the **_depth_** of the code. By depth I mean how many curly braces are you nested in. 
    
    **For getting the name of the method**, there were a couple of points that I had to account for:
   - Is the opening curly bracket on the same line of the method definition?
   - Is it on the line below it, or perhaps 10 lines below. With method parameters in between.

    So clearly getting the name had to be kind of dynamic and not a sort of fixed, position. i.e you can not assume that a method will always look the same. So this is how my implementation roughly works:
   - We get the index of an opening curly bracket `{`, that belongs to a method, from the algorithm described above.
   - We get the index of the `{`, on that line, and start _walking_ backwards until we encounter an opening round bracket `(`. Any word before this, must then be the name of the method. Now this `(`, could be on the same line or `x` lines above it. So we must also take that into account.

4. Since we know have the complexity evaluator done, I copied and pasted the algorithm into the CodeStyleChecker class and tweaked such that it only gets the method name, and checks whether its lower camelCase or not.
5. I then created an ``Application`` class, which uses both previous classes, and gives the ability to run the methods on all files in a directory rather than a single file.
6. In Main I call the ``Application`` class, and print the results in a formatted table.

## Design Choices 
### Design Choice 1: Separation of Concerns
You can see that the CodeStyleChecker class and the CodeComplexityEvaluator class behave almost in the exact same way (*Both use the same algorithm with a stack*). So you could say that since they are very similar, that you could merge them into 1 single class/method. In my opinion: if these were the only code metrics we would ever add to the engine, then that would not be a bad idea, since it would result in higher performance. However, since there is a very high chance that we will be adding more metrics, it is better to keep them separated.
### Design Choice 2: Code Complexity Formula
The code complexity starts from 0. So if you have a method with no conditional statements: complexity -> 0.