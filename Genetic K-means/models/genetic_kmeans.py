from genetic_algorithm import GeneticAlgorithm
import numpy as np
from sklearn import datasets
from sklearn.cluster import KMeans


def main():
	iris = datasets.load_iris()
	X = iris.data
	y = iris.target
	n_clusters = 3
	pop_size = 5
	mutation_rate = 0.01
	crossover_rate = 0.85
	elitism_count = 3
	ga = GeneticAlgorithm(n_clusters, pop_size, mutation_rate, X, crossover_rate,
	                      elitism_count)

	pop = ga.init_population(pop_size)

	ga.evaluate_fitness(pop)

	max_generations = 1000

	gen_count = 0

	previous_best = np.zeros(pop.get_fittest().shape)

	converged = 0

	for i in range(max_generations):
		fittest = pop.get_fittest()

		print("G" + str(i), "Best Solution", fittest)

		pop = ga.natural_selection(pop)

		ga.evaluate_fitness(pop)

		if np.all(previous_best == fittest):
			converged += 1
		else:
			converged = 0

		if converged == 3:
			break

		gen_count = i

		previous_best = fittest

	print("==================================================================")
	print("Stopped after", gen_count, "generations")
	fittest = pop.get_fittest()
	print("Best solution (" + str(fittest.get_fitness()) + "):", fittest.decode())
	print(fittest.get_fitness())

	print()
	kmeans = KMeans(3)
	kmeans.fit(X)
	print("official sklearn: ", kmeans.score(X))
	print(kmeans.cluster_centers_)




if __name__ == '__main__':
	main()