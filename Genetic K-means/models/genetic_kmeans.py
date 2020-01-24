from genetic_algorithm import GeneticAlgorithm
import numpy as np
from sklearn import datasets
from sklearn.cluster import KMeans


def run_algo(ga, X, pop_size):
	pop = ga.init_population(pop_size)

	ga.evaluate_fitness(pop)

	max_generations = 1000

	gen_count = 0

	previous_best = np.zeros(pop.get_fittest().shape)

	converged = 0

	for i in range(max_generations):
		fittest = pop.get_fittest()

		# print("G" + str(i), "Best Solution", fittest)

		pop = ga.natural_selection(pop)

		ga.evaluate_fitness(pop)

		if np.all(previous_best == fittest):
			converged += 1
		else:
			converged = 0

		if converged == 2:
			break

		gen_count = i

		previous_best = fittest

	print("==================================================================")
	print("Stopped after", gen_count, "generations")
	fittest = pop.get_fittest()
	print("Best solution (" + str(-fittest.get_fitness()) + "):", fittest.decode())
	print()

	print()
	kmeans = KMeans(3)
	kmeans.fit(X)
	print("official sklearn: ", kmeans.score(X))
	print(kmeans.cluster_centers_)
	# print("Same clusters", np.all(fittest.centers == kmeans.cluster_centers_))
	print()
	print()


def main():
	iris = datasets.load_iris()
	X = iris.data
	y = iris.target
	n_clusters = 3
	pop_size = 100
	mutation_rate = 0.01
	crossover_rate = 0.85
	elitism_count = 3
	ga = GeneticAlgorithm(n_clusters, pop_size, mutation_rate, X, crossover_rate,
	                      elitism_count)

	d1, y1 = datasets.make_blobs(n_samples=121, centers=3, n_features=5)
	d2, y2 = datasets.make_blobs([378, 233], 2)
	d3, y3 = datasets.make_blobs([220, 145, 56, 378, 117], 5)
	d4, y4 = datasets.make_blobs(100, 8)

	ga1 = GeneticAlgorithm(n_clusters, pop_size, mutation_rate, d1, crossover_rate,
	                       elitism_count)
	ga2 = GeneticAlgorithm(n_clusters, pop_size, mutation_rate, d2, crossover_rate,
	                       elitism_count)
	ga3 = GeneticAlgorithm(n_clusters, pop_size, mutation_rate, d3, crossover_rate,
	                       elitism_count)
	ga4 = GeneticAlgorithm(n_clusters, pop_size, mutation_rate, d4, crossover_rate,
	                       elitism_count)

	run_algo(ga, X, pop_size)
	run_algo(ga1, d1, pop_size)
	run_algo(ga2, d2, pop_size)
	run_algo(ga3, d3, pop_size)
	run_algo(ga4, d4, pop_size)


if __name__ == '__main__':
	main()
