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


## Design Choices 
### Design Choice 1: Separation of Concerns
You can see that the CodeStyleChecker class and the CodeComplexityEvaluator class behave almost in the exact same way (*Both use the same algorithm with a stack*). So you could say that since they are very similar, that you could merge them into 1 single class/method. In my opinion: if these were the only code metrics we would ever add to the engine, then that would not be a bad idea, since it would result in higher performance. However, since there is a very high chance that we will be adding more metrics, it is better to keep them separated.
### Design Choice 2: Code Complexity Formula
The code complexity starts from 0. So if you have a method with not conditional statements: complexity -> 0.