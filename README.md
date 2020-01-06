# Winter-Term-2020
Genetic Algorithms For Winter Term 2020

In this project, I use Genetic algorithms to try to precict a given target string.

Stages

1. Generate Population
Given a target phrase. eg, "to be or not to be", the model first generates a population of random phrases.

2. Evaluate Fitness
Next, we calculate the fitness score for each of the entities in the population. 
The fitness score I use is the square of the proportion of characters in the generated phrase that are in the same position as the target string.

3. Selection
Using the fitness score, choose two parents to use in the next stage (reproduction)
To choose the parents, we have two methods:
  - We either build a mating pool that contains all the strings in the population, but how many times they appear in the pool depends on how close the strings are to the target phrase.
  - Use the Monte Carlo method to pick two parents, using the fitness score as the probability that a parent is chosen
  
 4. Reproduction
 Next, we create a new child entity from the two selected parents using crossover.
 There are two contenting crossover methods:
  - Random Midpoint Crossover
  Choosing a random midpoint in one parent, and picking genes up until the this midpoint, and then choosing the rest of the genes from the other parent.
  
  - Equal Chance Crossover
  Here, each parent has an equal chance of providing the each specific gene character. We flip a coin, HEADS we choose a the corresponding character from parent 1, TAIL we choose from parent 2.
  
5. Create new generation
  We add each child to a new population set. Once we have aas many children as we had in the original population, we say a generation has ended.
  
  6/ Repeat steps 1-5 for each new generation, until we reach a total_geeneration_limit, or until we get a solution for the problem, whichever comes first.


Inspired: The Nature of Code by Daniel Shiffman. (Chapter 9)
